package com.bayviewglen.tree;

public class IntTreeNode implements Comparable{

	private Integer data;
	private IntTreeNode left;
	private IntTreeNode right;
	
	
	public IntTreeNode()  {
		super();
		this.data = new Integer(0);
		this.left = null;
		this.right = null;
	}
	
	public IntTreeNode(int data) {
		super();
		this.data = new Integer(data);
		this.left = null;
		this.right = null;
	}
	
	public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}
	public Integer getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public IntTreeNode getLeft() {
		return left;
	}
	public void setLeft(IntTreeNode left) {
		this.left = left;
	}
	public IntTreeNode getRight() {
		return right;
	}
	public void setRight(IntTreeNode right) {
		this.right = right;
	}

	
	//TODO MAKE SO COMPARES VIA COMPARABLE NOT BST
	@Override
	public int compareTo(Object o) {
		Integer x = ((IntTreeNode)(o)).getData();
		
		if (x.intValue() > this.getData().intValue() )
			return 1;
		return 0;
	}
	
}
