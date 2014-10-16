package com.adorsys.projet.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class Produit implements Serializable {

	private int version = 0;

	private String nomproduit;

	private Set<Producteur> nomproducteur = new HashSet<Producteur>();

	private String qualite;

	private Region region;

	private String ville;

	private int quantite;
	
	private File imageproduit;

	private int prix;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public Date getDate() {
		return date;
	}

	public File getImageproduit() {
		return imageproduit;
	}

	public void setImageproduit(File imageproduit) {
		this.imageproduit = imageproduit;
	}

	public void setDate(Date date) {
		this.date = date;
	}

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

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		if (id != null) {
			return id.equals(((Produit) that).id);
		}
		return super.equals(that);
	}

	@Override
	public int hashCode() {
		if (id != null) {
			return id.hashCode();
		}
		return super.hashCode();
	}

	public String getNomproduit() {
		return this.nomproduit;
	}

	public void setNomproduit(final String nomproduit) {
		this.nomproduit = nomproduit;
	}

	public String getQualite() {
		return this.qualite;
	}

	public void setQualite(final String qualite) {
		this.qualite = qualite;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(final Region region) {
		this.region = region;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(final String ville) {
		this.ville = ville;
	}

	public int getQuantite() {
		return this.quantite;
	}

	public void setQuantite(final int quantite) {
		this.quantite = quantite;
	}

	public int getPrix() {
		return this.prix;
	}

	public void setPrix(final int prix) {
		this.prix = prix;
	}

	public Set<Producteur> getNomproducteur() {
		return nomproducteur;
	}

	public void setNomproducteur(Set<Producteur> nomproducteur) {
		this.nomproducteur = nomproducteur;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (nomproduit != null && !nomproduit.trim().isEmpty())
			result += "nomproduit: " + nomproduit;
		if (qualite != null && !qualite.trim().isEmpty())
			result += ", qualite: " + qualite;
		if (ville != null && !ville.trim().isEmpty())
			result += ", ville: " + ville;
		result += ", quantite: " + quantite;
		result += ", prix: " + prix;
		return result;
	}
}