package be.vdab.util;

import java.io.Serializable;
import java.text.DecimalFormat;

final public class Datum implements Comparable<Datum>, Serializable {
	final private int dag, maand, jaar;

	public Datum(int dag, int maand, int jaar){
		//controleren of dag maand jaar wel mogelijk zijn
		controleDagMaandJaar(dag, maand, jaar);
		this.dag=dag;
		this.maand=maand;
		this.jaar=jaar;
	}


	private void controleDagMaandJaar(int dag, int maand, int jaar) {
		// TODO Auto-generated method stub
		try{
			//Jaartal tussen 1583 en 4099
			if((jaar<1583)||(jaar>4099)) throw new DatumException("Jaartal is niet juist(moet tussen 1583 en 4099 zijn.");
			//maand tussen 1 en 12
			if((maand<1)||(maand>12)) throw new DatumException("Maand kan maar tussen 1 en 12 zijn");
			//Dagen tussen 1 en 31
			if(((maand==1)||(maand==3)||(maand==5)||(maand==7)||(maand==8)||(maand==10)||(maand==12))&&((dag>31)||(dag<1))) throw new DatumException("Teveel of te weinig dagen in maand(tussen 1 en 31 zijn voor deze maand).");
			//Dagen voor februari
			else if(maand==2){
				//Voor schrikkeljaar
				if(((jaar % 4 == 0) && (jaar % 100 != 0) || (jaar % 400 == 0))&&((dag>29)||(dag<1))) throw new DatumException("Teveel of te weinig dagen in maand(tussen 1 en 29 zijn voor deze maand).");
				//Dagen tussen 1 en 28
				else if((dag>28)||(dag<1)) throw new DatumException("Teveel of te weinig dagen in maand(tussen 1 en 28 zijn voor deze maand).");
			}
			//Dagen tussen 1 en 30
			else if((dag>30)||(dag<1)) throw new DatumException("Teveel of te weinig dagen in maand(tussen 1 en 30 zijn voor deze maand).");
		}catch(DatumException e){
			e.getCause();
		}
	}
	//Getters
	public int getDag() {
		return dag;
	}
	public int getMaand() {
		return maand;
	}
	public int getJaar() {
		return jaar;
	}
	//Hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dag;
		result = prime * result + jaar;
		result = prime * result + maand;
		return result;
	}
	//Equal methode
	@Override
	public boolean equals(Object obj) {
		boolean result= true;
		if (obj == null) result=false;
		if (getClass() != obj.getClass()) result=false;
		Datum other = (Datum) obj;
		if ((dag != other.dag)||(jaar != other.jaar)||(maand != other.maand)) result= false;
		return result;
	}
	//Comparable
	@Override
	public int compareTo(Datum datum) {
		// TODO Auto-generated method stub
		int result= 1;;
		if (jaar < datum.getJaar())  result= -1;
		else if (jaar > datum.getJaar()) result= 1;
		else {
			if (maand < datum.getMaand()) result= -1;
			else if (maand > datum.getMaand()) result= 1;
			else {
				if (dag < datum.getDag()) result= -1;
				else if (dag > datum.getDag())	result= 1;
				else result= 0;
			}
		}
		return result;
	}
	//toString
	@Override
	public String toString() {
		DecimalFormat dagd = new DecimalFormat("#00");
		DecimalFormat maandd = new DecimalFormat("#00");
		String dags= dagd.format(dag);
		String maands= maandd.format(maand);
		return dags+"/"+maands+"/"+getJaar();
	}

}
