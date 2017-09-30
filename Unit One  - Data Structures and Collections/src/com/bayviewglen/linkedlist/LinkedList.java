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

	
	/**
	 * creates new IntNode point head towards it
	 * @param newFront value that will be the new head
	 */
	public void addFirst(int newFront) {
		head = new IntNode(newFront, head);
		numNodes++;
		if (numNodes == 1)
			tail = head;

	}

	
	/**
	 * adds to end and updates tail and head if needed
	 * @param toAdd new value to be added
	 * @return true if was able to add element to list
	 */
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
	
	/**
	 * Inserts the specified element at the specified position in this list.
	 * @param index position to add value at
	 * @param toAdd value to be added
	 * @return true if was able to add element to list
	 */
	public boolean add(int index, int toAdd) {
		if (index > numNodes)
			throw new IndexOutOfBoundsException();
		else if (index == numNodes) {
			add(toAdd);
			return true;
		}
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
	
	/**
	 * Removes all of the elements from this list.
	 * @return true is was able to clear list
	 */
	public boolean clear() {
		head = null;
		tail = null;
		numNodes = 0;
		return true;
	}

	
	/**
	 * @param x value to be check if in list
	 * @return  true if this list contains the specified element.
	 */
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

	
	/**
	 * @param index specified position to be taken from list
	 * @return element at specified position
	 */
	public int get(int index) {
		if (index >= numNodes)
			throw new IndexOutOfBoundsException();
		IntNode curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.getLink();
		}
		return curr.getData();
	}


	/**
	 * @return the first element in this list.
	 */
	public int getFirst() {
		if (numNodes == 0)
			throw new IndexOutOfBoundsException();
		return head.getData();
	}


	/**
	 * @return the last element in this list.
	 */
	public int getLast() {
		if (numNodes == 0)
			throw new IndexOutOfBoundsException();
		IntNode curr = head;
		for (int i = 0; i < numNodes-1; i++) {
			curr = curr.getLink();
		}
		return curr.getData();
	}


	/**
	 * @param index specified position in this list.
	 * @return element that was in position before was removed
	 */
	public int remove(int index) {
		if (index >= numNodes)
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

	
	/**
	 * Removes first element from this list.
	 * @return returns element that used to be in first position
	 */
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
	
	
	/**
	 * Removes the last element from this list.
	 * @return element that used to be last in the list
	 */
	public int removeLast() {
		if (numNodes == 0)
			throw new IndexOutOfBoundsException();
		IntNode curr = head;
		//Iterates to last 
		for (int i = 1; i < numNodes-1; i++) {
			curr = curr.getLink();
		}
		//sets curr to new tail
		int temp = curr.getLink().getData();
		curr.setLink(null);
		tail = curr;
		numNodes--;
		return temp;
	}

	
	/**
	 * Removes the first occurrence of the specified element in this list (when traversing the list from head to tail).
	 * @param x specified element to be removed from list
	 * @return True if element was found and removed
	 */
	public boolean removeFirstOccurance(int x) {
		if (numNodes == 0)
			return false;
		IntNode curr = head;
		//first checks if first element
		if (curr.getData() == x) {
			numNodes--;
			head = curr.getLink();
			return true;
		}
		//then check rest of list 
		for (int i = 1; i < numNodes-1; i++) {
			curr = curr.getLink();
			//if occurance of in then gets rid of it
			if (curr.getLink().getData() == x) {
				curr.setLink(curr.getLink().getLink());
				numNodes--;
				//checks if new list is empty
				if (numNodes == 0) {
					tail = null;
					head = null;
				}
				//check if there is a new tail
				if (i+1 == numNodes)
					tail = curr;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes the first occurrence of the specified element in this list (when traversing the list from head to tail).
	 * @param x specified element to be removed from list
	 * @return True if element was found and removed
	 */
	public boolean removeLastOccurance(int x) {
		if (numNodes == 0)
			return false;
		IntNode curr = head;
		IntNode isHere = null;
		int index = -1;
		//first check the first element in list
		if (curr.getData() == x) {
			index  = 0;
			isHere = head;
		}
		//then goes to check the next element of each element
		//gets element to before so able to remove
		for (int i = 0; i < numNodes-1; i++) {
			if (curr.getLink().getData() == x) {
				index = i;
				isHere = curr;
			}
			curr = curr.getLink();
		}
		//check if last occurrence is the first element
		//if so it is removing first
		if (index == 0) {
			removeFirst();
			return true;
		}
		//check if not in the list and if so removes
		if (index == -1) {
			return false;
		}
		//other wise removes normaly
		numNodes--;
		isHere.setLink(isHere.getLink().getLink());
		return true;
		
		//old code that was not as good
		/*
		IntNode search = head;
		for (int i = 0; i < index; i++) {
			search = search.getLink();
		}
		if (index != -1) {
			remove(index);
			return true;
			
			curr.setLink(curr.getLink().getLink());
			numNodes--;
			if (numNodes == 0) {
				tail = null;
				head = null;
			}
			if (index == numNodes)
				tail = curr;
			return true;
			
		} */
		
	}
	
	
	/**
	 * Replaces the element at the specified position in this list with the specified element.
	 * @param index specified position in list
	 * @param x value that you want to set in list
	 * @return value that was in position before it was replaced
	 */
	public int set(int index, int x) {
		if (index > numNodes)
			throw new IndexOutOfBoundsException();
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
	

	/**
	 * @return the number of elements in this list.
	 */
	public int size() {
		return numNodes;
	}
	
	
	/**
	 * @return  an array containing all of the elements in this list in proper sequence (from first to last element).
	 */
	public int[] toArray() {
		int [] temp = new int [numNodes];
		IntNode curr = head;
		for (int i  = 0; i < numNodes-1; i++) {
			temp[i] = curr.getData();
			curr = curr.getLink();
		}
		return temp;
	}
}
