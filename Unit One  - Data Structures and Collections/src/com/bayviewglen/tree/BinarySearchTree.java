package com.bayviewglen.tree;

public class BinarySearchTree {
	private IntTreeNode root;

	public BinarySearchTree(IntTreeNode root) {
		super();
		this.root = root;
	}

	public BinarySearchTree() {
		super();
		this.root = null;
	}

	public void inorderTraversal(IntTreeNode current) {
		if (current.getLeft() != null)
			inorderTraversal(current.getLeft());
		evaluate(current);
		if (current.getRight() != null)
			inorderTraversal(current.getRight());
	}

	public void preorderTraversal(IntTreeNode current) {
		evaluate(current);

		if (current.getLeft() != null)
			inorderTraversal(current.getLeft());
		if (current.getRight() != null)
			inorderTraversal(current.getRight());
	}

	public void postorderTraversal(IntTreeNode current) {
		if (current.getLeft() != null)
			inorderTraversal(current.getLeft());
		if (current.getRight() != null)
			inorderTraversal(current.getRight());
		evaluate(current);
	}

	public void add(IntTreeNode current, int x) {
		if (current == null) {
			IntTreeNode temp = new IntTreeNode(x);
			current = temp;
		} else if (x < current.getData()) {
			add(current.getLeft(), x);
		} else if (x >= current.getData()) {
			add(current.getRight(), x);
		}
	}

	public void add(int x) {
		if (root == null) {
			IntTreeNode temp = new IntTreeNode(x);
			root = temp;
		} else
			add(root, x);
	}

	private void evaluate(IntTreeNode current) {
		System.out.println(current.getData());
	}

	public IntTreeNode getRoot() {
		return root;
	}

	public void setRoot(IntTreeNode root) {
		this.root = root;
	}

}
