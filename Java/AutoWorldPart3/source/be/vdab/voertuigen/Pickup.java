package be.vdab.voertuigen;

import java.awt.Color;

import be.vdab.util.Datum;
import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;

public class Pickup extends Personenwagen implements Laadbaar {
	private Volume volume;
	public Pickup(Volume volume, Color kleur, String merk, Datum DEKIN, int aankoopprijs,int zitplaatsen, Mens... args) {
		super(kleur, merk, DEKIN, aankoopprijs, zitplaatsen, args);
		this.volume= volume;
	}
	@Override
	public void setLaadvolume(Volume volume) {
		this.volume=volume;
	}

	@Override
	public Volume getLaadvolume() {
		return volume;
	}
	
	public String toString(){
		return super.toString()+" "+volume;
	}


}
