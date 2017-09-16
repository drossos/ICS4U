package com.bayviewglen.stacksandqueues;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class StacksMathPractise {
	static final String OPERATORS = "+-*/";
	static final String OPERANDS = "1234567890";

	public static void main(String[] args) {
		Stack operators = new Stack();
		Stack operands = new Stack();
		Scanner input = null;

		try {
			input = new Scanner(new File("data/mathProblem.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String[] problem = input.nextLine().split(" ");

		for (int i = 0; i < problem.length; i++) {
			if (OPERATORS.indexOf(problem[i]) != -1 || problem[i].equals("("))
				operators.push(problem[i]);
			if (isNumeric(problem[i])) {
				if (!operands.empty() && OPERATORS.indexOf((String) operators.peek()) != -1) {
					operands.push(problem[i]);
					doTheMath(operands,operators);
				}
				else 
					operands.push(problem[i]);
			}
			if (problem[i].equals(")")) {
				boolean operatorFound = false;
				while(!operatorFound) {
					if (((String)(operators.peek())).equals("(")){
						operators.pop();
						operatorFound = true;
						
					}
					
					else if (OPERATORS.indexOf((String)(operators.peek())) != -1) {
						operands.push(problem[i]);
						doTheMath(operands,operators);
					}
				}
			}

		}
		if (!operators.isEmpty()) {
			doTheMath(operands, operators);
		}
		System.out.println("Java math: " + (( 13 + 56 ) * ( ( 20 + 3 + 3 ) )));
		System.out.println("Daniel math: " + operands.pop());
	}

	private static void doTheMath(Stack operands, Stack operators) {
		if (((String) (operators.peek())).equals("+")) {
			operators.pop();
			operands.push(""+(Integer.parseInt((String)operands.pop()) + Integer.parseInt((String)operands.pop())));
		}
		else if (((String) (operators.peek())).equals("-")) {
			operators.pop();
			operands.push(""+(Integer.parseInt((String)operands.pop()) - Integer.parseInt((String)operands.pop())));
		}
		else if (((String) (operators.peek())).equals("*")) {
			operators.pop();
			operands.push(""+(Integer.parseInt(((String)(operands.pop()))) * Integer.parseInt(((String)(operands.pop())))));
		}
		else if (((String) (operators.peek())).equals("/")) {
			operators.pop();
			operands.push(""+((Integer.parseInt((String)operands.pop()) / Integer.parseInt((String)operands.pop()))));
		}
		
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}
