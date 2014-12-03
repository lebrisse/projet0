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

import com.adorsys.projet.model.AppeloffreAchat;
import com.adorsys.projet.util.DbCollectionUtil;
import com.mongodb.DB;
import com.mongodb.DBCollection;


@Stateless
@Path("/appeloffreAchats")
public class AppeloffreAchatEndpoint
{
	private DB db  = DbCollectionUtil.getdb();
	DBCollection dbcollection = db.getCollection("appeloffreAchat");

	@POST
	@Consumes("application/json")
	public Response create(AppeloffreAchat entity)
	{
		JacksonDBCollection<AppeloffreAchat, String> coll = JacksonDBCollection.wrap(dbcollection, AppeloffreAchat.class,
				String.class);
		WriteResult<AppeloffreAchat, String> result = coll.insert(entity);
		entity = result.getSavedObject();

		return Response.created(UriBuilder.fromResource(AppeloffreAchatEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteById(@PathParam("id") String id)
	{

		JacksonDBCollection<AppeloffreAchat, String> coll= JacksonDBCollection.wrap(dbcollection, AppeloffreAchat.class, String.class);
		DBUpdate.Builder builder= new DBUpdate.Builder();
		builder.set("etat", "desactive");
		AppeloffreAchat result = coll.findAndModify(DBQuery.is("_id",id), null, null, false, builder, false, false);
		//  Client result=coll.save(entity).getSavedObject();

		return Response.noContent().build();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response findById(@PathParam("id") String id)
	{
		String j = id+"";
		JacksonDBCollection<AppeloffreAchat, String> coll = JacksonDBCollection.wrap(dbcollection, AppeloffreAchat.class,
				String.class);

		AppeloffreAchat entity = coll.findOneById(j);

		return Response.ok(entity).build();
	}

	@GET
	@Produces("application/json")
	public List<AppeloffreAchat> listAll()
	{
		JacksonDBCollection<AppeloffreAchat, String> coll= JacksonDBCollection.wrap(dbcollection, AppeloffreAchat.class, String.class);
		DBCursor<AppeloffreAchat> results =coll.find(DBQuery.is("etat", "active"));
		List<AppeloffreAchat> arrayList = new ArrayList<AppeloffreAchat>();
		while(results.hasNext()){
			AppeloffreAchat reponse= results.next(); 
			arrayList.add(reponse);
		}
		return arrayList;
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public Response update(AppeloffreAchat entity)
	{
		JacksonDBCollection<AppeloffreAchat, String> coll= JacksonDBCollection.wrap(dbcollection, AppeloffreAchat.class, String.class);
		// DBUpdate.Builder builder= new DBUpdate.Builder();

		AppeloffreAchat result=coll.save(entity).getSavedObject();
		return Response.noContent().build();
	}
}