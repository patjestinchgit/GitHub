/*
 * DEKIN= 		Datum Eerste Keer In Gebruik Name
 * LI=    		Lijst Inzittenden
 * VRijbewijs=	Vereiste rijbewijs	
 * VRlijst= 	Vereiste rijbewijs lijst
 */
package be.vdab.voertuigen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.div.DIV;
import be.vdab.voertuigen.div.Nummerplaat;

public abstract class Voertuig implements Comparable<Voertuig>, Serializable {
	private final Nummerplaat nummerplaat;
	private String merk;
	private Datum DEKIN;
	private int aankoopprijs;
	private final int zitplaatsen;
	private Mens bestuurder;
	private List<Mens> LI= new ArrayList<Mens>();
	private List<Rijbewijs> VRlijst=new ArrayList<Rijbewijs>();
	
	
	public Voertuig(String merk, Datum DEKIN, int aankoopprijs, int zitplaatsen, Mens...args){
		VRlijst.add(Rijbewijs.B);
		//VRlijst.add(Rijbewijs.BE);
		nummerplaat= DIV.getInstance().getNummerplaat();
		this.zitplaatsen=zitplaatsen;
		this.DEKIN=DEKIN;
		this.aankoopprijs=aankoopprijs;
		this.merk=merk;
		
		for(Mens mens: args){
			if((!LI.contains(mens))&&(LI.size()+1<=zitplaatsen)) LI.add(mens);
			//Lijncode zal nooit gebruikt worden doordat men enkel toevoegt als er nog plaats is.
			else if(LI.size()+1>zitplaatsen) throw new MensException("Te veel inzittenden");
		}
		if(LI.size()<1) throw new IllegalArgumentException("Geen inzittenden");
		setBestuurder(LI.get(0));
	}
	public void setBestuurder(Mens mens) {
		// TODO Auto-generated method stub
		if(mens.getRijbewijslijst().containsAll(VRlijst)) bestuurder= mens;
		else throw new MensException("Bestuurder "+mens.getNaam()+ " heeft geen gelidge rijbewijs(zen) voor dit voertuig. En heeft als gevolg er geen bestuurder aangewezen is.");
	}
	
	public void setNieuweInzittende(Mens mens, boolean nieuwebestuurder){
		//Voor als er een nieuwe bestuurder wordt aangewezen
		//controleren of er wel plaats is
		if(LI.size()+1>zitplaatsen) throw new IllegalArgumentException("Er kan geen inzittende bijgeplaatst worden");
		else if(LI.contains(mens))  throw new MensException("De inzittende is al aan boord");
		else if(nieuwebestuurder==false) LI.add(mens);
		else{
			LI.set(0, mens);
			setBestuurder(mens);
		}
	}
	
	
	
	//Alle Getters
	public Nummerplaat getNummerplaat() {
		return nummerplaat;
	}
	
	public String getMerk() {
		return merk;
	}
	
	public Datum getDEKIN() {
		return DEKIN;
	}
	
	public int getAankoopprijs() {
		return aankoopprijs;
	}
	
	public int getZitplaatsen() {
		return zitplaatsen;
	}
	
	public Mens getBestuurder() {
		return bestuurder;
	}
	
	public List<Mens> getLI() {
		return LI;
	}
	
	public List<Rijbewijs> getVRlijst() {
		return VRlijst;
	}
	
	public boolean isInzittend(Mens mens){
		return LI.contains(mens);
	}
	//Alle setters
	public void setMerk(String merk) {
		this.merk = merk;
	}
	
	public void setDEKIN(Datum dEKIN) {
		DEKIN = dEKIN;
	}
	
	public void setAankoopprijs(int aankoopprijs) {
		this.aankoopprijs = aankoopprijs;
	}
	
	public void setVRlijst(List<Rijbewijs> vRlijst) {
		VRlijst = vRlijst;
	}
	
	@Override
	public String toString() {
		if(LI.isEmpty()) return nummerplaat.toString()+" "+merk+" "+DEKIN.toString()+ " "+aankoopprijs+" "+bestuurder;
		// nummerplaat.toString()+" "+merk+" "+DEKIN.toString()+ " "+aankoopprijs+" "+bestuurder+ " "+LI
		else return /*nummerplaat.toString()"+"*/ merk+" "+DEKIN.toString()+ " "+aankoopprijs+" "+ bestuurder + " "+LI;
	}
	
//Methods
	public int compareTo(Voertuig vt) {
        return nummerplaat.compareTo(vt.getNummerplaat());
    }

    public int compareTo(String merk) {
        return this.merk.compareTo(merk);
    }
    
    
    public static MerkComperator getMerkComparator() {
        return new MerkComperator();
    }

    public static AankoopprijsComperator getAankoopprijsComparator() {
        return new AankoopprijsComperator();
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DEKIN == null) ? 0 : DEKIN.hashCode());
		result = prime * result + ((LI == null) ? 0 : LI.hashCode());
		result = prime * result + ((VRlijst == null) ? 0 : VRlijst.hashCode());
		result = prime * result + aankoopprijs;
		result = prime * result+ ((bestuurder == null) ? 0 : bestuurder.hashCode());
		result = prime * result + ((merk == null) ? 0 : merk.hashCode());
		result = prime * result+ ((nummerplaat == null) ? 0 : nummerplaat.hashCode());
		result = prime * result + zitplaatsen;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		boolean result= true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voertuig other = (Voertuig) obj;
		if((nummerplaat!=other.nummerplaat)||(!nummerplaat.equals(other.nummerplaat))) result=false;
		return result;
	}

	static class MerkComperator implements Comparator<Voertuig>, Serializable {

        @Override
        public int compare(Voertuig vt1, Voertuig vt2) {
            return vt1.getMerk().compareTo(vt2.getMerk());
        }
    }

    static class AankoopprijsComperator implements Comparator<Voertuig>, Serializable {

        @Override
        public int compare(Voertuig vt1, Voertuig vt2) {
            if (vt1.getAankoopprijs() == vt2.getAankoopprijs()) {
                return 0;
            } else if (vt1.getAankoopprijs() > vt2.getAankoopprijs()) {
                return -1;
            } else {
                return 1;
            }
        }
    }


	
	
	
	
}
