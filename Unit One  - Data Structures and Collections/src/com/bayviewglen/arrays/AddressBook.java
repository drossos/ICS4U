package com.bayviewglen.arrays;

import java.util.Arrays;

public class AddressBook {
	private Contact[] contacts;
	private int numContacts;
	String pNumberRegex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

	public AddressBook() {
		contacts = new Contact[0];
		numContacts = 0;
	}

	public Contact[] getContacts() {
		return contacts;
	}

	public void setContacts(Contact[] contacts) {
		this.contacts = contacts;
	}

	public void addContact() {
		boolean valid = false;
		Contact toAdd = null;
		while(!valid) {
			int spaces = 0;
		String contact = askForContactAdd();
		for (int i = 0; i < contact.length(); i++)
			if (contact.charAt(i) == ' ')
				spaces++;
		if (spaces != 2)
			System.out.println("Invalid Contact");
		else {
		String[] brokenUp = contact.split(" ");
		if (brokenUp[2].matches(pNumberRegex))
			valid = true;
		else 
			System.out.println("Invalid Phone Number. Format like 123-456-7890,(123)456-7890 or 1234567890/n");
		}
		}
		numContacts++;
		contacts = new Contact[numContacts];
		contacts[numContacts - 1] = toAdd;
		

	}

	private String askForContactAdd() {
		System.out.println("Enter name of new contact (John Doe 416-123-4567)");
		return AdressBookDriver.input.nextLine().trim();

	}

	public void displayAll() {
		if (contacts.length == 0) {
			System.out.println("You have no contacts");
		} else {
			System.out.println("CONTACTS");
			for (int i = 0; i < contacts.length; i++) {
				displayInfo(contacts[i]);
			}
		}
		

	}

	private void displayInfo(Contact contact) {
		System.out.println(contact.getLname() + ", " + contact.getFname()
				+ " " + contact.getPhone());
		
	}

	public void searchByLastN(int option) {
		if (contacts.length == 0) {
			System.out.println("You have no contacts");
		} else {
		String lastNameSearch = askLastName();
		for (int i = 0; i < contacts.length;i++) {
			if (contacts[i].getLname().equals(lastNameSearch) && option == AdressBookDriver.SEARCH_LAST)
				displayInfo(contacts[i]);
			else if (contacts[i].getLname().equals(lastNameSearch) && option == AdressBookDriver.DELETE_LAST){
				deleteByLastN(i);
				i--;
			}
			else 
				System.out.println("Contact not in adress book/n");
		}
			
		}
		
	}

	private String askLastName() {
		//TODO enter check that is name entering
		System.out.println("Enter in last name of contact: ");
		return AdressBookDriver.input.nextLine().trim();
	}

	public void deleteByLastN(int indexOfRemove) {
		Contact [] temp = new Contact[numContacts - 1];
		int tempInd = 0;
		for (int i = 0; i < contacts.length; i++) {
			if (i != indexOfRemove) {
				temp[tempInd] = contacts[i];
				tempInd++;
			}
		}
		numContacts--;
		contacts = temp;
	}

}
