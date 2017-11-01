package com.bayviewglen.dpproblemset;

public class ZigZag {

	public static void main(String[] args) {
		int [] nums = {4,3,2,1};
		int [] sln = new int[nums.length+1];
		sln[0] = 0;
		sln[1] = 1;
		boolean increasing = true;
		boolean either = true;
		if (nums.length > 1) {
		if (nums[1] > nums[0])
			increasing = true;
		else if (nums[1] < nums[0])
			increasing = false;
		else 
			either = true;
		
		}
		int toBeComapred = nums[0];
		for (int i = 2; i < sln.length; i++) {
			if (nums[i-1] > toBeComapred && increasing == true || either && nums[i-1] != toBeComapred) {
				sln[i] = sln[i-1] + 1;
				increasing = false;
				either = false;
				toBeComapred = nums[i-1];
			} else if (nums[i-1] < nums[i-2] && increasing == false || either && nums[i-1] != toBeComapred) {
				sln[i] = sln[i-1] + 1;
				increasing = true;
				either = false;
				toBeComapred = nums[i-1];
			} else {
				sln[i] = sln[i-1];
			}
		}
		
		System.out.println(""+sln[sln.length-1]);

	}
	
	

}
