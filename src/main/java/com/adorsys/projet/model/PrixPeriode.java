package com.adorsys.projet.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrixPeriode {
	
	private String date1;
	
	private String date2;
	
private String nomproduit;

private List<Produit> produit= new ArrayList<Produit>();
	
	private double prixMoyen;

	private String id;
	 
	@ObjectId
	  @JsonProperty("_id")
	  public String getId() {
	    return id;
	  }
	  @ObjectId
	  @JsonProperty("_id")
	  public void setId(String id) {
	    this.id = id;
	  }
	  @Override
		 public boolean equals(Object that)
		   {
		      if (this == that)
		      {
		         return true;
		      }
		      if (that == null)
		      {
		         return false;
		      }
		      if (getClass() != that.getClass())
		      {
		         return false;
		      }
		      if (id != null)
		      {
		         return id.equals(((PrixPeriode) that).id);
		      }
		      return super.equals(that);
		   }
		@Override
		  public int hashCode()
		   {
		      if (id != null)
		      {
		         return id.hashCode();
		      }
		      return super.hashCode();
		   }
	
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getNomproduit() {
		return nomproduit;
	}
	public void setNomproduit(String nomproduit) {
		this.nomproduit = nomproduit;
	}
	public double getPrixMoyen() {
		return prixMoyen;
	}
	public void setPrixMoyen(double prixMoyen) {
		this.prixMoyen = prixMoyen;
	}
	public List<Produit> getProduit() {
		return produit;
	}
	public void setProduit(List<Produit> produit) {
		this.produit = produit;
	}
}
