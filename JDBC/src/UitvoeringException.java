
public class UitvoeringException extends Exception {
	public UitvoeringException() {}
	public UitvoeringException(String omschrijving) {
		super(omschrijving);
	}
	public UitvoeringException(Exception exception){
		super(exception);
	}
	public UitvoeringException(String omschrijving, Exception exception){
		super(omschrijving, exception);
	}
}
