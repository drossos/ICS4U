package com.bayviewglen.tree;

public class TreeNode implements Comparable {
	private TreeNode left;
	private TreeNode right;
	private Comparable data;
	
	public TreeNode () {
		this.left = null;
		this.right = null;
		data = null;
	}
	
	public TreeNode(Comparable data) {
		super();
		this.data = data;
	}
	
	public TreeNode (TreeNode left, TreeNode right) {
		super();
		this.left = null;
		this.right = null;
	}
	
	public TreeNode(Comparable data, TreeNode left, TreeNode right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public Comparable getData() {
		return data;
	}

	public void setData(Comparable data) {
		this.data = data;
	}

	@Override
	public int compareTo(Object o) {
		Comparable x = (Comparable)o;
		return this.getData().compareTo(x);
	}
	
	
	
}
