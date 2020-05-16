package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import exceptions.BillAlreadyOnListException;
import exceptions.ProviderAlreadyOnListException;

public class Controller {
	private ArrayList<Provider> providers;
	private Account firstAccount;

	public Controller() {
		this.providers = new ArrayList<Provider>();
		firstAccount = new Account("Alejandro", "Garcia", "alejandro.garcia1@hotmail.com", "p", "p");
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
	
	public void addAccount(String name, String lastname, String email, String username, String password) {
		Account aux = new Account(name, lastname, email, username, password);
		
		if(firstAccount == null) {
			firstAccount = aux;
		}else {
			Account current = firstAccount;
			while(current.getNext() != firstAccount) {
				current = current.getNext();
			}
			current.setNext(aux);
		}
		
		aux.setNext(firstAccount);
	}
	
	public Account findAccount(String username, String password) {
		Account aux = null;

		if (firstAccount != null) {
			if (firstAccount.getUsername().equalsIgnoreCase(username) && firstAccount.getPassword().equals(password)) {
				aux = firstAccount;
			}else {
				Account current = firstAccount;
				boolean found = false;
				
				while (current.getNext() != null && !found){
					if (current.getNext().getUsername().equalsIgnoreCase(username) && firstAccount.getPassword().equals(password)) {
						aux = current.getNext();
						found = true;
					}
					
					current = current.getNext();
				}
			}
		}		
		return aux;
	}
	
	public boolean searchUsername(String username) {
		boolean found = false;

		if (firstAccount != null) {
			if (firstAccount.getUsername().equalsIgnoreCase(username)) {
				found = true;
			}else {
				Account current = firstAccount;
				while (current.getNext() != null && !found){
					if (current.getNext().getUsername().equalsIgnoreCase(username)) {
						found = true;
					}
					
					current = current.getNext();
				}
			}
		}		
		return found;
	}
	
	public void getAccounts() {
		Account aux = firstAccount;
		
		while (aux != null) {
			System.out.println(aux.getName());
			aux = aux.getNext();
		}
	}
}
