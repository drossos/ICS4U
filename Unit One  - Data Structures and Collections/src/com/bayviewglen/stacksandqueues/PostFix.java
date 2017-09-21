package com.bayviewglen.stacksandqueues;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class PostFix {

	static final String OPERATORS = "+-*/";
	static final String OPERANDS = "1234567890";

	public static void main(String[] args) {
		Stack stack = new Stack();
		Scanner input = null;

		try {
			input = new Scanner(new File("data/postFix.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (input.hasNextLine()) {

			String[] problem = input.nextLine().split(" ");

			for (int i = 0; i < problem.length; i++) {
				if (OPERANDS.indexOf(problem[i]) != -1) {
					stack.push(problem[i]);
				} else if (OPERATORS.indexOf(problem[i]) != -1) {
					doMath(stack, problem, problem[i]);
				}
			}

			System.out.println("Daniel math: " + stack.pop());
			
		}

	}

	private static void doMath(Stack stack, String[] problem, String string) {
		if (string.equals("+")) {
			stack.push("" + (Integer.parseInt((String) stack.pop()) + Integer.parseInt((String) stack.pop())));
		} else if (string.equals("-")) {
			stack.push("" + (-1*Integer.parseInt((String) stack.pop()) + Integer.parseInt((String) stack.pop())));
		} else if (string.equals("*")) {
			stack.push("" + (Integer.parseInt(((String) (stack.pop()))) * Integer.parseInt(((String) (stack.pop())))));
		} else if (string.equals("/")) {
			int first = Integer.parseInt((String) stack.pop());
			int second = Integer.parseInt((String) stack.pop());
			stack.push("" + (second / first));
		}

	}

}
