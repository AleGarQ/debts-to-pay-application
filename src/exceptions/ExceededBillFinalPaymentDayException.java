package exceptions;

@SuppressWarnings("serial")
public class ExceededBillFinalPaymentDayException extends Exception{
	public ExceededBillFinalPaymentDayException() {
		super("An extra pay has been added because you pass the Final Payment day");
	}
}
