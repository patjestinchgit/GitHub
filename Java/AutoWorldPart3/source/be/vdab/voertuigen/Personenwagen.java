package be.vdab.voertuigen;

import java.awt.Color;
import java.io.Serializable;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;

public class Personenwagen extends Voertuig implements Serializable  {
	private Color kleur;
	public Personenwagen(Color kleur,String merk, Datum DEKIN, int aankoopprijs,int zitplaatsen, Mens... args) {
		super(merk, DEKIN, aankoopprijs, zitplaatsen, args);
		// TODO Auto-generated constructor stub
		this.kleur= kleur;
		if(zitplaatsen>8) throw new IllegalArgumentException("Er kunnen maar 8 zitplaatsen zijn in een personenwagen");
	}
	
	public String toString(){
		return super.toString()+ " Kleur "+kleur;
	}
	
	public Color getKleur() {
		return kleur;
	}
	public void setKleur(Color kleur) {
		this.kleur = kleur;
	}
	
}
