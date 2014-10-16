package com.adorsys.projet.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class CategorieProduit implements Serializable
{

  
   private int version = 0;

   @Column(length = 50)
   private String nomcategorie;

   @OneToMany
   private Set<Produit> produits = new HashSet<Produit>();

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

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
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
         return id.equals(((CategorieProduit) that).id);
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

   public String getNomcategorie()
   {
      return this.nomcategorie;
   }

   public void setNomcategorie(final String nomcategorie)
   {
      this.nomcategorie = nomcategorie;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (nomcategorie != null && !nomcategorie.trim().isEmpty())
         result += "nomcategorie: " + nomcategorie;
      return result;
   }

   public Set<Produit> getProduits()
   {
      return this.produits;
   }

   public void setProduits(final Set<Produit> produits)
   {
      this.produits = produits;
   }
}