package com.bayviewglen.tree;

public class TreeDriver {
		public static void main (String [] args) {
			BinarySearchTree test = new BinarySearchTree();
			test.add(new Integer(3));
			test.add(new Integer(5));
			test.add(new Integer(4));
			test.add(new Integer(2));
			test.add(new Integer(3));
			test.add(new Integer(1));
			test.add(new Integer(80));
			test.add(new Integer(0));
			test.inorderTraversal(test.getRoot());
			System.out.println();
			test.postorderTraversal(test.getRoot());
			System.out.println();
			test.inorderTraversal(test.getRoot());
			System.out.println();System.out.println();System.out.println();
			test.remove(5);
			System.out.println();
			test.inorderTraversal(test.getRoot());
			
		}
}
