package com.bayviewglen.dpproblemset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HateYourNeighbor {

	public static void main(String[] args) throws IOException {
		// un comment this code to make it take inputs instead of having hard coded
		// array
		BufferedReader input = new BufferedReader(new FileReader("data/BadNeighbor.dat"));
		int lim = Integer.parseInt(input.readLine());
		 //int [] nums = {5 ,10, 7, 2, 9};

		for (int j = 0; j < lim; j++) {
			input.readLine();
			String[] srngNums = input.readLine().split(" ");
			int[] nums = new int[srngNums.length];
			for (int i = 0; i < srngNums.length; i++) {
				nums[i] = Integer.parseInt(srngNums[i]);
			}

			// comment this line out if switching to input and not hardcoded

			// these are the two different ways you can go to not allow for front and back
			// element to be comapred
			int[] noFront = new int[nums.length - 1];
			int[] noEnd = new int[nums.length - 1];
			int[] noFrontSoln = new int[nums.length];
			int[] noEndSoln = new int[nums.length];
			for (int i = 0; i < nums.length - 1; i++) {
				noEnd[i] = nums[i];
				noFront[i] = nums[i + 1];
			}
			if (nums.length == 1) {
				System.out.println(nums[0]);
			} else {
				// index of sln being subproblem with that many hoses in given array
				noFrontSoln[0] = 0;
				noEndSoln[0] = 0;
				noFrontSoln[1] = noFront[0];
				noEndSoln[1] = noEnd[0];

				for (int i = 1; i < noEnd.length; i++) {
					noFrontSoln[i + 1] = Math.max(noFrontSoln[i - 1] + noFront[i], noFrontSoln[i]);
					noEndSoln[i + 1] = Math.max(noEndSoln[i - 1] + noEnd[i], noEndSoln[i]);
				}

				System.out.println(Math.max(noEndSoln[noEndSoln.length - 1], noFrontSoln[noFrontSoln.length - 1]));
			}
		}

		/*
		 * //first index is total value and other indices are the points on circle that
		 * are in that soloution int[][] sln = new int[nums.length+1][1]; sln[0][0] = 0;
		 * for (int i = 1; i < sln.length; i++) { for (int j = 0; j < nums.length; j++)
		 * { boolean notAdjacent = true; if (nums[j] + sln[i-1][0] > sln[i][0]) { for
		 * (int k = 1; k < sln[i-1].length; k++) { int x = Math.abs(j - sln[i-1][k]); if
		 * (Math.abs(j - sln[i-1][k]) == 1 || Math.abs(j - sln[i-1][k]) == nums.length-1
		 * || j == sln[i-1][k]) notAdjacent = false; } if (notAdjacent) { sln[i] = new
		 * int[sln[i-1].length+1]; sln[i][0] = sln[i-1][0] + nums[j]; sln[i] = sln[i-1];
		 * sln[i][sln[i].length] = j; for (int k = 1;k < sln[i-1].length; k++) {
		 * sln[i][k] = sln[i-1][k]; } sln[i][sln[i].length-1] = j; } } } }
		 * 
		 * System.out.println(sln[sln.length-1][0]);
		 */
	}

}
