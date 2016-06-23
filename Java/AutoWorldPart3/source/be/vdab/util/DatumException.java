package be.vdab.util;

public class DatumException extends Exception {
	public DatumException() {}
	public DatumException(String omschrijving) {
		super(omschrijving);
	}
	public DatumException(Exception exception){
		super(exception);
	}
	public DatumException(String omschrijving, Exception exception){
		super(omschrijving, exception);
	}
	public DatumException(String omschrijving, IllegalAccessError iac) {
		// TODO Auto-generated constructor stub
		super(omschrijving, iac);
	}
}