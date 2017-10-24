package com.bayviewglen.dynamicprogramming;

public class CoinProblem {
	static int[] solutions;
	static int n = 4;
	static int [] numCoins = {1,3,5};
	public static void main(String[] args) {
		solutions = new int[n];
		
	}
	
	public int solve (int n) {
		solutions[0] = 0;
		
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < numCoins.length; j++) {
				solutions[i] = 
			}
		}
	}

}
