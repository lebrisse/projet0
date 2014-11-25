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
import org.mongojack.DBQuery;
import org.mongojack.DBUpdate;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import com.adorsys.projet.model.Client;
import com.adorsys.projet.model.Commandes;
import com.adorsys.projet.util.DbCollectionUtil;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * 
 */
@Stateless
@Path("/commandess")
public class CommandesEndpoint
{
	private DB db  = DbCollectionUtil.getdb();
	DBCollection dbcollection = db.getCollection("commandes");

	@POST
	@Consumes("application/json")
	public Response create(Commandes entity)
	{
		JacksonDBCollection<Commandes, String> coll = JacksonDBCollection.wrap(dbcollection, Commandes.class,
				String.class);
		WriteResult<Commandes, String> result = coll.insert(entity);
		entity = result.getSavedObject();

		return Response.created(UriBuilder.fromResource(CommandesEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteById(@PathParam("id") String id)
	{

		/*JacksonDBCollection<Commandes, String> coll = JacksonDBCollection.wrap(dbcollection, Commandes.class,
		       String.class);
	  // Auteur entity = coll.findAndRemove(DBQuery.is("id", id));
	   WriteResult<Commandes, String> entity= coll.removeById(id);*/

		JacksonDBCollection<Commandes, String> coll= JacksonDBCollection.wrap(dbcollection, Commandes.class, String.class);
		DBUpdate.Builder builder= new DBUpdate.Builder();
		builder.set("etat", "desactive");
		Commandes result = coll.findAndModify(DBQuery.is("_id",id), null, null, false, builder, false, false);

		return Response.noContent().build();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response findById(@PathParam("id") String id)
	{
		String j = id+"";
		JacksonDBCollection<Commandes, String> coll = JacksonDBCollection.wrap(dbcollection, Commandes.class,
				String.class);

		Commandes entity = coll.findOneById(j);

		return Response.ok(entity).build();
	}

	@GET
	@Produces("application/json")
	public List<Commandes> listAll()
	{
		JacksonDBCollection<Commandes, String> coll= JacksonDBCollection.wrap(dbcollection, Commandes.class, String.class);
		DBCursor<Commandes> results =coll.find(DBQuery.is("etat", "active"));
		List<Commandes> arrayList = new ArrayList<Commandes>();
		while(results.hasNext()){
			Commandes reponse= results.next(); 
			arrayList.add(reponse);
		}
		return arrayList; 
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(Commandes entity)
	{
		JacksonDBCollection<Commandes, String> coll= JacksonDBCollection.wrap(dbcollection, Commandes.class, String.class);
		// DBUpdate.Builder builder= new DBUpdate.Builder();

		Commandes result=coll.save(entity).getSavedObject();

		return Response.noContent().build();
	}
}