package model;

public class BankEntity {
	private String accountNumber;
	private String bankName;

	public BankEntity(String accountNumber, String bankName) {
		this.accountNumber = accountNumber;
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getBankName() {
		return bankName;
	}
}
