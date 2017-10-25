package com.bayviewglen.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoinProblem {
	static int[] solutions;
	static int n;
	static int[] numCoins = { 1, 3, 5 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(input.readLine());
		solutions = new int[n+1];
		System.out.println(solve(n));

	}

	public static int solve(int n) {
		solutions[0] = 0;

		for (int i = 1; i <= n; i++) {
			solutions[i] = Integer.MAX_VALUE;
			for (int j = 0; j < numCoins.length; j++) {
				if (numCoins[j] <= i)
					solutions[i] = Math.min(solutions[i], 1 + solutions[i - numCoins[j]]);
			}
		}
		return solutions[n];
	}

}
