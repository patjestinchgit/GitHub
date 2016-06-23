package be.vdab.util;

public enum Maat {
	centimeter("centimeter"), decimeter("decimeter"), meter("meter");
	String maat;
	Maat(String maat){ 
		this.maat=maat;
	}
	public String toString(){
		return maat;
	}
	public String getMaat() {
		return maat;
	}
}