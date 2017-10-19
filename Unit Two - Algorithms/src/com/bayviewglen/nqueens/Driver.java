package com.bayviewglen.nqueens;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Driver {

	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		int n = Integer.parseInt(read.nextLine());

		solveQueens(n);
	}

	private static void solveQueens(int n) {
		n -= 1;
		int filled = 1;
		Stack<Queen> stack = new Stack<Queen>();
		stack.push(new Queen(0, 0));
		int row = 1;
		int col = 0;
		while (filled <= n) {
			if (!conflict(col, row, stack) && row <= n) {
				stack.push(new Queen(col, row));
				filled++;
				row++;
				col = 0;
			} else if (conflict(col, row, stack) && row <= n) {
				col++;
				
			} else if (stack.size() == 1 && stack.peek().getX() == n) {
				System.out.println("No Soloution");
				break;
			}
			
			if (col > n) {
				filled--;
				stack.pop();
				col = stack.peek().getX() + 1;
				row--;
			}

		}

		for (int i = 0; i <= n; i++) {
			for (int k = 0; k <= n; k++) {
				if (stack.peek().getX() == k) {
					stack.pop();
					System.out.print("Q ");
				} else
					System.out.print(". ");
			}
			System.out.println();
		}

	}

	private static boolean conflict(int col, int row, Stack<Queen> stack) {
		Iterator<Queen> iter = stack.iterator();

	for (int i = stack.size()-1; i >= 0; i --) {

			if (stack.get(i).getX() == col)
				return true;
			else if (Math.abs((row - stack.get(i).getY()) / (col - stack.get(i).getX())) == 1)
				return true;
		}
		return false;
	}

}
