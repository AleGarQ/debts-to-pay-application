package exceptions;

@SuppressWarnings("serial")
public class SameIntermediaryPhoneException extends Exception {
	public SameIntermediaryPhoneException() {
		super("The name of the intermadiary can be the same, but the number of phone needs to be different");
	}
}
