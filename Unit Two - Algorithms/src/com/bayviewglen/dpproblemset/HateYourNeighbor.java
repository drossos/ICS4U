package com.bayviewglen.dpproblemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HateYourNeighbor {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String[] srngNums = input.readLine().split(" ");
		int[] nums = new int[srngNums.length];
		for (int i = 0; i < srngNums.length; i++) {
			nums[i] = Integer.parseInt(srngNums[i]);
		}
		srngNums = null;
		
		//first index is total value and other indices are the points on circle that are in that soloution
		int[][] sln = new int[nums.length+1][1];
		sln[0][0] = 0;
		for (int i = 1; i < sln.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				boolean notAdjacent = true;
				if (nums[j] + sln[i-1][0] > sln[i][0]) {
					for (int k = 1; k < sln[i-1].length; k++) {
						int x = Math.abs(j - sln[i-1][k]);
						if (Math.abs(j - sln[i-1][k]) == 1 || Math.abs(j - sln[i-1][k]) == nums.length-1 || j == sln[i-1][k])
							notAdjacent = false;
					}
					if (notAdjacent) {
						sln[i] = new int[sln[i-1].length+1];
						sln[i][0] = sln[i-1][0] + nums[j];
						/*sln[i] = sln[i-1];
						sln[i][sln[i].length] = j;*/
						for (int k = 1;k < sln[i-1].length; k++) {
							sln[i][k] = sln[i-1][k];
						}
						sln[i][sln[i].length-1] = j;
					}
				}
			}
		}
		
		System.out.println(sln[sln.length-1][0]);
	}

}
