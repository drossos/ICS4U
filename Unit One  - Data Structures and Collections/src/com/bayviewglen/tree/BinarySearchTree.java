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
			preorderTraversal(current.getLeft());
		if (current.getRight() != null)
			preorderTraversal(current.getRight());
	}

	public void postorderTraversal(IntTreeNode current) {
		if (current.getLeft() != null)
			postorderTraversal(current.getLeft());
		if (current.getRight() != null)
			postorderTraversal(current.getRight());
		evaluate(current);
	}

	public void add(IntTreeNode currentParent, int x) {
		if (currentParent == null) {
			currentParent = new IntTreeNode(x);
		} else if (x < currentParent.getData() && currentParent.getLeft() != null) {
			add(currentParent.getLeft(), x);
		} else if (x >= currentParent.getData() && currentParent.getRight() != null) {
			add(currentParent.getRight(), x);
		} else if (x < currentParent.getData() && currentParent.getLeft() == null) {
			currentParent.setLeft(new IntTreeNode(x));
		} else if (x >= currentParent.getData() && currentParent.getRight() == null) {
			currentParent.setRight(new IntTreeNode(x));
		}
	}

	public void add(int x) {
		if (root == null) {
			IntTreeNode temp = new IntTreeNode(x);
			root = temp;
		} else
			add(root, x);
	}

	public boolean remove(int x) {
		if (root == null)
			return false;
		else
			return remove(x, root);
	}

	private boolean remove(int x, IntTreeNode node) {
		if (node.getData() < x && node.getRight().getData() != x)
			return remove(x, node.getRight());
		else if (node.getData() > x && node.getLeft().getData() != x)
			return remove(x, node.getLeft());
		else {
			/*
			 * if (node.getLeft() == null && node.getRight() == null) { node = null; return
			 * true; } else
			 */
			//this check if the parent root node is the one to be removed
			if (node.getData() < x && node.getRight().getData() == x) {
				IntTreeNode toRemove = node.getRight();
				if (toRemove.getLeft() == null && toRemove.getRight() == null) { 
					node.setRight(null); 
					return true; 
				} else if (toRemove.getLeft() == null && toRemove.getRight() != null) {
					node.setRight(toRemove.getRight());
					//remove(toRemove.getRight().getData());
					return true;
				} else if (toRemove.getLeft() != null && toRemove.getRight() == null) {
					node.setRight(toRemove.getLeft());
					//remove(toRemove.getLeft().getData());
					return true;
				} else {
					IntTreeNode replace = findLargest(toRemove.getLeft());
					toRemove.setData(replace.getData());
					remove(replace.getData(), toRemove);
					return true;
				}
			} else {
				IntTreeNode toRemove = node.getLeft();
				if (toRemove.getLeft() == null && toRemove.getRight() == null) { 
					node.setLeft(null); 
					return true; 
				} else if (toRemove.getLeft() == null && toRemove.getRight() != null) {
					node.setLeft(toRemove.getRight());
					//remove(toRemove.getRight().getData());
					return true;
				} else if (toRemove.getLeft() != null && toRemove.getRight() == null) {
					node.setLeft(toRemove.getLeft());
					//remove(toRemove.getLeft().getData());
					return true;
				} else {
					IntTreeNode replace = findLargest(toRemove.getLeft());
					toRemove.setData(replace.getData());
					remove(replace.getData(), toRemove);
					return true;
				}
			}
		}
		
	}

	private void evaluate(IntTreeNode current) {
		System.out.println(current.getData());
	}

	public IntTreeNode findSmallest() {
		if (root == null)
			return null;
		else
			return findSmallest(root);

	}

	public IntTreeNode search(int x) {
		if (root == null)
			return null;
		else
			return search(x, root);
	}

	private IntTreeNode search(int x, IntTreeNode node) {
		if (node.getData() < x)
			return search(x, node.getRight());
		else if (node.getData() > x)
			return search(x, node.getLeft());
		else
			return node;
	}

	private IntTreeNode findSmallest(IntTreeNode node) {
		if (node.getLeft() == null)
			return node;
		else
			return findSmallest(node.getLeft());

	}

	public IntTreeNode findLargest() {
		if (root == null)
			return null;
		else
			return findLargest(root);
	}

	private IntTreeNode findLargest(IntTreeNode node) {
		if (node.getRight() == null)
			return node;
		else
			return findLargest(node.getRight());
	}

	public IntTreeNode getRoot() {
		return root;
	}

	public void setRoot(IntTreeNode root) {
		this.root = root;
	}

}
