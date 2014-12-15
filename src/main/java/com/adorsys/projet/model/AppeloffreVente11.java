package com.adorsys.projet.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppeloffreVente11 implements Serializable{

	private int version = 0;

	private String nomAppeloffre;
	private String localite;
	private String ville;
	private Region region;
	private Pays pays;
	
	private String etat="active";
	private int prixvente;
	@Temporal(TemporalType.TIMESTAMP)

	private Date Datepub;
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
			return id.equals(((AppeloffreVente11) that).id);
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

	public int getPrixvente() {
		return prixvente;
	}

	public void setPrixvente(int prixvente) {
		this.prixvente = prixvente;
	}

	public long getQuantite() {
		return quantite;
	}

	public void setQuantite(long quantite) {
		this.quantite = quantite;
	}

	public String getLocalite() {
		return localite;
	}

	public void setLocalite(String localite) {
		this.localite = localite;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(final Region region) {
		this.region = region;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public Date getDatepub() {
		return Datepub;
	}

	public void setDatepub(Date datepub) {
		Datepub = datepub;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (nomAppeloffre != null && !nomAppeloffre.trim().isEmpty())
			result += "nomAppeloffre: " + nomAppeloffre;
		if (localite != null && !localite.trim().isEmpty())
			result += ", localite: " + localite;
		result += ", quantite: " + quantite;
	
		return result;
	}   

}
