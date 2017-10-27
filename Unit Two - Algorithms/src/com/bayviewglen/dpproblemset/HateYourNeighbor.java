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


	
	//trying to make more efficent
	/*Integer [] numsSorted = new Integer [nums.length];
	for (int i = 0; i < nums.length; i++)
		numsSorted[i] = nums[i];
	Arrays.sort(numsSorted);
	Integer[] sln = new Integer[nums.length+1];
	Arrays.fill(sln, 0);
	sln[0] = 0;
	int frontCount = 1;
	for (int i = numsSorted.length-1;i>=1; i--) {
		if (sln[frontCount] == null || sln[frontCount-1] + numsSorted[i] > sln[frontCount]) {
			boolean x = nums[Arrays.asList(nums).indexOf(numsSorted[i])] != nums.length-1;if (frontCount == 1) {
				sln[frontCount] = sln[frontCount-1] + numsSorted[i];
				nums[Arrays.asList(nums).indexOf(numsSorted[i])] = -1;
			}
			else if ( nums[Arrays.asList(nums).indexOf(numsSorted[i])] != nums.length-1 && nums[Arrays.asList(nums).indexOf(numsSorted[i]) + 1] != -1 && nums[Arrays.asList(nums).indexOf(numsSorted[i]) - 1] != -1 
					|| frontCount == nums.length-1 && nums[Arrays.asList(nums).indexOf(numsSorted[i]) - 1] != -1) {
				sln[frontCount] = sln[frontCount-1] + numsSorted[i];
				nums[Arrays.asList(nums).indexOf(numsSorted[i])] = -1;
			} else {
				sln[frontCount] = sln[frontCount-1];
			}
		} else {
			sln[frontCount] = sln[frontCount-1];
		}
		frontCount++;
		
		
	}System.out.println(sln[sln.length-1]);*/


