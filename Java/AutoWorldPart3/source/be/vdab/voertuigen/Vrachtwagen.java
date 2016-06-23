/*
 * MTM= 	Maximaal Toegelaten Massa
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;

public class Vrachtwagen extends Voertuig implements Laadbaar {
	private Volume laadvolume; 
	private int MTM;
	private int aantalassen;
	public Vrachtwagen(Volume laadvolume, int MTM, int aantalassen, String merk, Datum DEKIN, int aankoopprijs,int zitplaatsen, Mens...args) {
		super(merk, DEKIN, aankoopprijs, zitplaatsen, args);
		// TODO Auto-generated constructor stub
		this.laadvolume=laadvolume;
		this.MTM= MTM;
		this.aantalassen=aantalassen;
		if(zitplaatsen>3) throw new IllegalArgumentException("Er zijn maar 3 plaatsen in een vrachtwagen");
	}

	@Override
	public Volume getLaadvolume() {
		// TODO Auto-generated method stub
		return laadvolume;
	}

	@Override
	public void setLaadvolume(Volume volume) {
		// TODO Auto-generated method stub
		this.laadvolume=volume;
	}

	public int getMTM() {
		return MTM;
	}

	public int getAantalassen() {
		return aantalassen;
	}

	public void setMTM(int MTM) {
		this.MTM = MTM;
	}

	public void setAantalassen(int aantalassen) {
		this.aantalassen = aantalassen;
	}
	
	public String toString() {
		return super.toString()+" assen:"+aantalassen+", maximaal toegelaten massa:"+MTM+", laadvolume:"+laadvolume;
	}
	
}
