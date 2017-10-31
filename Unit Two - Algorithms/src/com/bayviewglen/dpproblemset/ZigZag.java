package com.bayviewglen.dpproblemset;

public class ZigZag {

	public static void main(String[] args) {
		int [] nums = {1,5,1,5};
		int [] sln = new int[nums.length+1];
		sln[0] = 0;
		sln[1] = 1;
		boolean currBigger = true;;
		boolean either = true;
		if (nums[1] > nums[0])
			currBigger = true;
		else if (nums[1] < nums[0])
			currBigger = false;
		else 
			either = true;
		
		for (int i = 2; i < sln.length; i++) {
			if (nums[i-1] > nums[i-2] && currBigger == true || either) {
				sln[i] = sln[i-1] + 1;
				currBigger = false;
				either = false;
			} else if (nums[i-1] < nums[i-2] && currBigger == false || either) {
				sln[i] = sln[i-1] + 1;
				currBigger = true;
				either = false;
			} else {
				sln[i] = sln[i-1];
			}
		}
		
		System.out.println(""+sln[sln.length-1]);

	}

}
