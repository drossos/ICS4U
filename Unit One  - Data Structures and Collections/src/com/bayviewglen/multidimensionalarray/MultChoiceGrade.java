package com.bayviewglen.multidimensionalarray;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MultChoiceGrade {

	public static void main(String[] args) {
		Scanner input = null;
		try {
			input = new Scanner(new File ("data/multChoice.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		char [] key = createKey(input);
		char [][] sAnswers = new char[8][];
		studentAnswers(input, sAnswers);
		compareToKey(sAnswers,key);

	}

	
	private static void compareToKey(char[][] sAnswers, char[] key) {
		for (int i = 0; i < sAnswers.length; i++) {
			int score = 0;
			for(int j = 0; j < sAnswers[i].length; j++) {
				if (sAnswers[i][j] == key[j]) 
					score++;
			}
			System.out.println("Student " + i + " score: "+ score);
		}
		
	}


	private static void studentAnswers(Scanner input, char[][] sAnswers) {
		for (int i = 0; i < 8; i++) {
			sAnswers[i] = input.nextLine().toCharArray();
		}
		
	}


	private static char[] createKey(Scanner input) {
		return input.nextLine().toCharArray();
		
	}

}
