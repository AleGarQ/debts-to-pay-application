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

	public void addProvider(String bankName, String accountNumber, String bussinessName, String nit, String intermediaryName, String intermediaryPhone) throws ProviderAlreadyOnListException {
		if (!searchProvider(nit)) {
			Provider aux = new Provider(new BankEntity(accountNumber, bankName), bussinessName, nit, intermediaryName, intermediaryPhone, true);
			providers.add(aux);
		}else {
			throw new ProviderAlreadyOnListException("The provider's NIT entered is already registered");
		}
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
		return "";
	}

	public boolean searchProvider(String nit) {
		boolean exists = false;
		
		for (int i = 0; i < providers.size() && !exists; i++) {
			if (providers.get(i).getNit().equals(nit)) {
				exists = true;
			}
		}
		
		return exists;
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
			while(current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(aux);
		}
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

	public void selectionSortProvidersByBussines() {
		for (int i = 0; i < providers.size()-1; i++) {
			Provider min = providers.get(i);
			int pos = i;
			
			for (int j = i + 1; j < providers.size(); j++) {
				if (providers.get(j).getBussinessName().compareTo(min.getBussinessName()) < 0) {
					min = providers.get(j);
					pos = j;
				}
				
				Provider aux = providers.get(i);
				providers.set(i, min);
				providers.set(pos, aux);
			}
		}
	}

	public void bubbleSortProvidersByIntermediary() {
		Provider aux = null;
		
		for (int i = 1; i < providers.size(); i++) {
			for (int j = 0; j < providers.size() - i; j++) {
				if (providers.get(j).getIntermediaryName().compareTo(providers.get(j + 1).getIntermediaryName()) > 0) {
					aux = providers.get(j);
					providers.set(j, providers.get(j + 1));
					providers.set(j + 1, aux);
				}
			}
		}
	}
}
