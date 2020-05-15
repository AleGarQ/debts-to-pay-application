package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import exceptions.BillAlreadyOnListException;
import exceptions.ProviderAlreadyOnListException;

public class Controller {
	private ArrayList<Provider> providers;

	public Controller() {
		this.providers = new ArrayList<Provider>();
	}

	public void addProvider(String bankName, String accountNumber, String bussinessName, String nit,
			String intermediaryName, String intermediaryPhone) throws ProviderAlreadyOnListException {
		// TODO
	}

	public void addBill(String providerName, String concept, boolean current, double value, GregorianCalendar builtDate,
			GregorianCalendar finalPaymentDate, double interestPercentage, int fee, String paymentMethod, boolean paid,
			double valuePaid) throws BillAlreadyOnListException {
		// TODO
	}

	public void modifyProvider(String intermediaryName, String intermediaryPhone) {
		// TODO
	}

	public void addPayment(String providerName, String billConcept, int value) {
		// TODO
	}

	public String showBillsToPay() {
		// TODO
		return "";
	}

	public String showPaidBills() {
		// TODO
		return "";
	}

	public String showPaymentsMade() {
		// TODO
		return "";
	}

	public String showProviders() {
		// TODO
		return "";
	}

	public void searchProvider(String nit) {
		// TODO
	}

	public void searchBill(String nit, String concept) {
		// TODO
	}
}
