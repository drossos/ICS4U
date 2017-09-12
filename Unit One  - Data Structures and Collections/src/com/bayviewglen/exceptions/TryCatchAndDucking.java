package com.bayviewglen.exceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TryCatchAndDucking {
	
	public static void main(String [] args) {
		tryCatchMethod();
		//duckingMethod();  it was thrown and now we ducked and Eclipse went down like 
		//a shot when the exception hit in the face
		try {
		throwingAndExceptionWithPerfectMechanics();
		}catch (BestExceptionEver ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private static void throwingAndExceptionWithPerfectMechanics() throws BestExceptionEver {
		throw new BestExceptionEver();
		
	}

	// Can either try catch or duck and pass exception on to someone else to deal with
	private static void duckingMethod() throws FileNotFoundException {
		Scanner scanner = new Scanner (new File("data.dat"));
	}

	private static void tryCatchMethod() {
		try {
			Scanner scanner = new Scanner (new File("data.dat"));
			throwingAndExceptionWithPerfectMechanics();
		}catch (FileNotFoundException ex) {
			/* the catch keeps it from crashing */
			System.out.println(ex.getMessage());
		} catch (BestExceptionEver e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Finally");
		}
		
	}

}
