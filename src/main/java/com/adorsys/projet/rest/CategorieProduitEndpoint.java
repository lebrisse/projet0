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

import com.adorsys.projet.model.CategorieProduit;
import com.adorsys.projet.util.DbCollectionUtil;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * 
 */
@Stateless
@Path("/categorieproduits")
public class CategorieProduitEndpoint
{
	private DB db  = DbCollectionUtil.getdb();
	DBCollection dbcollection = db.getCollection("categorieproduit");

   @POST
   @Consumes("application/json")
   public Response create(CategorieProduit entity)
   {
	   JacksonDBCollection<CategorieProduit, String> coll = JacksonDBCollection.wrap(dbcollection, CategorieProduit.class,
		       String.class);
	WriteResult<CategorieProduit, String> result = coll.insert(entity);
	entity = result.getSavedObject();
	
      return Response.created(UriBuilder.fromResource(CategorieProduitEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id}")
   public Response deleteById(@PathParam("id") String id)
   {
	   JacksonDBCollection<CategorieProduit, String> coll = JacksonDBCollection.wrap(dbcollection, CategorieProduit.class,
		       String.class);
	  // Auteur entity = coll.findAndRemove(DBQuery.is("id", id));
	   WriteResult<CategorieProduit, String> entity= coll.removeById(id);
	   
      return Response.noContent().build();
   }

   @GET
   @Path("/{id}")
   @Produces("application/json")
   public Response findById(@PathParam("id") String id)
   {
	   String j = id+"";
	   JacksonDBCollection<CategorieProduit, String> coll = JacksonDBCollection.wrap(dbcollection, CategorieProduit.class,
		       String.class);
	   
	   CategorieProduit entity = coll.findOneById(j);
	   
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<CategorieProduit> listAll()
   {
	   JacksonDBCollection<CategorieProduit, String> coll= JacksonDBCollection.wrap(dbcollection, CategorieProduit.class, String.class);
	   DBCursor<CategorieProduit> results =coll.find();
        List<CategorieProduit> arrayList = new ArrayList<CategorieProduit>();
	   while(results.hasNext()){
		   CategorieProduit reponse= results.next(); 
    	 arrayList.add(reponse);
     }
	   return arrayList;
   }

   @PUT
   @Path("/{id}")
   @Consumes("application/json")
   public Response update(CategorieProduit entity)
   {
	   JacksonDBCollection<CategorieProduit, String> coll= JacksonDBCollection.wrap(dbcollection, CategorieProduit.class, String.class);
	     // DBUpdate.Builder builder= new DBUpdate.Builder();
	      
	   CategorieProduit result=coll.save(entity).getSavedObject();
	   
      return Response.noContent().build();
   }
}