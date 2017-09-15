package com.bayviewglen.multidimensionalarray;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GPSProblem {

	public static void main(String[] args) {
		double [][] grid = new double [8][2];
		Scanner input = null;
		try {
			input = new Scanner(new File("data/cordinates.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		populateGrid(input, grid);
		checkForLowest(grid);
		

	}

	private static void checkForLowest(double[][] grid) {
		double x1 = Integer.MAX_VALUE;
		double y1 = Integer.MAX_VALUE;
		double x2 = Integer.MAX_VALUE;
		double y2 = Integer.MAX_VALUE;
		double lowestDistance = Integer.MAX_VALUE;
		//first point followed by second point
		double [][] lowestPoints = new double [grid.length][4];
		int lowestCount = 0;
		for (int i = 0; i < 8; i++) {
			x1 = grid[i][0];
			y1 = grid[i][1];
			for (int j = i+1; j < 8; j++ ) {
				x2 = grid[j][0];
				y2 = grid[j][1];
				double length = pythag(x1,y1,x2,y2);
				if (length <= lowestDistance) {
					lowestDistance = length;
					lowestPoints[lowestCount][0] = x1;
					lowestPoints[lowestCount][1] = y1;
					lowestPoints[lowestCount][2] = x2;
					lowestPoints[lowestCount][3] = y2;
					lowestCount++;
					
				}
				
			}
			
		}
		lowestPoints = trimArray(lowestPoints, lowestDistance);
		printLowPoints(ensureLowest(lowestPoints, lowestDistance));
		
	}

	private static List<double[]> ensureLowest(double[][] lowestPoints, double lowestDistance) {
		List<double[]> test = new ArrayList<double[]>();
		for (int i = 0; i < lowestPoints.length; i++) {
			if (pythag(lowestPoints[i][0], lowestPoints[i][1],lowestPoints[i][2],lowestPoints[i][3]) <= lowestDistance)
				test.add(lowestPoints[i]);
		}
		return test;
	}

	private static double[][] trimArray(double[][] lowestPoints, double lowestDistance) {
		double[][] arr = null;
		int lengthArr = 0;
		for(int i  = 0; i < lowestPoints.length; i++) {
			if (lowestPoints[i][0] != 0.0 && lowestPoints[i][3] != 0.0)
				lengthArr++;
		}
		arr = new double [lengthArr][4];
		for (int i = 0; i < lengthArr; i++) {
			arr[i] = lowestPoints[i];
		}
		return arr;
		
	}

	private static void printLowPoints(List<double[]> list) {
		System.out.println("The points closest together are:");
		for(int i = 0; i < list.size();i++) {
			
				System.out.println("(" + list.get(i)[0] + "," + list.get(i)[1] + ") and (" 
						+ list.get(i)[2] + "," + list.get(i)[3] + ")");
				
			
		}
		
	}

	private static double pythag(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(Math.abs(x2-x1), 2) + Math.pow(Math.abs(y2-y1), 2));
	}

	private static void populateGrid(Scanner input, double[][] grid) {
		for (int i = 0; i < 8;i++) {
			for (int j = 0; j < 2; j++) {
			grid[i][j] = input.nextDouble();
			}
		}
		
	}

}
