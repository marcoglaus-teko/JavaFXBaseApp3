package ch.teko.prg3.kontakt;

import javafx.beans.property.SimpleStringProperty;

public class Kontakt {

	private SimpleStringProperty firstName;
	private SimpleStringProperty secondName;
	private SimpleStringProperty phoneNumber;
	private SimpleStringProperty email;
	
	public Kontakt(String firstName, String secondName, String phoneNumber, String email) {
		this.firstName = new SimpleStringProperty(firstName);
		this.secondName = new SimpleStringProperty(secondName);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.email = new SimpleStringProperty(email);
	}

	// Getter Setter
	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(SimpleStringProperty firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName.get();
	}

	public void setSecondName(SimpleStringProperty secondName) {
		this.secondName = secondName;
	}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public void setPhoneNumber(SimpleStringProperty phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(SimpleStringProperty email) {
		this.email = email;
	}
	
	
	
	
	
}
