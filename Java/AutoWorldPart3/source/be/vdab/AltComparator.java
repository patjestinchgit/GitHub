package be.vdab;

import java.util.Comparator; 

import be.vdab.voertuigen.Voertuig;
public class AltComparator implements Comparator<Voertuig> { 
	@Override 
	public int compare(Voertuig obj1, Voertuig obj2) { 
		if (obj1.getAankoopprijs() == obj2.getAankoopprijs()) return 0; //return 1; wanneer wel dubbele punten zijn toegestaan 
		else if (obj1.getAankoopprijs() > obj2.getAankoopprijs()) return -1; 
		else return 1; 
	}
}
