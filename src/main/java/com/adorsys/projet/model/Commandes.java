package com.adorsys.projet.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class Commandes implements Serializable
{

	private int version = 0;
	private String nomcommande ="brice";
	private Set<Produit> nomproduit = new HashSet<Produit>();

	private Set<Client> nomclient = new HashSet<Client>();

	private long quantite;
	private long prixproposer=1500;

	private String etat="active";

	@Temporal(TemporalType.TIMESTAMP)
	private Date datedebut;
	//private Date datefin;

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
			return id.equals(((Commandes) that).id);
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

	public Set<Produit> getNomproduit()
	{
		return this.nomproduit;
	}

	public void setNomproduit(final Set<Produit> nomproduit)
	{
		this.nomproduit = nomproduit;
	}

	public Set<Client> getNomclient()
	{
		return this.nomclient;
	}

	public void setNomclient(final Set<Client> nomclient)
	{
		this.nomclient = nomclient;
	}

	public long getQuantite()
	{
		return this.quantite;
	}

	public void setQuantite(final long quantite)
	{
		this.quantite = quantite;
	}

	public String getNomcommande() {
		return nomcommande;
	}
	public void setNomcommande(String nomcommande) {
		this.nomcommande = nomcommande;
	}
	public long getPrixproposer() {
		return prixproposer;
	}
	public void setPrixproposer(long prixproposer) {
		this.prixproposer = prixproposer;
	}
	public Date getDatedebut() {
		return datedebut;
	}
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
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
		result += "quantite: " + quantite;
		return result;
	}
}