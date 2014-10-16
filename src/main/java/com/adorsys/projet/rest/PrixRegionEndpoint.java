package com.adorsys.projet.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import com.adorsys.projet.model.PrixRegion;
import com.adorsys.projet.model.Produit;
import com.adorsys.projet.model.Region;
import com.adorsys.projet.util.DbCollectionUtil;
import com.mongodb.DB;
import com.mongodb.DBCollection;

@Stateless
@Path("/prixRegions")
public class PrixRegionEndpoint {

	private DB db = DbCollectionUtil.getdb();
	DBCollection dbcollection = db.getCollection("produit");

	/* @GET
	   @Path("/{id}")
	   @Produces("application/json")
	   public Response findById(@PathParam("id") String id)
	   {
			JacksonDBCollection<Produit, String> coll = JacksonDBCollection.wrap(
					dbcollection, Produit.class, String.class);
			System.out.println("inter");
			PrixRegion prixRegion=new PrixRegion();
			DBCursor<Produit> results = coll.find(DBQuery.and(
					DBQuery.is("region", prixRegion.getRegion()),
					DBQuery.is("nomproduit", prixRegion.getNomProduit())));
			System.out.println("midle");
			ArrayList<Produit> arrayList = new ArrayList<Produit>();
			while (results.hasNext()) {
				Produit reponse = results.next();
				arrayList.add(reponse);
			}
			
			prixRegion.setProduit(arrayList);
			double prixMoyen = prixMoyen(arrayList);
			System.out.println("end");
			prixRegion.setPrixMoyen(prixMoyen);
			System.out.println(prixRegion);
	      return Response.ok(prixRegion).build();
	   }
*/
	   @GET
	   @Produces("application/json")
	   public List<Produit> listAll()
	   {
		   JacksonDBCollection<Produit, String> coll= JacksonDBCollection.wrap(dbcollection, Produit.class, String.class);
		   DBCursor<Produit> results =coll.find();
	        List<Produit> arrayList = new ArrayList<Produit>();
		   while(results.hasNext()){
	    	 Produit reponse= results.next(); 
	    	 arrayList.add(reponse);
	     }
		   
		   return arrayList;
	   }

	@GET
	@Produces("application/json")
	@Path("/searchByRegion")
	
	//public PrixRegion findByRegion(PrixRegion prixRegion) {
	
	public PrixRegion list(@QueryParam("region")Region region,@QueryParam("nomproduit")String nomproduit){
	
		JacksonDBCollection<Produit, String> coll = JacksonDBCollection.wrap(
				dbcollection, Produit.class, String.class);
		System.out.println("inter");
		
		
		DBCursor<Produit> results = coll.find(DBQuery.and(
				DBQuery.is("region", region),
				DBQuery.is("nomproduit", nomproduit)));
		System.out.println("midle");
		List<Produit> arrayList = new ArrayList<Produit>();
		while (results.hasNext()) {
			Produit reponse = results.next();
			arrayList.add(reponse);
		}
		PrixRegion prixRegion = new PrixRegion();
		prixRegion.setProduit(arrayList);
		double prixMoyen = prixMoyen(arrayList);
		System.out.println("end");
		prixRegion.setPrixMoyen(prixMoyen);
		
		return  prixRegion;
	}

	private double prixMoyen(List<Produit> arrayList) {
		int div = arrayList.size();
		double totalPrix = 0;
		for (Produit produits : arrayList) {
			totalPrix = totalPrix + produits.getPrix();
		}
		if (div == 0){
			return 0;}
		return (totalPrix / div);
	}

}
