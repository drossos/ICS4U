package com.bayviewglen.tree;

public class TreeDriver {
		public static void main (String [] args) {
			BinarySearchTree test = new BinarySearchTree();
			test.add(3);
			test.add(5);
			test.add(4);
			test.add(2);
			test.add(3);
			test.add(1);
			test.add(80);
			test.add(0);
			test.inorderTraversal(test.getRoot());
			System.out.println();
			test.postorderTraversal(test.getRoot());
			System.out.println();
			test.inorderTraversal(test.getRoot());
			System.out.println();System.out.println();System.out.println();
			test.remove(5);
			System.out.println();
			test.inorderTraversal(test.getRoot());
			Object x = new Integer (2);
			Object y = new Integer (4);
			
		}
}
