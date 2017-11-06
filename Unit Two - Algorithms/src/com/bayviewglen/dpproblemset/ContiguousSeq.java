package com.bayviewglen.dpproblemset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ContiguousSeq {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new FileReader("data/Contig.dat"));
		int lim = Integer.parseInt(input.readLine());
		//int[] nums = { 0 ,-1, 2 };

		for (int j = 0; j < lim; j++) {
			input.readLine();
			String[] srngNums = input.readLine().split(" ");
			int[] nums = new int[srngNums.length];
			for (int i = 0; i < srngNums.length; i++) {
				nums[i] = Integer.parseInt(srngNums[i]);
			}
			int[] sln = new int[nums.length + 1];

			sln[0] = 0;
			ArrayList<Integer> subArray = new ArrayList<Integer>();

			sln[1] = nums[0];
			subArray.add(nums[0]);

			for (int i = 2; i < sln.length; i++) {
				if (sln[i - 1] + nums[i - 1] >= nums[i - 1]) {
					sln[i] = sln[i - 1] + nums[i - 1];
					subArray.add(nums[i - 1]);
				} else if (sln[i - 1] + nums[i - 1] < nums[i - 1]) {
					sln[i] = nums[i - 1];
					subArray.clear();
					subArray.add(nums[i - 1]);
				}

			}
			int sum = Math.max(0, sum(subArray));
			System.out.println(sum);
		}

	}

	private static int sum(ArrayList<Integer> subArray) {
		int sum = 0;
		for (int i = 0; i < subArray.size(); i++) {
			sum += subArray.get(i);
		}
		return sum;
	}

}
