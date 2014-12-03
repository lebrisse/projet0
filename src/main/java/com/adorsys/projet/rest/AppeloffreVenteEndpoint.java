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

import com.adorsys.projet.model.AppeloffreVente;
import com.adorsys.projet.util.DbCollectionUtil;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * 
 */
@Stateless
@Path("/appeloffreVentes")
public class AppeloffreVenteEndpoint
{
	private DB db  = DbCollectionUtil.getdb();
	DBCollection dbcollection = db.getCollection("appeloffreVente");

	@POST
	@Consumes("application/json")
	public Response create(AppeloffreVente entity)
	{
		JacksonDBCollection<AppeloffreVente, String> coll = JacksonDBCollection.wrap(dbcollection, AppeloffreVente.class,
				String.class);
		WriteResult<AppeloffreVente, String> result = coll.insert(entity);
		entity = result.getSavedObject();

		return Response.created(UriBuilder.fromResource(AppeloffreVenteEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteById(@PathParam("id") String id)
	{
		/* JacksonDBCollection<Client, String> coll = JacksonDBCollection.wrap(dbcollection, Client.class,
		       String.class);
	  // Auteur entity = coll.findAndRemove(DBQuery.is("id", id));
	   WriteResult<Client, String> entity= coll.removeById(id);*/

		JacksonDBCollection<AppeloffreVente, String> coll= JacksonDBCollection.wrap(dbcollection, AppeloffreVente.class, String.class);
		DBUpdate.Builder builder= new DBUpdate.Builder();
		builder.set("etat", "desactive");
		AppeloffreVente result = coll.findAndModify(DBQuery.is("_id",id), null, null, false, builder, false, false);
		//  Client result=coll.save(entity).getSavedObject();

		return Response.noContent().build();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response findById(@PathParam("id") String id)
	{
		String j = id+"";
		JacksonDBCollection<AppeloffreVente, String> coll = JacksonDBCollection.wrap(dbcollection, AppeloffreVente.class,
				String.class);

		AppeloffreVente entity = coll.findOneById(j);

		return Response.ok(entity).build();
	}

	@GET
	@Produces("application/json")
	public List<AppeloffreVente> listAll()
	{
		JacksonDBCollection<AppeloffreVente, String> coll= JacksonDBCollection.wrap(dbcollection, AppeloffreVente.class, String.class);
		DBCursor<AppeloffreVente> results =coll.find(DBQuery.is("etat", "active"));
		List<AppeloffreVente> arrayList = new ArrayList<AppeloffreVente>();
		while(results.hasNext()){
			AppeloffreVente reponse= results.next(); 
			arrayList.add(reponse);
		}
		return arrayList;
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public Response update(AppeloffreVente entity)
	{
		JacksonDBCollection<AppeloffreVente, String> coll= JacksonDBCollection.wrap(dbcollection, AppeloffreVente.class, String.class);
		// DBUpdate.Builder builder= new DBUpdate.Builder();

		AppeloffreVente result=coll.save(entity).getSavedObject();
		return Response.noContent().build();
	}
}