package com.adorsys.projet.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppeloffreAchat11 implements Serializable{

	private int version = 0;

	private String nomAppeloffre;
	private String responsable;

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
			return id.equals(((AppeloffreAchat11) that).id);
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

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getNomAppeloffre() {
		return nomAppeloffre;
	}

	public void setNomAppeloffre(String nomAppeloffre) {
		this.nomAppeloffre = nomAppeloffre;
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

	public long getPrixproposer() {
		return prixproposer;
	}

	public void setPrixproposer(long prixproposer) {
		this.prixproposer = prixproposer;
	}

	public long getQuantite() {
		return quantite;
	}

	public void setQuantite(long quantite) {
		this.quantite = quantite;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (nomAppeloffre != null && !nomAppeloffre.trim().isEmpty())
			result += "nomAppeloffre: " + nomAppeloffre;
		if (responsable != null && !responsable.trim().isEmpty())
			result += ", Responsable: " + responsable;
		result += ", quantite: " + quantite;
	
		return result;
	}   

}
