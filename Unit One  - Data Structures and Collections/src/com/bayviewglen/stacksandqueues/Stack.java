package com.bayviewglen.stacksandqueues;

public class Stack {
	private Object[] stack;
	private int index;
	
	
	public Stack () {
		stack = new Object[100];
		index = 0;
		
	}
	
	public void push (Object x) {
		stack[index++] = x;
	}
	
	public Object pop () {
		Object pop = stack[--index];
		if (pop == null)
			throw new NullPointerException();
		return pop;
	}
	
	public Object peek() {
		Object pop = stack[index-1];
		if (pop == null)
			throw new NullPointerException();
		return pop;
	}
	
	public boolean empty() {
		if (stack[index-1] == null )
			return true;
		return false;
	}
}
