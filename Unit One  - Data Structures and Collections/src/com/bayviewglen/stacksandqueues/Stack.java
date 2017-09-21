package com.bayviewglen.stacksandqueues;

public class Stack {
	private Object[] stack;
	private int index;
	private int indexPop;
	
	public Stack () {
		stack = new Object[100];
		index = 0;
		
	}
	
	public void push (Object x) {
		stack[index++] = x;
	}
	
	public Object pop () {
		Object pop = stack[--index];
		return pop;
	}
	
	public Object peek() {
		Object pop = stack[index-1];
		return pop;
	}
	
	public boolean empty() {
		if (index == indexPop)
			return true;
		return false;
	}
}
