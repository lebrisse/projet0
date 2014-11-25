package com.adorsys.projet.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.DBUpdate;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import com.adorsys.projet.model.Client;
import com.adorsys.projet.model.Produit;
import com.adorsys.projet.util.DbCollectionUtil;
import com.mongodb.DB;
import com.mongodb.DBCollection;
//import org.jboss.resteasy.plugins.providers.multipart.InputPart;
//import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 * 
 */
@Stateless
@Path("/produits")
public class ProduitEndpoint
{
	private DB db  = DbCollectionUtil.getdb();
	DBCollection dbcollection = db.getCollection("produit");

   @POST
   @Consumes({"application/json","multipart/form-data"})
   public Response create(Produit entity,MultipartFormDataInput dataInput)
   {
     JacksonDBCollection<Produit, String> coll =JacksonDBCollection.wrap(dbcollection,Produit.class, String.class);
     WriteResult<Produit, String> result=coll.insert(entity);
     entity=result.getSavedObject();
	
     Map<String, List<InputPart>> uploadForm = dataInput.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("imageproduit");

		for (InputPart inputPart : inputParts) {
			try {

				InputStream inputStream = inputPart.getBody(InputStream.class,null);
				System.out.println("Done");
				
			} catch (IOException e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
     return Response.created(UriBuilder.fromResource(ProduitEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id}")
   public Response deleteById(@PathParam("id") String id)
   {
	  /* JacksonDBCollection<Produit, String> coll = JacksonDBCollection.wrap(dbcollection, Produit.class,
		       String.class);
	  // produit entity = coll.findAndRemove(DBQuery.is("id", id));
	   WriteResult<Produit, String> entity= coll.removeById(id);*/
	   JacksonDBCollection<Produit, String> coll= JacksonDBCollection.wrap(dbcollection, Produit.class, String.class);
	      DBUpdate.Builder builder= new DBUpdate.Builder();
	      builder.set("etat", "desactive");
	      Produit result = coll.findAndModify(DBQuery.is("_id",id), null, null, false, builder, false, false);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id}")
   @Produces("application/json")
   public Response findById(@PathParam("id") String id)
   {
	   String j = id+"";
	   JacksonDBCollection<Produit, String> coll = JacksonDBCollection.wrap(dbcollection, Produit.class,
		       String.class);
	   
	   Produit entity = coll.findOneById(j);
	   
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<Produit> listAll()
   {
	   JacksonDBCollection<Produit, String> coll= JacksonDBCollection.wrap(dbcollection, Produit.class, String.class);
	   DBCursor<Produit> results =coll.find(DBQuery.is("etat", "active"));
        List<Produit> arrayList = new ArrayList<Produit>();
	   while(results.hasNext()){
    	 Produit reponse= results.next(); 
    	 arrayList.add(reponse);
     } 
	   return arrayList;
   }

   @PUT
   @Path("/{id}")
   @Consumes("application/json")
   public Response update(Produit entity)
   {
	   JacksonDBCollection<Produit, String> coll= JacksonDBCollection.wrap(dbcollection, Produit.class, String.class);
	     // DBUpdate.Builder builder= new DBUpdate.Builder();
	      
	      Produit result=coll.save(entity).getSavedObject();
      return Response.noContent().build();
   }
   
  	/*@POST
   	@Path("/plannings")
   	@Consumes("multipart/form-data")
   	public Response tripUpload(MultipartFormDataInput dataInput) {

   		Map<String, List<InputPart>> uploadForm = dataInput.getFormDataMap();
   		List<InputPart> inputParts = uploadForm.get("plannings");

   		for (InputPart inputPart : inputParts) {
   			try {

   				InputStream inputStream = inputPart.getBody(InputStream.class,null);
   				System.out.println("Done");
   				
   			} catch (IOException e) {
   				e.printStackTrace();
   				return Response.serverError().build();
   			}
   		}
   		return Response.ok().build();
   	}*/
 }