package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import exceptions.BillAlreadyOnListException;
import exceptions.ExceededBillFinalPaymentDayException;
import exceptions.ExceededBillValueException;
import exceptions.InvalidInformationException;

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
			double valuePaid) throws BillAlreadyOnListException { 
		Bill toAdd = new Bill(concept, current, value, builtDate, finalPaymentDate, interestPercentage, fee, paymentMethod, paid, valuePaid);
		if (searchBill(concept)) {
			throw new BillAlreadyOnListException();
		} else {
			bills.add(toAdd);
		}
			
	}

	public void addPayment(String billConcept, double value) throws InvalidInformationException, ExceededBillValueException, ExceededBillFinalPaymentDayException {
		Bill toPay = searchBills(billConcept);
		Calendar today = new GregorianCalendar();
		if (today.compareTo(toPay.getFinalPaymentDate()) > 0 && !toPay.isMora()) {
			toPay.setValue(toPay.getValue() * (1 + toPay.getInterestPercentage()));
			toPay.setMora(true);
			throw new ExceededBillFinalPaymentDayException();
		} 
		if (toPay.getValue() - toPay.getValuePaid() < value) {
			throw new ExceededBillValueException(value - toPay.getValue() - toPay.getValuePaid());
		} else {
			toPay.setValuePaid(value + toPay.getValuePaid());
			if (toPay.getValuePaid() == toPay.getValue()) {
				toPay.setPaid(true);
			}
		}
		
	}

	public boolean searchBill(String concept) {
		boolean ward = false;
		for (int i = 0; i < bills.size(); i++) {
			if (bills.get(i).getConcept().equalsIgnoreCase(concept)) {
				ward = true;
			}
		}
		return ward;
	}
	
	public Bill searchBills(String billConcept) throws InvalidInformationException {
		Bill toSearch = null;
		for (int i = 0; i < bills.size(); i++) {
			if (bills.get(i).getConcept().equalsIgnoreCase(billConcept)) {
				toSearch = bills.get(i);
			}
		}
		if (toSearch == null) {
			throw new InvalidInformationException();
		}
		return toSearch;
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
