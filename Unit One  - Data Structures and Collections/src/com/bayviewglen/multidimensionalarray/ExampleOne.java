package com.bayviewglen.multidimensionalarray;

public class ExampleOne {

	public static void main(String[] args) {
		char [][] words = new char[3][];

		words[0] = "cipher".toCharArray();
		words[1] = "hiccup".toCharArray();
		words[2] = "laughable".toCharArray();
		/* Can't change size of array but can make it point to another array instead
		 * words[1] = words[2];
		 */
		
		
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length; j++) {
				words[i][j]+= 2;
			}
		}
		
		/*
		for (Character[]arr:words) {
			for (int j = 0; j < words[i].length; j++) {
				words[i][j]+= 2;
			}
		}
		*/

		for (char[] arr : words) {
			for (char i : arr) {
				System.out.print(i);
			}
			System.out.println();
		}

	}

}
