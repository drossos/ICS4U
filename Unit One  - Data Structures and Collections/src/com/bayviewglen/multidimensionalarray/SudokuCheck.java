package com.bayviewglen.multidimensionalarray;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuCheck {

	public static void main(String[] args) {
		Scanner input = null;
		try {
			input = new Scanner(new File("data/sudoku.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		char [][] suduko = createSudoku(input);
		boolean valid = true;
		valid = checkSud(suduko,valid);
			System.out.print("This Suduko solution is ");
			if (valid)
				System.out.print("valid and solved");
			else 
				System.out.print("invalid and not solved");
				
		

	}

	private static boolean checkSud(char[][] suduko, boolean valid) {
		for (int i = 0; i < suduko.length;i++) {
			for (int j = 0; j<suduko[1].length;j++) {
				valid  = testRow(suduko[i][j],j,suduko[i]);
				if (!valid)
					return false;
				valid  = testColumn(suduko[i][j], i,j, suduko);
				if (!valid)
					return false;
				//TODO MAKE WORK INDEX OUT BOUNDS
				if (i %3 == 0 && j %3 == 0) {
					valid = testBox(suduko[i][j], j,i, suduko);
				}
					
						
			}
		}
		return valid;
		
	}

	private static boolean testBox(char c, int col, int row, char[][] suduko) {
		char test = (char) -1;
		boolean first = true;
		for (int i = row; i < row+3; i++) {
			first = true;
			for (int j = col; j < col + 3; j++) {
				if(!first) {
					if (test == suduko[i][j])
						return false;
				}
				if (first) {
					test = suduko[i][j];
					first = false;
				}
			}
		}
		return true;
	}

	private static boolean testColumn(char c, int row, int column, char[][] suduko) {
		for (int i = row+1; i < 9; i++) {
			if (suduko[i][column] == c)
				return false;
		}
		return true;
	}

	private static boolean testRow(char c, int coloumn, char[] rowPassed) {
		for (int i = coloumn+1; i < 9; i++) {
			if (c == rowPassed[i])
				return false;
		}
		return true;
		
	}

	private static char[][] createSudoku(Scanner input) {
		char [][] arr = new char[9][9];
		for (int i = 0; i < 9; i++) {
			arr[i] = input.nextLine().toCharArray();
			
		}
		return arr;
		
	}

}
