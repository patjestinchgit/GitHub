package be.vdab.util.mens;

import be.vdab.voertuigen.div.DIV;

public enum Rijbewijs {
	A("A"), B("B"), BE("B+E"), C("C"), CE("C+E"), D("D"), DE("D+E");
	String soort;
	Rijbewijs(String soort){
		this.soort=soort;
	}
	public String toString(){
		return soort;
	}
}
