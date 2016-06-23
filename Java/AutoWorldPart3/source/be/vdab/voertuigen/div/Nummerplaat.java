package be.vdab.voertuigen.div;

import java.io.Serializable;
//Comparable en Serializable
public class Nummerplaat implements Comparable<Nummerplaat>, Serializable {
	private String plaat;
	//Constructor
	Nummerplaat(String plaat){
		this.plaat=plaat;
	}
	//GetPlaat
	public String getPlaat() {
		return plaat;
	}
	//ToString
	@Override
	public String toString() {
		return plaat;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plaat == null) ? 0 : plaat.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Nummerplaat)) {
			return false;
		}
		Nummerplaat other = (Nummerplaat) obj;
		if (plaat == null) {
			if (other.plaat != null) {
				return false;
			}
		} else if (!plaat.equals(other.plaat)) {
			return false;
		}
		return true;
	}
	//Compareable
	@Override
	public int compareTo(Nummerplaat nummperplaat2) {
		// TODO Auto-generated method stub
		return plaat.compareToIgnoreCase(nummperplaat2.getPlaat());
	}
}
