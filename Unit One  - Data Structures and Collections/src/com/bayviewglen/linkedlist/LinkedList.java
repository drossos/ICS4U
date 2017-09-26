package com.bayviewglen.linkedlist;

public class LinkedList {
	private IntNode head;
	private int numNodes;
	private IntNode tail;

	public LinkedList() {
		head = null;
		tail = null;
		numNodes = 0;
	}

	// creates new IntNode point head towards it
	public void addFirst(int newFront) {
		head = new IntNode(newFront, head);
		numNodes++;
		if (numNodes == 1)
			tail = head;

	}

	// adds to end and updates tail and head if needed
	// returns boolean based on if successful
	public boolean add(int toAdd) {
		if (numNodes == 0) {
			head = new IntNode(toAdd, null);
			tail = head;
			numNodes++;
		} else {
			IntNode temp = new IntNode(toAdd, null);
			tail.setLink(temp);
			tail = temp;
			numNodes++;
		}

		return true;
	}

	public boolean add(int index, int toAdd) {
		if (index == 0)
			addFirst(toAdd);
		else {
			IntNode previous = head;
			for (int i = 0; i < index-1; i++)
				previous = previous.getLink();

			if (index == 1 && numNodes == 1) {
				previous.setLink(tail = new IntNode(toAdd, previous.getLink()));
			} else
				previous.setLink(new IntNode(toAdd, previous.getLink()));
			numNodes++;
		}
		return true;
	}

	public boolean clear() {
		head = null;
		tail = null;
		numNodes = 0;
		return true;
	}

	// TODO OUTOFBOUNS
	public boolean contains(int x) {
		if (numNodes == 0)
			return false;
		IntNode curr = head;
		for (int i = 0; i < numNodes; i++) {
			if (curr.getData() == x) {
				return true;
			}
			curr = curr.getLink();
		}
		return false;
	}

	public int get(int index) {
		if (numNodes == 0)
			throw new IndexOutOfBoundsException();
		IntNode curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.getLink();
		}
		return curr.getData();
	}

	public int getFirst() {
		if (numNodes == 0)
			throw new IndexOutOfBoundsException();
		return head.getData();
	}

	public int getLast() {
		if (numNodes == 0)
			throw new IndexOutOfBoundsException();
		IntNode curr = head;
		for (int i = 0; i < numNodes-1; i++) {
			curr = curr.getLink();
		}
		return curr.getData();
	}

	public int remove(int index) {
		if (numNodes == 0)
			throw new IndexOutOfBoundsException();
		else if (index == 0) {
			return removeFirst();
		} else {
		IntNode prev = head;
		for (int i = 1; i < index; i++) {
			prev = prev.getLink();
		}
		IntNode temp = prev.getLink();
		prev.setLink(prev.getLink().getLink());
		numNodes--;
		if (index  == numNodes)
			tail = prev;
		return temp.getData();
		}
	}

	public int removeFirst() {
		if (numNodes == 0)
			throw new IndexOutOfBoundsException();
		IntNode oldHead = head;
		IntNode temp = head.getLink();
		head = temp;
		if (numNodes == 2) 
			head = tail;
		numNodes--;
		if (numNodes == 0)
			tail = null;
		return oldHead.getData();
	}
	
	public int removeLast() {
		if (numNodes == 0)
			throw new IndexOutOfBoundsException();
		IntNode curr = head;
		for (int i = 0; i < numNodes-2; i++) {
			curr = curr.getLink();
		}
		int temp = curr.getLink().getData();
		curr.setLink(null);
		tail = curr;
		return temp;
	}

	public boolean removeFirstOccurance(int x) {
		if (numNodes == 0)
			return false;
		IntNode curr = head;
		if (curr.getData() == x) {
			head = curr.getLink();
			return true;
		}
		for (int i = 1; i < numNodes-1; i++) {
			curr = curr.getLink();
			if (curr.getLink().getData() == x) {
				curr.setLink(curr.getLink().getLink());
				numNodes--;
				if (numNodes == 0) {
					tail = null;
					head = null;
				}
				if (i+1 == numNodes)
					tail = curr;
				return true;
			}
		}
		return false;
	}

	public boolean removeLastOccurance(int x) {
		if (numNodes == 0)
			return false;
		IntNode curr = head;
		int index = -1;
		for (int i = 0; i < numNodes; i++) {
			if (curr.getData() == x) {
				index = i;
			}
			curr = curr.getLink();
		}
		IntNode search = head;
		for (int i = 0; i < index; i++) {
			search = search.getLink();
		}
		if (index != -1) {
			remove(index);
			return true;
			/*
			curr.setLink(curr.getLink().getLink());
			numNodes--;
			if (numNodes == 0) {
				tail = null;
				head = null;
			}
			if (index == numNodes)
				tail = curr;
			return true;
			*/
		}
		return false;
	}
	
	public int set(int index, int x) {
		if (index == 0) {
			int temp = head.getData();
			head = new IntNode(x, head);
			if (numNodes == 1)
				tail = head;
			return temp;
		}
		else {
			IntNode curr = head;
			for (int i = 0; i < index; i++)
				curr = curr.getLink();
			IntNode temp = new IntNode(curr.getData(), curr.getLink());
			curr.setData(x);
			return temp.getData();
		}
		
	}
	
	public int size() {
		return numNodes;
	}
	
	public int[] toArray() {
		int [] temp = new int [numNodes];
		IntNode curr = head;
		for (int i  = 0; i < numNodes; i++) {
			temp[i] = curr.getData();
			curr = curr.getLink();
		}
		return temp;
	}
}
