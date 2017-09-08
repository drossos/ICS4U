package com.bayviewglen.arrays;

import java.util.Arrays;

public class AddressBook {
	private Contact[] contacts;
	private int numContacts;

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
		String contact = askForContactAdd();
		String[] brokenUp = contact.split(" ");
		Contact toAdd = new Contact(brokenUp[0], brokenUp[1], brokenUp[2]);
		numContacts++;
		contacts = new Contact[numContacts];
		contacts[numContacts - 1] = toAdd;
		

	}

	private String askForContactAdd() {
		// TODO add regex to validate the output
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
