package com.bayviewglen.stacksandqueues;

public class Queue {
	private Object[] queue;
	private int indexAdd;
	private int indexPop;
	
	public Queue () {
		queue = new Object[100];
		indexAdd = 0;
		indexPop = 0;
		
	}
	
	public void enqueue (Object x) {
		queue[indexAdd++] = x;
	}
	
	public Object dequeue () {
		Object pop = queue[indexPop++];
		if (pop == null)
			throw new NullPointerException();
		return pop;
	}
	
	public Object peek() {
		Object pop = queue[indexPop];
		if (pop == null)
			throw new NullPointerException();
		return pop;
	}
	
	public boolean empty() {
		if (indexAdd == indexPop)
			return true;
		return false;
	}
	
	public void clear() {
		queue = new Object[100];
	}
}
