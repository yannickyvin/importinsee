package com.doubley.batch.communes.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity

public class Commune {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NonNull
	private Integer codeInsee;
	@NonNull
	private Integer codePostal;
	@NonNull
	private String nom;
	@NonNull
	private String departement;
	@NonNull
	private String region;
	private String statut;
	private String altitude;
	private Float superficie;
	private Float population;
	private String geo;
		
	public Commune() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Commune(Integer codeInsee, Integer codePostal, String nom, String departement, String region, String statut,
			String altitude, Float superficie, Float population, String geo) {
		super();
		this.codeInsee = codeInsee;
		this.codePostal = codePostal;
		this.nom = nom;
		this.departement = departement;
		this.region = region;
		this.statut = statut;
		this.altitude = altitude;
		this.superficie = superficie;
		this.population = population;
		this.geo = geo;
	}

	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Integer getCodeInsee() {
		return codeInsee;
	}
	public void setCodeInsee(Integer codeInsee) {
		this.codeInsee = codeInsee;
	}
	public Integer getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}
	
	
	
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getAltitude() {
		return altitude;
	}
	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}
	public Float getSuperficie() {
		return superficie;
	}
	public void setSuperficie(Float superficie) {
		this.superficie = superficie;
	}
	public Float getPopulation() {
		return population;
	}
	public void setPopulation(Float population) {
		this.population = population;
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}


	@Override
	public String toString() {
		return "Commune [codeInsee=" + codeInsee + ", codePostal=" + codePostal + ", nom=" + nom + ", departement="
				+ departement + ", region=" + region + ", statut=" + statut + ", altitude=" + altitude + ", superficie="
				+ superficie + ", population=" + population + ", geo=" + geo + "]";
	}
		
}
