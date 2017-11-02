package com.bayviewglen.dpproblemset;

public class ZigZag {

	public static void main(String[] args) {
		int [] nums = {-5 ,4, -2, 4, -4, -1, 4, -1, 5, 1 };
		
		
		int [] sln = new int[nums.length+1];
		sln[0] = 0;
		sln[1] = 1;
		boolean increasing = true;
		if (nums.length > 1) {
		if (nums[1] > nums[0])
			increasing = true;
		else 
			increasing = false;
	
		
		}
		
		for (int i = 2; i < sln.length; i++) {
			if (nums[i-1] > nums[i-2] && increasing == true && nums[i-1] != nums[i-2]) {
				sln[i] = sln[i-1] + 1;
				increasing = false;
			} else if (nums[i-1] < nums[i-2] && increasing == false && nums[i-1] != nums[i-2]) {
				sln[i] = sln[i-1] + 1;
				increasing = true;
			} else {
				sln[i] = sln[i-1];
			}
		}
		
		System.out.println(""+sln[sln.length-1]);

	}
	
	

}