package be.vdab.schoolgerief;

import java.awt.Color;
import java.io.Serializable;

import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;

public class Boekentas implements Laadbaar, Serializable{
	String kleur;
	Volume laadvolume;
	public Boekentas(Volume laadvolume, String kleur){
		this.laadvolume=laadvolume;
		this.kleur= kleur;
		if((laadvolume==null)||(kleur==null)) throw new IllegalArgumentException("Laadvolume en/of kleur zijn/is null");
		
	}
	@Override
	public Volume getLaadvolume() {
		// TODO Auto-generated method stub
		return laadvolume;
	}

	public String getKleur() {
		return kleur;
	}
	public void setKleur(String kleur) {
		this.kleur = kleur;
	}
	@Override
	public void setLaadvolume(Volume volume) {
		// TODO Auto-generated method stub
		this.laadvolume=volume;
		
	}
	@Override
	public String toString() {
		return "Boekentas [kleur=" + kleur + ", laadvolume=" + laadvolume + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kleur == null) ? 0 : kleur.hashCode());
		result = prime * result
				+ ((laadvolume == null) ? 0 : laadvolume.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		boolean result=true;
		if ((obj == null)||(getClass() != obj.getClass())) result = false;
		Boekentas other = (Boekentas) obj;
		if ((!kleur.equals(other.kleur)||(!laadvolume.equals(other.laadvolume))))	result=false;
		return result;
	}
	
	
}
