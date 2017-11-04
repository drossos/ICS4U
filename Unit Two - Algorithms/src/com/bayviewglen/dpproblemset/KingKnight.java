package com.bayviewglen.dpproblemset;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KingKnight {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new FileReader("data/KingKnight.dat"));
		int lim = Integer.parseInt(input.readLine());
		for (int j = 0; j < lim; j++) {
		/* Question asks for num of ways to get to end, not best num of moves to get to end*/
		int dimens = Integer.parseInt(input.readLine());
		String[] startS = input.readLine().split(" ");
		String[] endS = input.readLine().split(" ");
		int [] end = new int[2];
		int [] start = new int[2];
		for (int i = 0; i < 2; i++) {
			start[i] = Integer.parseInt(startS[i]);
			end[i] = Integer.parseInt(endS[i]);
		}
		int [] curr = start;
		//int [][] board = new int[dimens][dimens];
		int moves = 0;
		
		//first is distance to end
		//sec y, third is x
		double[][] sln = new double[1000][3]; 
		for (int i =0; i < sln[0].length; i++) {
			sln[i][0] = Math.hypot(Math.abs(start[0] - end[0]) ,Math.abs(start[1] - end[1]));
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
			
			if (yDir==0) {
				if (curr[0] == 0)
					yDir = 1;
				else if (curr[0] == dimens-1)
					yDir = -1;
				else 
					yDir = 1;
			}
			if (xDir==0) {
				if (curr[1] == 0)
					xDir = 1;
				else if (curr[1] == dimens-1)
					xDir = -1;
				else 
					xDir = 1;
			}
			//to check for the up L is best sln
			if (curr[0] + 2*yDir < dimens && curr[1] + 1*xDir < dimens) {
				//to find which one has less moves toget to end
				if (Math.hypot(Math.abs(curr[0] + 2*yDir - end[0]),Math.abs(curr[1] + 1*xDir - end[1])) < sln[slnIndex][0]) {
					sln[slnIndex][0] = Math.hypot(Math.abs(curr[0] + 2*yDir - end[0]),Math.abs(curr[1] + 1*xDir - end[1]));
					sln[slnIndex][1] = curr[0] + 2*yDir;
					sln[slnIndex][2] = curr[1] + 1*xDir;
				}
			}
			//to check for side L 
			if (curr[0] + 1*yDir < dimens && curr[1] + 2*xDir < dimens) {
				//to find which one has less moves toget to end
				if (Math.hypot(Math.abs(curr[0] + 1*yDir - end[0]),Math.abs(curr[1] + 2*xDir - end[1])) < sln[slnIndex][0]) {
					sln[slnIndex][0] = Math.hypot(Math.abs(curr[0] + 1*yDir - end[0]),Math.abs(curr[1] + 2*xDir - end[1]));
					sln[slnIndex][1] = curr[0] + 1*yDir;
					sln[slnIndex][2] = curr[1] + 2*xDir;
				}
			}
			//to check for diag 
			if (curr[0] + 1*yDir < dimens && curr[1] + 1*xDir < dimens) {
				//to find which one has less moves toget to end
				if (Math.hypot(Math.abs(curr[0] + 1*yDir - end[0]),Math.abs(curr[1] + 1*xDir - end[1])) < sln[slnIndex][0]) {
					sln[slnIndex][0] = Math.hypot(Math.abs(curr[0] + 1*yDir - end[0]),Math.abs(curr[1] + 1*xDir - end[1]));
					sln[slnIndex][1] = curr[0] + 1*yDir;
					sln[slnIndex][2] = curr[1] + 1*xDir;
				}
			}
			//vert adjacent 
			if (curr[0] + 1*yDir < dimens) {
				//to find which one has less moves toget to end
				if (Math.hypot(Math.abs(curr[0] + 1*yDir - end[0]),Math.abs(curr[1] - end[1])) < sln[slnIndex][0]) {
					sln[slnIndex][0] = Math.hypot(Math.abs(curr[0] + 1*yDir - end[0]),Math.abs(curr[1] - end[1]));
					sln[slnIndex][1] = curr[0] + 1*yDir;
					sln[slnIndex][2] = curr[1];
				}
			}
			
			if (curr[1] + 1*xDir < dimens) {
				//to find which one has less moves toget to end
				if (Math.hypot(Math.abs(curr[0] - end[0]),Math.abs(curr[1] + 1*xDir - end[1])) < sln[slnIndex][0]) {
					sln[slnIndex][0] =Math.hypot(Math.abs(curr[0] - end[0]),Math.abs(curr[1] + 1*xDir - end[1]));
					sln[slnIndex][1] = curr[0];
					sln[slnIndex][2] = curr[1] + 1*xDir;
				}
			}
			curr[0] = (int) sln[slnIndex][1];
			curr[1] = (int) sln[slnIndex][2];
			slnIndex++;
			sln[slnIndex][0] = sln[slnIndex-1][0];
		}
		
		System.out.println(slnIndex);
	}
	}

}
