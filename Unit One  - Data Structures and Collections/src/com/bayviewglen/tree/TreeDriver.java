package com.bayviewglen.tree;

import com.bayviewglen.arrays.Contact;

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
			test.add(new Contact());
			test.inorderTraversal(test.getRoot());
			System.out.println();
			test.postorderTraversal(test.getRoot());
			System.out.println();
			test.inorderTraversal(test.getRoot());
			System.out.println();System.out.println();System.out.println();
			test.remove("DEFAULT_LAST_NAME");
			System.out.println();
			test.inorderTraversal(test.getRoot());
			System.out.println();
			System.out.println(new Integer(2).toString());
		}
}
