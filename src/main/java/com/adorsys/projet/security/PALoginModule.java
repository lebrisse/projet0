package com.adorsys.projet.security;

import java.io.IOException;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import com.adorsys.projet.model.User;
import com.adorsys.projet.util.DbCollectionUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class PALoginModule implements LoginModule {

	protected Subject subject;
	protected CallbackHandler callbackHandler;
	protected Map<String, ?> sharedState;
	protected Map<String, ?> options;
	protected Logger log;
	protected boolean trace = false;
	protected static String username="brice";
	protected static String password="";

	/**
	 * Flag indicating if the login phase succeeded. Subclasses that override
	 * the login method must set this to true on successful completion of login
	 */
	protected boolean loginOk;
	/** the principal to use when a null username and password are seen */
	protected Principal unauthenticatedIdentity;
	private User user ;
	private static final Logger LOGGER = Logger.getLogger(PALoginModule.class.getName());
	private DB db  = DbCollectionUtil.getdb();
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {

		InitialContext initialContext;
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;
		log = Logger.getLogger(getClass().getName());
		trace = log.isLoggable(Level.FINER);
		if (trace)
		{
			log.finer("initialize");

			// log securityDomain, if set.
			log.finer("Security domain: "
					+ (String) options
					.get(SecurityConstants.SECURITY_DOMAIN_OPTION));
		}

		// Check for unauthenticatedIdentity option.
		String name = (String) options.get("unauthenticatedIdentity");
		if (name != null)
		{
			try
			{
				unauthenticatedIdentity = new SimplePrincipal(name);
				if (trace)
					log.finer("Saw unauthenticatedIdentity = " + name);
			}
			catch (Exception e)
			{
				log.warning("Failed to create custom unauthenticatedIdentity: "
						+ e.getMessage());
			}
		}

	}

	@Override
	public boolean login() throws LoginException {

		 
		NameCallback nameCallback = new NameCallback("Enter your user name: ");
		PasswordCallback passwordCallback = new PasswordCallback(
				"Enter your password", false);
		BasicDBObject search = new BasicDBObject();
        search.put("username", username);
        DBCollection dbcollection = db.getCollection("User");
        DBObject Userr = dbcollection.findOne(search);
        
		try
		{
			callbackHandler.handle(new Callback[] { nameCallback,
					passwordCallback });
		}
		catch (IOException e)
		{
			throw new IllegalStateException(e);
		}
		catch (UnsupportedCallbackException e)
		{
			throw new IllegalStateException(e);
		}

		String username=nameCallback.getName();
		String password=new String( passwordCallback.getPassword());

		if (username.equals(Userr.get("username")) && password.equals(Userr.get("password"))){
			
			LOGGER.log(Level.SEVERE, "Mon erreure");
			user= new User();
			user.setUsername(username);
			user.setPassword(password);
			return true;	            
			
		}
		return false;
	}

	@Override
	public boolean abort() throws LoginException {
		if (trace)
			log.finer("abort");
		user = null;
		return true;
	}
	
	@Override
	public boolean commit() throws LoginException {
		 if (user == null)
				return false;

			/*
			 * The set of principals of this subject. We will add the 
			 * SecurityConstants.CALLER_PRINCIPAL_GROUP and the 
			 * SecurityConstants.ROLES_GROUP to this set.
			 */
			Set<Principal> principals = subject.getPrincipals();

			/*
			 * The user identity.
			 */
			Principal identity = new SimplePrincipal(user.getUsername());
			principals.add(identity);

			// get the CallerPrincipal group
			Group callerGroup = findGroup(SecurityConstants.CALLER_PRINCIPAL_GROUP, principals);
			if (callerGroup == null)
			{
				callerGroup = new SimpleGroup(SecurityConstants.CALLER_PRINCIPAL_GROUP);
				principals.add(callerGroup);
			}
			// Add this principal to the group.
			callerGroup.addMember(identity);

			// get the Roles group
			Group[] roleSets = getRoleSets();
			for (Group group : roleSets)
			{
				Group sunjectGroup = findGroup(group.getName(), principals);
				if (sunjectGroup == null)
				{
					sunjectGroup = new SimpleGroup(group.getName());
					principals.add(sunjectGroup);
				}
				// Copy the group members to the Subject group
				Enumeration<? extends Principal> members = group.members();
				while (members.hasMoreElements())
				{
					Principal role = (Principal) members.nextElement();
					sunjectGroup.addMember(role);
				}
			}
			return true;
	}

	@Override
	public boolean logout() throws LoginException {
		
		if (trace)
			log.finer("logout");

		// Remove all principals and groups of classes defined here.
		Set<Principal> principals = subject.getPrincipals();
		Set<SimplePrincipal> principals2Remove = subject.getPrincipals(SimplePrincipal.class);
		principals.removeAll(principals2Remove);

		return true;
	}
	
	
	private Group[] getRoleSets()
	{
		SimpleGroup simpleGroup = new SimpleGroup(SecurityConstants.ROLES_GROUP);
		simpleGroup.addMember(new SimplePrincipal("LOGIN"));
		return new Group[] { simpleGroup };
	}

	private Group findGroup(String name, Set<Principal> principals)
	{
		for (Principal principal : principals)
		{
			if (!(principal instanceof Group))
				continue;
			Group group = Group.class.cast(principal);
			if (name.equals(group.getName()))
				return group;
		}
		return null;
	}

}
