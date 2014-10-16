package com.adorsys.projet.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.adorsys.projet.model.PrixPeriode;
import com.adorsys.projet.model.Produit;
import com.adorsys.projet.util.DbCollectionUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

@Stateless
@Path("/prixPeriodes")
public class PrixPeriodeEndpoint {

	private DB db = DbCollectionUtil.getdb();
	DBCollection dbcollection = db.getCollection("produit");

	@GET
	@Produces("application/json")
	@Path("/searchByPeriode")
	//public Response findById(@PathParam("id") String id) {
	
	 public PrixPeriode findByPeriode(@QueryParam("nomproduit")String nomproduit,
			 @QueryParam("date1")String date1, @QueryParam("date2")String date2) throws ParseException
	{
		System.out.println("date1"+date1);
		System.out.println(date2);
		   SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		  Date date3 = sdf2.parse(date1);
		  Date date4 = sdf2.parse(date2);  
		JacksonDBCollection<Produit, String> coll = JacksonDBCollection.wrap(
				dbcollection, Produit.class, String.class);
		System.out.println("inter");
		BasicDBObject allQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();

		/*obj.add(new BasicDBObject("nomproduit", nomproduit));
		System.out.println(obj);
		obj.add(new BasicDBObject("date", new BasicDBObject("$gt",date4).append("$lt", date3)));
		System.out.println(obj);
		allQuery.put("$and", obj);*/
		obj.add(new BasicDBObject("nomproduit", nomproduit));
		System.out.println(obj);
		obj.add(new BasicDBObject("date", new BasicDBObject("$lte",date4).append("$gte", date3)));
		//System.out.println(obj);
		allQuery.put("$and", obj);
		System.out.println(allQuery.toString());
		DBCursor<Produit> cursor = coll.find(allQuery);
		System.out.println("midle");
		List<Produit> array = new ArrayList<Produit>();
		while (cursor.hasNext()) {
			// System.out.println(cursor.next());
			Produit reponse = cursor.next();
			array.add(reponse);
		}
		PrixPeriode prixp= new PrixPeriode();
		prixp.setProduit(array);
		double prixmoyen = prixmoyenP(array);
		prixp.setPrixMoyen(prixmoyen);
		System.out.println("end");
		System.out.println(prixp);

		return prixp;
	}

	private double prixmoyenP(List<Produit> arrayList) {
		int divp = arrayList.size();
		double totalprix = 0;
		for (Produit produits : arrayList) {
			totalprix = totalprix + produits.getPrix();
		}
		if (divp == 0)
			return 0;
		return totalprix / divp;
	}

	@GET
	@Produces("application/json")
	public List<Produit> listAll() {
		JacksonDBCollection<Produit, String> coll = JacksonDBCollection.wrap(
				dbcollection, Produit.class, String.class);
		DBCursor<Produit> results = coll.find();
		List<Produit> arrayList = new ArrayList<Produit>();
		while (results.hasNext()) {
			Produit reponse = results.next();
			arrayList.add(reponse);
		}

		return arrayList;
	}

}

/*
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

  Date dateObj = sdf.parse("09/02/2014");
   SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
   sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
  Date dateObj2 = sdf2.parse("09/03/2014");
   */
//	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//DateTimeFormatter parser2 = ISODateTimeFormat.dateTime();
//DateTime date3 = parser2.parseDateTime(date1);
//DateTime date4 = parser2.parseDateTime(date2);

//	 DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
// String string1 = date1;
//Date date3 = df1.parse(string1);
//	 Date date3 = df1.parse(date1);
//Date date4 = df1.parse(date1);

/* DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
String string2 = "2001-07-04T12:08:56.235-07:00";
Date result2 = df2.parse(string2)*/

