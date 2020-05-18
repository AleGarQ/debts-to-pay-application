package model;

import java.util.GregorianCalendar;

import exceptions.ExceededBillValueException;

public class Bill {
	private String concept;
	private boolean current;
	private double value;
	private GregorianCalendar builtDate;
	private GregorianCalendar finalPaymentDate;
	private double interestPercentage;
	private int fee;
	private String paymentMethod;
	private boolean paid;
	private double valuePaid;
	private boolean mora;

	public Bill(String concept, boolean current, double value, GregorianCalendar builtDate,
			GregorianCalendar finalPaymentDate, double interestPercentage, int fee, String paymentMethod, boolean paid,
			double valuePaid) {
		this.concept = concept;
		this.current = current;
		this.builtDate = builtDate;
		this.finalPaymentDate = finalPaymentDate;
		this.interestPercentage = interestPercentage;
		this.fee = fee;
		this.paymentMethod = paymentMethod;
		this.paid = paid;
		this.valuePaid = valuePaid;
		this.value = value * (1 + interestPercentage);
		setMora(false);
	}

	public String verifyBillState(double paidValue) throws ExceededBillValueException {
		// TODO
		return "";
	}

	public String showPaymentsMade() {
		// TODO
		return "";
	}

	public String toString() {
		// TODO
		return "";
	}

	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}

	public GregorianCalendar getFinalPaymentDate() {
		return finalPaymentDate;
	}

	public void setFinalPaymentDate(GregorianCalendar finalPaymentDate) {
		this.finalPaymentDate = finalPaymentDate;
	}

	public double getInterestPercentage() {
		return interestPercentage;
	}

	public void setInterestPercentage(double interestPercentage) {
		this.interestPercentage = interestPercentage;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public String getConcept() {
		return concept;
	}

	public boolean isCurrent() {
		return current;
	}

	public GregorianCalendar getBuiltDate() {
		return builtDate;
	}

	public int getFee() {
		return fee;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public double getValuePaid() {
		return valuePaid;
	}

	public void setValuePaid(double valuePaid) {
		this.valuePaid = valuePaid;
	}

	/**
	 * @return the mora
	 */
	public boolean isMora() {
		return mora;
	}

	/**
	 * @param mora the mora to set
	 */
	public void setMora(boolean mora) {
		this.mora = mora;
	}
}
