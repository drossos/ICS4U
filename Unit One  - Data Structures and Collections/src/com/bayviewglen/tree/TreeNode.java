package com.bayviewglen.tree;

import com.bayviewglen.arrays.Contact;

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
		if (o instanceof Contact) {
			if (this.data instanceof Contact) {
				return ((Contact)(this.data)).getLname().compareTo(((Contact)(o)).getLname());
			}
			return this.data.toString().compareTo(((Contact)(o)).getLname());
		}
		if (this.data instanceof Contact && o instanceof String)
			return (((Contact)(this.data)).getLname().compareTo((String)o));
		
		Comparable x = (Comparable)o;
		return this.getData().toString().compareTo(x.toString());
	}
	
	
	
}
