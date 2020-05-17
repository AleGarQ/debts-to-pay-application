package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Provider {
	private ArrayList<Bill> bills;
	private BankEntity account;
	private String bussinessName;
	private String nit;
	private String intermediaryName;
	private String intermediaryPhone;
	private boolean working;

	public Provider(BankEntity account, String bussinessName, String nit, String intermediaryName,
			String intermediaryPhone, boolean working) {
		this.bills = new ArrayList<Bill>();
		this.account = account;
		this.bussinessName = bussinessName;
		this.nit = nit;
		this.intermediaryName = intermediaryName;
		this.intermediaryPhone = intermediaryPhone;
		this.working = working;
	}

	public void addBill(String concept, boolean current, double value, GregorianCalendar builtDate,
			GregorianCalendar finalPaymentDate, double interestPercentage, int fee, String paymentMethod, boolean paid,
			double valuePaid) {
		// TODO
	}

	public void addPayment(String billConcept) {
		// TODO
	}

	public void searchBill(String concept) {
		// TODO
	}

	public String showPaymentsMade() {
		// TODO
		return "";
	}

	public ArrayList<Bill> getBills() {
		return bills;
	}

	public void setBills(ArrayList<Bill> bills) {
		this.bills = bills;
	}

	public String getIntermediaryName() {
		return intermediaryName;
	}

	public void setIntermediaryName(String intermediaryName) {
		this.intermediaryName = intermediaryName;
	}

	public String getIntermediaryPhone() {
		return intermediaryPhone;
	}

	public void setIntermediaryPhone(String intermediaryPhone) {
		this.intermediaryPhone = intermediaryPhone;
	}

	public String getAccount() {
		return account.getAccountNumber();
	}
	
	public String getBank() {
		return account.getBankName();
	}

	public String getBussinessName() {
		return bussinessName;
	}

	public String getNit() {
		return nit;
	}

	public boolean isWorking() {
		return working;
	}

	public void setWorking(boolean working) {
		this.working = working;
	}
	
	public String toString() {
		String info = bussinessName + " |" + nit + " |" + account.getBankName() +" |"+ account.getAccountNumber() + " |" + intermediaryName + " |"+ intermediaryPhone + " |" + working;
		return info;
	}
}
