package com.adorsys.projet.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class Producteur implements Serializable
{

   private int version = 0;
   
   private String nomproducteur;
  
   private String prenomproducteur;

   private Region region;

   private String ville;
   
   private Pays pays;
   
   private String localite;
   
   private Typeproducteur type_producteur;
   
   private Type_exploitation type_exploitation;

   private String email;
   
   private String status;

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
         return id.equals(((Producteur) that).id);
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

   public String getNomproducteur()
   {
      return this.nomproducteur;
   }

   public void setNomproducteur(final String nomproducteur)
   {
      this.nomproducteur = nomproducteur;
   }

   public String getPrenomproducteur()
   {
      return this.prenomproducteur;
   }

   public void setPrenomproducteur(final String prenomproducteur)
   {
      this.prenomproducteur = prenomproducteur;
   }

   public Region getRegion()
   {
      return this.region;
   }

   public void setRegion(final Region region)
   {
      this.region = region;
   }

   public String getVille()
   {
      return this.ville;
   }

   public void setVille(final String ville)
   {
      this.ville = ville;
   }

   public String getEmail()
   {
      return this.email;
   }

   public void setEmail(final String email)
   {
      this.email = email;
   }
   
   public Pays getPays() {
	return pays;
}
public void setPays(Pays pays) {
	this.pays = pays;
}
public String getLocalite() {
	return localite;
}
public void setLocalite(String localite) {
	this.localite = localite;
}
public Typeproducteur getType_producteur() {
	return type_producteur;
}
public void setType_producteur(Typeproducteur type_producteur) {
	this.type_producteur = type_producteur;
}
public Type_exploitation getType_exploitation() {
	return type_exploitation;
}
public void setType_exploitation(Type_exploitation type_exploitation) {
	this.type_exploitation = type_exploitation;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (nomproducteur != null && !nomproducteur.trim().isEmpty())
         result += "nomproducteur: " + nomproducteur;
      if (prenomproducteur != null && !prenomproducteur.trim().isEmpty())
         result += ", prenomproducteur: " + prenomproducteur;
      if (ville != null && !ville.trim().isEmpty())
         result += ", ville: " + ville;
      if (email != null && !email.trim().isEmpty())
         result += ", email: " + email;
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