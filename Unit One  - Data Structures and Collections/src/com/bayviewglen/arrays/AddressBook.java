package com.bayviewglen.arrays;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import com.bayviewglen.tree.BinarySearchTree;
import com.bayviewglen.tree.TreeNode;

public class AddressBook {
	private BinarySearchTree contacts;
	private int numContacts;
	String pNumberRegex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

	public AddressBook() {
		contacts = new BinarySearchTree();
		try {
			Scanner read = new Scanner(new File("data/contactList.dat"));
			numContacts = Integer.parseInt(read.nextLine());
			for (int i = 0; i < numContacts; i++) {
				String currLine = read.nextLine().trim();
				String[] parts = currLine.split(" ");
				contacts.add(new Contact(parts[0].trim(), parts[1].trim(), parts[2].trim()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public BinarySearchTree getContacts() {
		return contacts;
	}

	public void setContacts(BinarySearchTree contacts) {
		this.contacts = contacts;
	}

	public void addContact() {
		boolean valid = false;
		Contact toAdd = null;
		while (!valid) {
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
				} else
					System.out.println("Invalid Phone Number. Format like 123-456-7890,(123)456-7890 or 1234567890\n");
			}
		}
		// TODO ADD THE TOADD VALUE TO ARRAY
		numContacts++;
		contacts.add(toAdd);
		saveToFile();

	}

	private void saveToFile() {
		FileWriter fw;
		try {
			fw = new FileWriter(new File("data/contactList.dat"));
			/*
			 * ArrayList<Comparable> toWrite = contacts.toArray(contacts.getRoot());
			 * Collections.sort(toWrite);
			 */
			fw.write(numContacts + "");
			fw.write(System.getProperty("line.separator"));
			contacts.saveToFile(fw, contacts.getRoot());
			/*
			 * for (int i = 0; i < toWrite.size(); i++) { fw.write(((Contact)
			 * toWrite.get(i)).getFname() + " " + ((Contact) toWrite.get(i)).getLname() +
			 * " " + ((Contact) toWrite.get(i)).getPhone());
			 * fw.write(System.getProperty("line.separator")); }
			 */
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
		if (numContacts == 0) {
			System.out.println("You have no contacts");
		} else {
			System.out.println("CONTACTS");
			displayInfo();
		}

	}

	private void displayInfo() {
		contacts.inorderTraversal(contacts.getRoot());
		/*
		 * ArrayList<Comparable> toWrite = contacts.toArray(contacts.getRoot());
		 * Collections.sort(toWrite); for (int i = 0; i < toWrite.size(); i++) {
		 * System.out.println(((Contact) toWrite.get(i)).getLname() + ", " + ((Contact)
		 * toWrite.get(i)).getFname() + " " + ((Contact) toWrite.get(i)).getPhone());
		 * 
		 * }
		 */

	}

	public void searchByLastN(int option) {
		if (numContacts == 0) {
			System.out.println("You have no contacts");
		} else {
			String nameToRemove = askName();
			if (option == AdressBookDriver.SEARCH_LAST) {
				Comparable search = contacts.search(nameToRemove);
				if (search != null)
					System.out.println(((Contact) contacts.search(nameToRemove).getData()).forSaving());
				else {
					System.out.println("\nCONTACT NOT IN ADDRESS BOOK\n");
					return;
				}
			} else if (option == AdressBookDriver.DELETE_LAST) {
				String[] toRemove = nameToRemove.split(" ");
				boolean removed = contacts.remove(new Contact(toRemove[0], toRemove[1], "1234567899"),
						contacts.getRoot());
				if (removed) {
					numContacts--;
					System.out.println("Succesfully Deleted");
					if (numContacts != 0)
						saveToFile();
				} else
					System.out.println("\nCONTACT NOT IN ADDRESS BOOK\n");
			}
		}

	}

	private String askName() {
		// TODO enter check that is name entering
		boolean valid = false;
		String respon = "";
		while (!valid) {
			System.out.println("Enter in the name of contact: ");
			respon = AdressBookDriver.input.nextLine().trim();
			if (respon.indexOf(" ") != -1)
				valid = true;
			else
				System.out.println("That is not valid first and last name");
		}
		return respon;
	}

	public void deleteByLastN(int indexOfRemove) {
		ArrayList<Contact> temp = contacts.toArray(contacts.getRoot());
		Collections.sort(temp);
		Contact x = temp.remove(indexOfRemove);
		boolean removed = contacts.remove((Comparable) x);
		if (removed) {
			numContacts--;
			saveToFile();
		} else {
		}
	}

}
