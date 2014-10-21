package com.adorsys.server.login;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.jboss.resteasy.logging.Logger;

import com.adorsys.projet.util.DbCollectionUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

	/**
	 * @author brice
	 *
	 */
	public class JAASLoginModule implements LoginModule { 
	 
	    private static Logger LOGGER = Logger.getLogger(JAASLoginModule.class); 
	 
	    // initial state
	    private Subject subject;
	    private CallbackHandler callbackHandler;
	    private Map sharedState;
	    private Map options;

	    // configurable option
	    private boolean debug = false;
	    
	    // the authentication status
	    private boolean succeeded = false;
	    private boolean commitSucceeded = false;
	    
	    //user credentials
	    private String username = null;
	    private char[] password = null;
	    private String _idUser="";
	    
	    //user principle
	   private JAASUserPrincipal userPrincipal = null;
	   private JAASPasswordPrincipal passwordPrincipal = null;
	   
		private DB db  = DbCollectionUtil.getdb();
		
	    
	    public JAASLoginModule() {
	         super();
	    }

	    public void initialize(Subject subject, CallbackHandler callbackHandler,
	                Map<String, ?> sharedState, Map<String, ?> options) {
	        this.subject = subject;
	        this.callbackHandler = callbackHandler;
	        this.sharedState = sharedState;
	        this.options = options;
	  
	        debug = "true".equalsIgnoreCase((String)options.get("debug")); 
	    }

	    @Override
	    public boolean login() throws LoginException {
	  
	        if (callbackHandler == null){
	            throw new LoginException("Error: no CallbackHandler available " +
	            "to garner authentication information from the user");
	        }
	        Callback[] callbacks = new Callback[2];
	        callbacks[0] = new NameCallback("username");
	        callbacks[1] = new PasswordCallback("password: ", false);
	  
	        try {
	   
	            callbackHandler.handle(callbacks);
	            username = ((NameCallback)callbacks[0]).getName();
	            password = ((PasswordCallback)callbacks[1]).getPassword();
	   
	            if (debug) {
	                LOGGER.debug("Username :" + username);
	                LOGGER.debug("Password : " + password);
	            }
	   
	            if (username == null || password == null) {
	                LOGGER.error("Callback handler does not return login data properly");
	                throw new LoginException("Callback handler does not return login data properly"); 
	            }
	   
	            if (isValidUser()) { //validate user.
	                succeeded = true;
	                return true;
	            } 
	   
	        } catch (IOException e) { 
	             e.printStackTrace();
	        } catch (UnsupportedCallbackException e) {
	             e.printStackTrace();
	        }
	  
	        return false;
	    }

	    @Override
	    public boolean commit() throws LoginException {
	        
	    	if (succeeded == false) {
	            return false;
	        } 
	        else { 
	         userPrincipal = new JAASUserPrincipal(username);
	            if (!subject.getPrincipals().contains(userPrincipal)) {
	                subject.getPrincipals().add(userPrincipal);
	                LOGGER.debug("User principal added:" + userPrincipal);
	            }
	            passwordPrincipal = new JAASPasswordPrincipal(new String(password)); 
	            if (!subject.getPrincipals().contains(passwordPrincipal)) {
	                subject.getPrincipals().add(passwordPrincipal);
	                LOGGER.debug("Password principal added: " + passwordPrincipal);
	            }
	      
	            //populate subject with roles.
	            List<String> roles = getRoles();
	            for (String role: roles) {
	                JAASRolePrincipal rolePrincipal = new JAASRolePrincipal(role);
	                if (!subject.getPrincipals().contains(rolePrincipal)) {
	                    subject.getPrincipals().add(rolePrincipal); 
	                    LOGGER.debug("Role principal added: " + rolePrincipal);
	                }
	            }
	      
	            commitSucceeded = true;
	      
	            LOGGER.info("Login subject were successfully populated with principals and roles"); 
	      
	            return true;
	       }
	    }

	   @Override
	   public boolean abort() throws LoginException {
	      if (succeeded == false) {
	          return false;
	      } else if (succeeded == true && commitSucceeded == false) {
	          succeeded = false;
	          username = null;
	          if (password != null) {
	              password = null;
	          }
	          userPrincipal = null;    
	      } else {
	          logout();
	      }
	      return true;
	      
	   }

	    @Override
	    public boolean logout() throws LoginException {
	        subject.getPrincipals().remove(userPrincipal);
	        succeeded = false;
	        succeeded = commitSucceeded;
	        username = null;
	        if (password != null) {
	            for (int i = 0; i < password.length; i++){
	                password[i] = ' ';
	                password = null;
	            }
	        }
	        userPrincipal = null;
	        return true;
	   }
	 
	   private boolean isValidUser() throws LoginException {

		   try {
	            BasicDBObject search = new BasicDBObject();
	            search.put("username", username);
	            DBCollection dbcollection = db.getCollection("User");
	            DBObject user = dbcollection.findOne(search);

	            if (user == null){
	                LOGGER.debug("utilisateur introuvable!");
	                return false;
	            }else{
	                if ( new String(password).equals(user.get("password"))){
	                   _idUser = user.get("_id").toString();
	                    return true;
	                }else{
	                    LOGGER.debug("mot de passe invalide le l'user " + username);
	                }
	            }

	            return false;
	        } catch (Exception e) {
	            LOGGER.error("Error when loading user " + username + " from the database \n", e);
	        }
	       return false;
	   }

	 /**
	  * Returns list of roles assigned to authenticated user.
	  * @return
	  */
	  private List<String> getRoles() { 
	      
	      
		/*  BasicDBObject search = new BasicDBObject();
	        search.put("_idUser", _idUser);
	        DBObject userModules = db.getCollection("UserModules").findOne(search);
	        String jsonArrayModules = userModules.get("_idModules").toString();

	        String[] modules = null;
	        try {
	            modules = new ObjectMapper().readValue(jsonArrayModules, String[].class);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }*/

	        List<String> modulesList = new ArrayList<String>();
	    /*    for (String _idModule : modules) {
	            DBObject module = db.getCollection("Module").findOne(new BasicDBObject("_id", new ObjectId(_idModule)));
	            if (module != null){
	                modulesList.add(module.get("name").toString());
	            }
	        }*/

	        return modulesList;
	    
	 }

	
	}

	
