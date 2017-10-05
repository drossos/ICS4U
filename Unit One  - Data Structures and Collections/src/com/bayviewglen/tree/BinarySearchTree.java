package com.bayviewglen.tree;

public class BinarySearchTree {
	private TreeNode root;
	public BinarySearchTree(TreeNode root) {
		super();
		this.root = root;
	}

	public BinarySearchTree() {
		super();
		this.root = null;
	}

	public void inorderTraversal(TreeNode current) {
		if (current.getLeft() != null)
			inorderTraversal(current.getLeft());
		evaluate(current);
		if (current.getRight() != null)
			inorderTraversal(current.getRight());
	}

	public void preorderTraversal(TreeNode current) {
		evaluate(current);
		if (current.getLeft() != null)
			preorderTraversal(current.getLeft());
		if (current.getRight() != null)
			preorderTraversal(current.getRight());
	}

	public void postorderTraversal(TreeNode current) {
		if (current.getLeft() != null)
			postorderTraversal(current.getLeft());
		if (current.getRight() != null)
			postorderTraversal(current.getRight());
		evaluate(current);
	}

	public void add(TreeNode currentParent, Comparable x) {
		if (currentParent == null) {
			currentParent = new TreeNode(x);
		} else if (currentParent.compareTo(x) > 0 && currentParent.getLeft() != null) {
			add(currentParent.getLeft(), x);
		} else if (currentParent.compareTo(x) <= 0 && currentParent.getRight() != null) {
			add(currentParent.getRight(), x);
		} else if (currentParent.compareTo(x) > 0  && currentParent.getLeft() == null) {
			currentParent.setLeft(new TreeNode(x));
		} else if (currentParent.compareTo(x) <= 0 && currentParent.getRight() == null) {
			currentParent.setRight(new TreeNode(x));
		}
	}

	public void add(Comparable x) {
		if (root == null) {
			TreeNode temp = new TreeNode(x);
			root = temp;
		} else
			add(root, x);
	}

	public boolean remove(Comparable x) {
		if (root == null)
			return false;
		else
			return remove(x, root);
	}

	private boolean remove(Comparable x, TreeNode node) {
		//WHEN CHECKING .EQUALS EVERYTHING NEEDS TO BE PUT TO STRING SO ALL TYPES OF DATA
		//CAN BE USED
		//checks if needs to go right
		if (node.compareTo(x) < 0 && !node.getRight().getData().toString().equals(x.toString()))
			return remove(x, node.getRight());
		//checks if needs to go left
		else if (node.compareTo(x) > 0 && !node.getRight().getData().equals(x.toString()))
			return remove(x, node.getLeft());
		//checks if empty
		else if (node.compareTo(x) != 0 && node.getRight() == null && node.getLeft() == null)
			return false;
		else {
			/*
			 * if (node.getLeft() == null && node.getRight() == null) { node = null; return
			 * true; } else
			 */
			//this check if the parent root node is the one to be removed
			if (node.compareTo(x) < 0 && node.getRight().getData().toString().equals(x.toString())) {
				TreeNode toRemove = node.getRight();
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
					TreeNode replace = findLargest(toRemove.getLeft());
					toRemove.setData(replace.getData());
					remove(replace.getData(), toRemove);
					return true;
				}
			} else {
				TreeNode toRemove = node.getLeft();
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
					TreeNode replace = findLargest(toRemove.getLeft());
					toRemove.setData(replace.getData());
					remove(replace.getData(), toRemove);
					return true;
				}
			}
		}
		
	}

	private void evaluate(TreeNode current) {
		System.out.println(current.getData().toString());
	}

	public TreeNode findSmallest() {
		if (root == null)
			return null;
		else
			return findSmallest(root);

	}

	public TreeNode search(Comparable x) {
		if (root == null)
			return null;
		else
			return search(x, root);
	}

	private TreeNode search(Comparable x, TreeNode node) {
		if (node.compareTo(x) > 0)
			return search(x, node.getRight());
		else if (node.compareTo(x) < 0)
			return search(x, node.getLeft());
		else
			return node;
	}

	private TreeNode findSmallest(TreeNode node) {
		if (node.getLeft() == null)
			return node;
		else
			return findSmallest(node.getLeft());

	}

	public TreeNode findLargest() {
		if (root == null)
			return null;
		else
			return findLargest(root);
	}

	private TreeNode findLargest(TreeNode node) {
		if (node.getRight() == null)
			return node;
		else
			return findLargest(node.getRight());
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

}
