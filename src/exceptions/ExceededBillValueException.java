package exceptions;

@SuppressWarnings("serial")
public class ExceededBillValueException extends Exception {
	private double value;

	public ExceededBillValueException(double value) {
		super();
		this.value = value;
	}

	public double getValue() {
		return value;
	}
}
