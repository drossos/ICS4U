package com.bayviewglen.dpproblemset;


public class KingKnight {

	public static void main(String[] args) {
		/* Question asks for num of ways to get to end, not best num of moves to get to end*/
		int dimens = 7;
		int [] end = {0,3};
		int [] start = {2,0};
		int [] curr = start;
		//int [][] board = new int[dimens][dimens];
		int moves = 0;
		
		//first is distance to end
		//sec y, third is x
		int[][] sln = new int[1000][3]; 
		for (int i =0; i < sln[0].length; i++) {
			sln[i][0] = Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]);
			sln[i][1] = 0;
			sln[i][2] = 0;
		}
		int slnIndex = 0;
		//to direct movement
		int xDir = 1;
		int yDir = 1;
		while (curr[0] != end[0] || curr[1] != end[1]) {
			//check to see what quadrant need to travel in
			if (curr[0] < end[0])
				yDir = 1;
			else if (curr[0] > end[0])
				yDir = -1;
			else 
				yDir = 0;
			if (curr[1] < end[1])
				xDir = 1;
			else if (curr[1] > end[1])
				xDir = -1;
			else 
				xDir = 0;
			
			//to check for the up L is best sln
			if (curr[0] + 2*yDir < dimens && curr[1] + 1*xDir < dimens) {
				//to find which one has less moves toget to end
				if (Math.abs(curr[0] - 2*yDir - end[0]) + Math.abs(curr[1] + 1*xDir - end[1]) < sln[0][slnIndex]) {
					sln[slnIndex][0] = Math.abs(curr[0] + 2*yDir - end[0]) + Math.abs(curr[1] + 1*xDir - end[1]);
					sln[slnIndex][1] = curr[0] + 2*yDir;
					sln[slnIndex][2] = curr[1] + 1*xDir;
				}
			}
			//to check for side L 
			if (curr[0] - 1*yDir < dimens && curr[1] + 2*xDir < dimens) {
				//to find which one has less moves toget to end
				if (Math.abs(curr[0] + 1*yDir - end[0]) + Math.abs(curr[1] + 2*xDir - end[1]) < sln[0][slnIndex]) {
					sln[slnIndex][0] = Math.abs(curr[0] + 1*yDir - end[0]) + Math.abs(curr[1] + 2*xDir - end[1]);
					sln[slnIndex][1] = curr[0] + 1*yDir;
					sln[slnIndex][2] = curr[1] + 2*xDir;
				}
			}
			//to check for diag 
			if (curr[0] - 1*yDir < dimens && curr[1] + 1*xDir < dimens) {
				//to find which one has less moves toget to end
				if (Math.abs(curr[0] + 1*yDir - end[0]) + Math.abs(curr[1] + 1*xDir - end[1]) < sln[0][slnIndex]) {
					sln[slnIndex][0] = Math.abs(curr[0] + 1*yDir - end[0]) + Math.abs(curr[1] + 1*xDir - end[1]);
					sln[slnIndex][1] = curr[0] + 1*yDir;
					sln[slnIndex][2] = curr[1] + 1*xDir;
				}
			}
			//vert adjacent 
			if (curr[0] - 1*yDir < dimens) {
				//to find which one has less moves toget to end
				if (Math.abs(curr[0] + 1*yDir - end[0]) + Math.abs(curr[1]) < sln[0][slnIndex]) {
					sln[slnIndex][0] = Math.abs(curr[0] + 1*yDir - end[0]) + Math.abs(curr[1]);
					sln[slnIndex][1] = curr[0] + 1*yDir;
					//sln[2][slnIndex] = curr[1] + 1*xDir;
				}
			}
			
			if (curr[1] + 1*xDir < dimens) {
				//to find which one has less moves toget to end
				if (Math.abs(curr[0]) + Math.abs(curr[1] + 1*xDir - end[1]) < sln[0][slnIndex]) {
					sln[slnIndex][0] = Math.abs(curr[0]) + Math.abs(curr[1] + 1*xDir - end[1]);
					//sln[1][slnIndex] = curr[0] + 1*yDir;
					sln[slnIndex][2] = curr[1] + 1*xDir;
				}
			}
			curr[0] = sln[slnIndex][1];
			curr[1] = sln[slnIndex][2];
			slnIndex++;
		}
		
		System.out.println(slnIndex);
	}

}
