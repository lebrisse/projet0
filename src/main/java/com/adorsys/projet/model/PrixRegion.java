package com.adorsys.projet.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class PrixRegion implements Serializable{
	
	private Region region;
	
	private String nomproduit;
	
	private List<Produit> produit= new ArrayList<Produit>();
	
	private double prixMoyen =0;

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
	         return id.equals(((PrixRegion) that).id);
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
	
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
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

	
	@Override
	
	public String toString()
	   {
	      String result = getClass().getSimpleName() + " ";
	         result += ", PrixMoyen: " + nomproduit;
	      return result;
	   }

	public List<Produit> getProduit() {
		return produit;
	}

	public void setProduit(List<Produit> produit) {
		this.produit = produit;
	}
	
	

}
