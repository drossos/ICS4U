package com.bayviewglen.nqueens;

import java.io.BufferedReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class NQueens {

	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		int n = Integer.parseInt(read.nextLine());
		solveQueens(n);
	}

	public static void solveQueens(int n) {
		boolean validSln = true;
		n -= 1;
		int filled = 1;
		Stack<Queen> stack = new Stack<Queen>();
		stack.push(new Queen(0, 0));
		int row = 1;
		int col = 0;
		while (filled <= n) {
			if (stack.size() == 0 && col > n) {
				System.out.println("No Soloution");
				validSln = false;
				break;
			}
			if (!conflict(col, row, stack) && col <= n) {
				stack.push(new Queen(col, row));
				filled++;
				row++;
				col = 0;
			} else if (conflict(col, row, stack) && col <= n) {
				col++;

		/*	} else if (stack.size() == 1 && stack.peek().getX() == n) {
				System.out.println("No Soloution");
				validSln = false;
				break;*/
			}
			/*if(col > n && stack.size() == 1) {
				System.out.println("No Soloution");
				validSln = false;
				break;
			}*/
			if (col > n) {
				filled--;
				col = stack.peek().getX() + 1;
				stack.pop();
				row--;
			}

		}
		if (validSln) {
			int temp = stack.size();
			boolean foundForRow = false;
			for (int i = 0; i < temp; i++) {
				for (int k = 0; k < temp; k++) {
					if (!foundForRow && stack.peek().getX() == k) {
						stack.pop();
						System.out.print("Q ");
						foundForRow = true;
					} else
						System.out.print(". ");
				}
				foundForRow = false;
				System.out.println();
			}
		}

	}

	private static boolean conflict(int col, int row, Stack<Queen> stack) {
		Iterator<Queen> iter = stack.iterator();

		for (int i = stack.size() - 1; i >= 0; i--) {

			if (stack.get(i).getX() == col)
				return true;
			/*else if (Math.abs((row - stack.get(i).getY()) / (col - stack.get(i).getX())) == 1)*/
			else if (Math.abs(row-stack.get(i).getY()) == Math.abs(col - stack.get(i).getX()))
				return true;
		}
		return false;
	}

}
