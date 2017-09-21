package com.bayviewglen.stacksandqueues;

public class test {

	public static void main(String[] args) {
		Queue q = new Queue();
		q.enqueue("first");
		q.enqueue("Second");
		q.enqueue("Third");
		System.out.println("" + q.peek());
		System.out.println("" + q.dequeue());
		System.out.println(""+q.dequeue());
		System.out.println("" + q.peek());
		System.out.println("" + q.dequeue());
		

	}

}
