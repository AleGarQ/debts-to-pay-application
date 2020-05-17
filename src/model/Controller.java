package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import exceptions.BillAlreadyOnListException;
import exceptions.ExceededBillFinalPaymentDayException;
import exceptions.ExceededBillValueException;
import exceptions.InvalidInformationException;
import exceptions.ProviderAlreadyOnListException;

public class Controller {
	private ArrayList<Provider> providers;
	private Account firstAccount;

	public Controller() {
		this.providers = new ArrayList<Provider>();
		providers.add(new Provider(new BankEntity("12345", "bancolombia"), "colombina", "12345", "Sebastian", "3105896411", true));
		providers.add(new Provider(new BankEntity("123456", "bancolombia"), "coca cola", "123456", "Alejandro", "3105896411", false));
		providers.add(new Provider(new BankEntity("1234567", "bancolombia"), "rimax", "1234567", "Isabella", "3105896411", true));
		providers.add(new Provider(new BankEntity("12345678", "bancolombia"), "dell", "12345678", "Maria", "3105896411", false));
		providers.get(1).addBill("Vasos", true, 150000.0, new GregorianCalendar(2001, 01, 22), new GregorianCalendar(2010, 01, 22), 0.1, 0, "no lo se", false, 100000.0);
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
			double valuePaid) throws BillAlreadyOnListException, InvalidInformationException {
		Provider toAdd = searchProviders(providerName);
		toAdd.addBill(concept, current, value, builtDate, finalPaymentDate, interestPercentage, fee, paymentMethod, paid, valuePaid);
	}

	public void modifyProvider(String bussinessName, String intermediaryName, String intermediaryPhone) throws InvalidInformationException {
		Provider toChange = searchProviders(bussinessName);
		if (toChange.getIntermediaryPhone().equals(intermediaryPhone)) {
			throw new InvalidInformationException();
		} else {
			toChange.setIntermediaryName(intermediaryName);
			toChange.setIntermediaryPhone(intermediaryPhone);
		}
	}

	public void addPayment(String providerName, String billConcept, double value) throws InvalidInformationException, ExceededBillValueException, ExceededBillFinalPaymentDayException {
		Provider toPay = searchProviders(providerName);
		toPay.addPayment(billConcept, value);
	}
	
	public Provider searchProviders(String providerName) throws InvalidInformationException {
		Provider toSearch = null;
		for (int i = 0; i < providers.size(); i++) {
			if (providers.get(i).getBussinessName().equalsIgnoreCase(providerName)) {
				toSearch = providers.get(i);
			}
			if (toSearch == null) {
				throw new InvalidInformationException();
			}
		} 
		return toSearch;
		
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
		for (int i = 0; i < providers.size(); i++) {
			int pos = i;
			
            for (int j = i; j < providers.size(); j++) {
                if (providers.get(j).getBussinessName().compareTo(providers.get(pos).getBussinessName()) < 0) {
                    pos = j;
                }
            }

            Provider min = providers.get(pos);
            providers.set(pos, providers.get(i));
            providers.set(i, min);
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
	
	public List<Provider> getProviders(){
		return providers;
	}
	
	public List<Provider> getActiveProviders(){
		ArrayList<Provider> act = new ArrayList<Provider>();
		
		for (int i = 0; i < providers.size(); i++) {
			if(providers.get(i).isWorking()) {
				act.add(providers.get(i));
			}
		}
		
		return act;
	}
	
	public List<Provider> getInactiveProviders(){
		ArrayList<Provider> act = new ArrayList<Provider>();
		
		for (int i = 0; i < providers.size(); i++) {
			if(!providers.get(i).isWorking()) {
				act.add(providers.get(i));
			}
		}
		
		return act;
	}
	
	public List<Provider> getWBProviders(){
		ArrayList<Provider> act = new ArrayList<Provider>();
		
		for (int i = 0; i < providers.size(); i++) {
			if(providers.get(i).getBills().isEmpty()) {
				act.add(providers.get(i));
			}
		}
		
		return act;
	}
	
	public List<Bill> getBills(){
		ArrayList<Bill> bills = new ArrayList<Bill>();
		
		for (int i = 0; i < providers.size(); i++) {
			if (!providers.get(i).getBills().isEmpty()) {
				for (int j = 0; j < providers.get(i).getBills().size(); j++) {
					bills.add(providers.get(i).getBills().get(j));
				}
			}
		}
		
		return bills;
	}
}
