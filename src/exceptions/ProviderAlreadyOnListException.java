package exceptions;

@SuppressWarnings("serial")
public class ProviderAlreadyOnListException extends Exception {
	public ProviderAlreadyOnListException(String e) {
		super(e);
	}
}
