package com.bayviewglen.arrays;

import java.util.Scanner;

public class AdressBookDriver {
	static Scanner input = new Scanner(System.in);
	// make more valid responses
	static final String VALID_REPSONSES = "12345";
	static final int ADD_CONTACT = 1;
	static final int DISPLAY_ALL = 2;
	static final int SEARCH_LAST = 3;
	static final int DELETE_LAST = 4;
	static final int QUIT = 5;
	static boolean running = true;

	public static void main(String[] args) {
		AddressBook contacts = new AddressBook();
		
		int option;
		while (running) {
			option = getOption();
			processOption(option, contacts);
		}

	}

	private static void processOption(int option, AddressBook contacts) {
		if (option == ADD_CONTACT)
			contacts.addContact();
		if (option == DISPLAY_ALL)
			contacts.displayAll();
		if (option == SEARCH_LAST)
			contacts.searchByLastN(SEARCH_LAST);
		if (option == DELETE_LAST)
			contacts.searchByLastN(DELETE_LAST);
		if (option == QUIT)
			running = false;

	}

	private static int getOption() {
		boolean valid = false;
		String answer = "";
		displayMenu();
		while (!valid) {
			answer = input.nextLine();
			if (answer.length() == 1 && VALID_REPSONSES.indexOf(answer) != -1)
				valid = true;
			else {
				System.out.println("Not a valid option. Please select a valid option");
				displayMenu();
			}
		}

		return Integer.parseInt(answer);
	}

	private static void displayMenu() {
		System.out.println("Commands:");
		System.out.println("1. Add a contact");
		System.out.println("2. Display all contact");
		System.out.println("3. Search for contact (based off last name)");
		System.out.println("4. Delete contact (based off last name)");
		System.out.println("5. Quit");

	}

}
