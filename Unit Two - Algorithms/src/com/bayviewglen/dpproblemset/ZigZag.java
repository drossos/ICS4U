package com.bayviewglen.dpproblemset;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ZigZag {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new FileReader("data/ZigZag.dat"));
		int lim = Integer.parseInt(input.readLine());
		//int[] nums = { 3,3,4 };

		for (int j = 0; j < lim; j++) {
			input.readLine();
			String [] numsS = input.readLine().split(" ");
			int[]nums = new int[numsS.length];
			for (int k =0 ; k < numsS.length; k++) {
				nums[k] = Integer.parseInt(numsS[k]);
			}
			int[] sln = new int[nums.length + 1];
			sln[0] = 0;
			sln[1] = 1;
			boolean increasing = true;
			boolean either = false;
			if (nums.length > 1) {
				if (nums[1] > nums[0])
					increasing = true;
				else if (nums[1] < nums[0])
					increasing = false;
				else 
					either = true;

			}

			for (int i = 2; i < sln.length; i++) {
				if (nums[i - 1] > nums[i - 2] && increasing  || either && nums[i - 1] != nums[i - 2]) {
					sln[i] = sln[i - 1] + 1;
					increasing = false;
					if (either)
						either = false;
				} else if (nums[i - 1] < nums[i - 2] && !increasing || either && nums[i - 1] != nums[i - 2]) {
					sln[i] = sln[i - 1] + 1;
					increasing = true;
					if (either)
						either = false;
				} else {
					sln[i] = sln[i - 1];
				}
			}

			System.out.println("" + sln[sln.length - 1]);

		}
	}

}