package com.bayviewglen.arrays;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class ArraySorter {

	public static void main(String[] args) throws FileNotFoundException {
		String[] allWords = new String[1000];
		int wordCount = 0;

		Scanner input = new Scanner(new File("data/words.dat"));
		while (input.hasNext()) {
		    String word = input.next();
		    allWords[wordCount] = word;
		    wordCount++;
		}
		
		allWords = getRidOfNull(allWords, wordCount);
		Arrays.sort(allWords);
		
		for (String i: allWords) {
			System.out.println(i);
		}
		
		allWords = removeMiddle(allWords);
		Arrays.sort(allWords);
		System.out.println();
		
		for (String i: allWords) {
			System.out.println(i);
		}


	}

	private static String[] removeMiddle(String[] allWords) {
		String [] temp = new String [allWords.length-1];
		int mid = allWords.length-1;
		int count = 0;
		for (int i = 0; i < allWords.length;i++) {
			if (i != mid) {
				temp[count] = allWords[i];
				count++;
			}
		}
		return temp;
	}

	private static String[] getRidOfNull(String[] allWords, int wordCount) {
		String[] temp = new String [wordCount];
		for (int i = 0; i < wordCount; i++) {
			temp[i] = allWords[i];
		}
		return temp;
	}

}
