package be.vdab.util;

import java.io.Serializable;
import java.util.Objects;

public final class Volume implements Comparable<Volume>, Serializable {
    private final int hoogte, breedte, diepte;
    private final Maat maat;

    public Volume(int hoogte, int breedte, int diepte, Maat maat) {
        try{
        	if((hoogte < 0 || breedte <0 || diepte < 0)) throw new VolumeException("Negatieve waarden worden niet aanvaard.");
        }catch(VolumeException e){
        	System.out.println(e.getMessage());
        }
    	
        this.hoogte = hoogte;
        this.breedte = breedte;
        this.diepte = diepte;
        this.maat = maat;
    }

    public int getHoogte() {
        return hoogte;
    }

    public int getBreedte() {
        return breedte;
    }

    public int getDiepte() {
        return diepte;
    }

    public Maat getMaat() {
        return maat;
    }

    public long getVolume() {
        int volume = hoogte * breedte * diepte;
        switch (maat) {
            case centimeter:
                break;
            case decimeter:
                volume *= 1000;
                break;
            case meter:
                volume *= 1000000;
                break;
        }
        return volume;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + breedte;
		result = prime * result + diepte;
		result = prime * result + hoogte;
		result = prime * result + ((maat == null) ? 0 : maat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result= true;
		if ((obj == null)||(getClass() != obj.getClass())) result=false;
		Volume other = (Volume) obj;
		if((breedte != other.breedte)||(diepte != other.diepte)||(hoogte != other.hoogte)||(maat != other.maat)) result=false;
		return result;
	}
	
	public String toString() {
		return hoogte+"(h)x"+breedte+"(b)x"+diepte+"(d) "+maat;
	}

	@Override
	public int compareTo(Volume volume) {
		int result=1;
		if (this.getVolume() == volume.getVolume()) return 0; 
		else if (getVolume() < volume.getVolume())  return -1;
		return result;
	}

	
}

