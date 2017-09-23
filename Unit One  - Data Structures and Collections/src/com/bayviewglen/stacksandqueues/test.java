package com.bayviewglen.stacksandqueues;

public class test {

	public static void main(String[] args) {
		Queue q = new Queue();
		Stack s = new Stack();
		System.out.println(s.empty());
		System.out.println(q.empty());
		q.enqueue("first");
		q.enqueue("Second");
		q.enqueue("Third");
		System.out.println("" + q.peek());
		System.out.println("" + q.dequeue());
		System.out.println(""+q.dequeue());
		System.out.println("" + q.peek());
		System.out.println("" + q.dequeue());
		s.push("s");
		q.enqueue("s");
		System.out.println(s.empty());
		System.out.println(q.empty());
		

	}

}
