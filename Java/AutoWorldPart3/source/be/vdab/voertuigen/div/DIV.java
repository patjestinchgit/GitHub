package be.vdab.voertuigen.div;

public class DIV {
	
	private int teller=0;
	private static final DIV INSTANCE= new DIV();
	
	public static DIV getInstance() {
		return INSTANCE;
	}
	public Nummerplaat getNummerplaat(){
		teller++;
		if(teller>999) teller=1;
		return new Nummerplaat(String.format("AAA%03d", teller));
	}
}