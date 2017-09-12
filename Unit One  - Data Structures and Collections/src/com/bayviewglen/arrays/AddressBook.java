package com.bayviewglen.arrays;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class AddressBook {
	private Contact[] contacts;
	private int numContacts;
	String pNumberRegex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

	public AddressBook() {
		contacts = null;
		try {
			Scanner read = new Scanner(new File ("data/contactList.dat"));
			numContacts = Integer.parseInt(read.nextLine());
			contacts = new Contact[numContacts];
			for (int i = 0; i < numContacts; i++) {
				String currLine = read.nextLine().trim();
				String[] parts = currLine.split(" ");
				contacts[i] = new Contact (parts[0], parts[1], parts[2]);
			}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		
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
		if (brokenUp[2].matches(pNumberRegex)) {
			valid = true;
			toAdd = new Contact(brokenUp[0], brokenUp[1], brokenUp[2]);
		}
		else 
			System.out.println("Invalid Phone Number. Format like 123-456-7890,(123)456-7890 or 1234567890\n");
		}
		}
		//TODO ADD THE TOADD VALUE TO ARRAY
		numContacts++;
		Contact [] temp = new Contact[numContacts];
		for (int i = 0; i < contacts.length; i++) {
			temp[i] = contacts[i];
		}
		temp[temp.length-1 ] = toAdd;
		contacts = temp;
		saveToFile();
		

	}

	private void saveToFile() {
		FileWriter fw;
		try {
			fw = new FileWriter(new File("data/contactList.dat"));
			fw.write(numContacts + "");
			fw.write(System.getProperty("line.separator"));
			for (int i = 0; i < contacts.length;i++) {
				fw.write(contacts[i].getFname() + " " + contacts[i].getLname()
				+ " " + contacts[i].getPhone() );
				fw.write(System.getProperty("line.separator"));
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
		saveToFile();
	}

}
