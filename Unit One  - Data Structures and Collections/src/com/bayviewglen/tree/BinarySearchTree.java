package com.bayviewglen.tree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.bayviewglen.arrays.Contact;

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

	//places new node relative to other things in the tree
	//has to be places in reference to it's parent
	public void add(TreeNode currentParent, Comparable x) {
		if (currentParent == null) {
			currentParent = new TreeNode(x);
		} else if (currentParent.compareTo(x) > 0 && currentParent.getLeft() != null) {
			add(currentParent.getLeft(), x);
		} else if (currentParent.compareTo(x) <= 0 && currentParent.getRight() != null) {
			add(currentParent.getRight(), x);
		} else if (currentParent.compareTo(x) > 0 && currentParent.getLeft() == null) {
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

	public boolean remove(Comparable x, TreeNode node) {
		/*
		 * WHEN CHECKING .EQUALS EVERYTHING NEEDS TO BE PUT TO STRING SO ALL TYPES OF
		 * DATA CAN BE USED
		 */	
		
		//This initial part is used to check if the root is what needs to be removed which is a special case
		if (node.compareTo(x) == 0) {
			//if only root that needs to be removed and only root in tree
			//can make tree null
			if (node.getRight() == null && node.getLeft() == null) {
				root = null;
				return true;
			//If there is no left branch then must get the smallest from right to replace it
			} else if (node.getLeft() == null) {
				TreeNode replace = findSmallest(node.getRight());
				node.setData(replace.getData());
				//this checks if the node to the right is biggest and if it is, is it also a "terminal" node, if so just make that null
				if (node.getRight().getData().toString().equals(replace.getData().toString()) && node.getRight().getRight() == null && node.getRight().getLeft() == null)
					node.setRight(null);
				//otherwise you must just treat this like now a regular removal
				else
					remove(replace.getData(), node.getRight());
				return true;
				
				//This is same as case above, but using left branch because right could potentialy be null
			} else {
				TreeNode replace = findLargest(node.getLeft());
				node.setData(replace.getData());
				if (node.getLeft().getData().toString().equals(replace.getData().toString()) && node.getLeft().getRight() == null && node.getLeft().getLeft() == null)
					node.setLeft(null);
				else
					remove(replace.getData(), node.getLeft());
				return true;
				/*
				 * node.setData(node.getLeft().getData()); return
				 * remove(node.getLeft().getData(), node.getLeft());
				 */
				// x = node.getLeft().getData();
			}

		}
		// checks if needs to go right and if we are not on parent of what needs to be removed then calls to go that way
		if (node.compareTo(x) <= 0 && node.getRight() != null
				&& !node.getRight().getData().toString().equals(x.toString()))
			return remove(x, node.getRight());
		else if (node.compareTo(x) <= 0 && node.getRight() == null)
			return false;
		// checks if needs to go left and if we are not on parent of what needs to be removed then calls to go that way
		else if (node.compareTo(x) >= 0 && node.getLeft() != null
				&& !node.getLeft().getData().toString().equals(x.toString()))
			return remove(x, node.getLeft());
		else if (node.compareTo(x) >= 0 && node.getLeft() == null)
			return false;

		// checks if at the end of a branch and still is not the value looking for, then returns false, since nothing removed
		else if (node.compareTo(x) != 0 && node.getRight() == null && node.getLeft() == null)
			return false;
		//if we are on a parent node to what needs to be removed
		else {
			/*
			 * if (node.getLeft() == null && node.getRight() == null) { node = null; return
			 * true; } else
			 */
			
			//if the right child is what needs to be removed
			if (node.compareTo(x) <= 0 && node.getRight().getData().toString().equals(x.toString())) {
				TreeNode toRemove = node.getRight();
				//and has not children, just make it null
				if (toRemove.getLeft() == null && toRemove.getRight() == null) {
					node.setRight(null);
					return true;
					//if what needs to be removed has no left branch, set the right of the parent to be right of 
					//what needs to go, reference goes from what what to remove to what is after it
				} else if (toRemove.getLeft() == null && toRemove.getRight() != null) {
					node.setRight(toRemove.getRight());
					// remove(toRemove.getRight().getData());
					return true;
					//same as above but left side
				} else if (toRemove.getLeft() != null && toRemove.getRight() == null) {
					node.setRight(toRemove.getLeft());
					// remove(toRemove.getLeft().getData());
					return true;
				//otherwise have to find the largest value on the left child, set the parent data to that 
				//then remove that largest left child node after
				} else {
					TreeNode replace = findLargest(toRemove.getLeft());
					toRemove.setData(replace.getData());
					remove(replace.getData(), toRemove);
					return true;
				}
			//same as above, except for the left child not the right one
			} else {
				TreeNode toRemove = node.getLeft();
				if (toRemove.getLeft() == null && toRemove.getRight() == null) {
					node.setLeft(null);
					return true;
				} else if (toRemove.getLeft() == null && toRemove.getRight() != null) {
					node.setLeft(toRemove.getRight());
					// remove(toRemove.getRight().getData());
					return true;
				} else if (toRemove.getLeft() != null && toRemove.getRight() == null) {
					node.setLeft(toRemove.getLeft());
					// remove(toRemove.getLeft().getData());
					return true;
				} else if (toRemove.getLeft() != null && toRemove.getRight() != null) {
					TreeNode replace = findLargest(toRemove.getLeft());
					toRemove.setData(replace.getData());
					remove(replace.getData(), toRemove);
					return true;
				} else {
					return false;
				}

			}

		}

	}

	private void evaluate(TreeNode current) {
		//here since this tree for contact list checks if it is a Contact and if it is prints the format to see contacts
		if (current.getData() instanceof Contact)
			System.out.println(((Contact) (current.getData())).forSaving());
		else
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
		if (node.compareTo(x) < 0)
			if (node.getRight() != null)
				return search(x, node.getRight());
			else
				return null;
		else if (node.compareTo(x) > 0)
			if (node.getLeft() != null)
				return search(x, node.getLeft());
			else
				return null;
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
	
	//for an older version needed to convert to ArrayList, but not used in current AddressBook
	public ArrayList toArray(TreeNode current) {
		ArrayList result = new ArrayList<>();
		if (current == null) {
			return result;
		}
		if (current.getLeft() != null) {
			result.addAll(toArray(current.getLeft()));
		}

		if (current.getRight() != null) {
			result.addAll(toArray(current.getRight()));
		}

		result.add(current.getData());

		return result;

	}

	private TreeNode findLargest(TreeNode node) {
		if (node.getRight() == null)
			return node;
		else
			return findLargest(node.getRight());
	}
	
	//This just writes to a file that is alread passed in via the file writer
	public void saveToFile(FileWriter fw, TreeNode current) throws IOException {
		if (current.getData() instanceof Contact) {
			fw.write(((Contact) (current.getData())).forSaving());
		} else
			fw.write(((Contact) (current.getData())).forSaving());
		fw.write(System.getProperty("line.separator"));

		if (current.getLeft() != null)
			saveToFile(fw, current.getLeft());
		if (current.getRight() != null)
			saveToFile(fw, current.getRight());
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

}
