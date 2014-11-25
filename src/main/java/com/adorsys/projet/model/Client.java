package com.adorsys.projet.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;


@XmlRootElement
public class Client implements Serializable
{

   private int version = 0;

   private String nomclient;

   private String prenomclient;

   private Region region;

   private String ville;

   private String email;
   
   private String etat="active";
   
   
public Commandes idcommandes;

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
         return id.equals(((Client) that).id);
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

   public String getNomclient()
   {
      return this.nomclient;
   }

   public void setNomclient(final String nomclient)
   {
      this.nomclient = nomclient;
   }

   public String getPrenomclient()
   {
      return this.prenomclient;
   }

   public void setPrenomclient(final String prenomclient)
   {
      this.prenomclient = prenomclient;
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
   public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (nomclient != null && !nomclient.trim().isEmpty())
         result += "nomclient: " + nomclient;
      if (prenomclient != null && !prenomclient.trim().isEmpty())
         result += ", prenomclient: " + prenomclient;
      if (ville != null && !ville.trim().isEmpty())
         result += ", ville: " + ville;
      if (email != null && !email.trim().isEmpty())
         result += ", email: " + email;
      return result;
   }
}