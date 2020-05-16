package model;

public class Account {
	private String name;
	private String lastname;
	private String email;
	private String username;
	private String password;
	private Account next;
	private Account prev;
	
	public Account(String name, String lastname, String email, String username, String password) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Account getNext() {
		return next;
	}

	public Account getPrev() {
		return prev;
	}

	public void setNext(Account next) {
		this.next = next;
	}

	public void setPrev(Account prev) {
		this.prev = prev;
	}
}
