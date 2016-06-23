package be.vdab.util;

public class VolumeException extends Exception {
	public VolumeException(){}
	public VolumeException(String omschrijving) {
		super(omschrijving);
	}
	public VolumeException(Exception exception){
		super(exception);
	}
	public VolumeException(String omschrijving, Exception exception){
		super(omschrijving, exception);
	}
	public VolumeException(String omschrijving, IllegalAccessError iac) {
		// TODO Auto-generated constructor stub
		super(omschrijving, iac);
	}
}