package com.adorsys.projet.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppeloffreAchat implements Serializable{

	private int version = 0;

	private String nomAppeloffre;
	private String Responsable;

	@Temporal(TemporalType.TIMESTAMP)

	private Date Datedebut;
	private Date Datefin;
	private String etat="active";
	private long prixproposer;

	private Set<Producteur> nomproducteur = new HashSet<Producteur>();

	//   private String produit;
	private long quantite;

	private Set<Commandes> nomcommande = new HashSet<Commandes>();

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
			return id.equals(((AppeloffreAchat) that).id);
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


	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getNomAppeloffre() {
		return nomAppeloffre;
	}

	public void setNomAppeloffre(String nomAppeloffre) {
		this.nomAppeloffre = nomAppeloffre;
	}

	public String getResponsable() {
		return Responsable;
	}

	public void setResponsable(String responsable) {
		Responsable = responsable;
	}

	public Date getDatedebut() {
		return Datedebut;
	}

	public void setDatedebut(Date datedebut) {
		Datedebut = datedebut;
	}

	public Date getDatefin() {
		return Datefin;
	}

	public void setDatefin(Date datefin) {
		Datefin = datefin;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Set<Producteur> getNomproducteur() {
		return nomproducteur;
	}

	public void setNomproducteur(Set<Producteur> nomproducteur) {
		this.nomproducteur = nomproducteur;
	}

	public Set<Commandes> getNomcommande() {
		return nomcommande;
	}

	public void setNomcommande(Set<Commandes> nomcommande) {
		this.nomcommande = nomcommande;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (nomAppeloffre != null && !nomAppeloffre.trim().isEmpty())
			result += "nomAppeloffre: " + nomAppeloffre;
		if (Responsable != null && !Responsable.trim().isEmpty())
			result += ", Responsable: " + Responsable;
		if (nomcommande != null && !Responsable.trim().isEmpty())
			result += ", Responsable: " + Responsable;
		result += ", quantite: " + quantite;
	
		return result;
	}   

}
