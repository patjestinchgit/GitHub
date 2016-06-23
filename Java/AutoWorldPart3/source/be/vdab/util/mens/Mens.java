package be.vdab.util.mens;

import java.io.Serializable;
import java.util.ArrayList;

public class Mens implements Comparable<Mens>, Serializable  {
	private String naam;
	private ArrayList<Rijbewijs> rijbewijslijst;
	private Rijbewijs rijbewijs;
	public Mens(String naam,Rijbewijs... args){
		this.naam= naam;
		this.rijbewijslijst= new ArrayList<Rijbewijs>();
		for(Rijbewijs bewijs: args){
			if (!rijbewijslijst.contains(bewijs)) rijbewijslijst.add(bewijs);
		}
	}
	//Getters
	public String getNaam() {
		return naam;
	}
	public ArrayList<Rijbewijs> getRijbewijslijst() {
		return rijbewijslijst;
	}
	//CompareTo
	public int compareTo(Mens mens) {
		// TODO Auto-generated method stub
		return naam.compareTo(mens.getNaam());
	}
	//toString
	public String toString() {
		String returnstring=naam;
		if(rijbewijslijst.size()>0) {
			returnstring+="(";
			for(int i=0; i<rijbewijslijst.size();i++){
				returnstring+=rijbewijslijst.get(i).toString();
				if((i<rijbewijslijst.size()-1)) returnstring+=", ";
			}
			returnstring+=")";
		}
		else returnstring+= ": /";
		return returnstring;
	}
	//HashCode
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		result = prime * result+ ((rijbewijs == null) ? 0 : rijbewijs.hashCode());
		result = prime * result+ ((rijbewijslijst == null) ? 0 : rijbewijslijst.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result=true;
		Mens other = (Mens) obj;
		if (obj == null) result= false;
		if (getClass() != obj.getClass()) result= false;
		if ((!naam.equals(other.naam))||((rijbewijs != other.rijbewijs))||(!rijbewijslijst.equals(other.rijbewijslijst))) result= false;
		return result;
	}
}
