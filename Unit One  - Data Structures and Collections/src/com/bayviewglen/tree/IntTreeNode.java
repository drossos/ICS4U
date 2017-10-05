package com.bayviewglen.tree;
//NO LONGER NEEDED BECAUSE OF UPDATED TREENODE
public abstract class IntTreeNode extends TreeNode{

	private Integer data;
	
	
	public IntTreeNode()  {
		super();
		this.data = new Integer(0);
	}
	
	public IntTreeNode(int data) {
		super();
		this.data = new Integer(data);
	}
	
	public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {
		super(left,right);
		this.data = data;
	}
	@Override
	public Integer getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	
	@Override
	public int compareTo(Object o) {
		Integer x = ((IntTreeNode)(o)).getData();
		
		if (x.intValue() > this.getData().intValue() )
			return -1;
		if (x.intValue() < this.getData().intValue())
			return 1;
		else 
			return 0;
	}
	
}
