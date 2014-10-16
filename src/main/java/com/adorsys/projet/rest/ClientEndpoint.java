package com.adorsys.projet.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import com.adorsys.projet.model.Client;
import com.adorsys.projet.util.DbCollectionUtil;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * 
 */
@Stateless
@Path("/clients")
public class ClientEndpoint
{
	private DB db  = DbCollectionUtil.getdb();
	DBCollection dbcollection = db.getCollection("client");

   @POST
   @Consumes("application/json")
   public Response create(Client entity)
   {
	   JacksonDBCollection<Client, String> coll = JacksonDBCollection.wrap(dbcollection, Client.class,
		       String.class);
	WriteResult<Client, String> result = coll.insert(entity);
	entity = result.getSavedObject();
	
      return Response.created(UriBuilder.fromResource(ClientEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id}")
   public Response deleteById(@PathParam("id") String id)
   {
	   JacksonDBCollection<Client, String> coll = JacksonDBCollection.wrap(dbcollection, Client.class,
		       String.class);
	  // Auteur entity = coll.findAndRemove(DBQuery.is("id", id));
	   WriteResult<Client, String> entity= coll.removeById(id);
	   
      return Response.noContent().build();
   }

   @GET
   @Path("/{id}")
   @Produces("application/json")
   public Response findById(@PathParam("id") String id)
   {
	   String j = id+"";
	   JacksonDBCollection<Client, String> coll = JacksonDBCollection.wrap(dbcollection, Client.class,
		       String.class);
	   
	   Client entity = coll.findOneById(j);
      
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<Client> listAll()
   {
	   JacksonDBCollection<Client, String> coll= JacksonDBCollection.wrap(dbcollection, Client.class, String.class);
	   DBCursor<Client> results =coll.find();
        List<Client> arrayList = new ArrayList<Client>();
	   while(results.hasNext()){
    	 Client reponse= results.next(); 
    	 arrayList.add(reponse);
     }
	   return arrayList;
   }

   @PUT
   @Path("/{id}")
   @Consumes("application/json")
   public Response update(Client entity)
   {
		  JacksonDBCollection<Client, String> coll= JacksonDBCollection.wrap(dbcollection, Client.class, String.class);
		     // DBUpdate.Builder builder= new DBUpdate.Builder();
		      
		      Client result=coll.save(entity).getSavedObject();
      return Response.noContent().build();
   }
}