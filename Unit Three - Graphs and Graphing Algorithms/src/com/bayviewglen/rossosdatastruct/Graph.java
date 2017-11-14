package com.bayviewglen.rossosdatastruct;

import java.util.LinkedList;

public class Graph {
	private int numVert;
	private int numEdge;
	private LinkedList<Integer>[] adjList;
	
	public Graph () {
		this.numVert = 0;
		this.numEdge = 0;
	}
	
	public Graph(int numVert) { 
		this.numVert = numVert;
		numEdge = 0;
		adjList = new LinkedList[numVert];
		
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new LinkedList();
		}
	}
	
	public void addEdge(int x, int y) {
		if (x > 0 && x < numVert+1 && y > 0 && y < numVert+1) {
			adjList[x].add(y);
			adjList[y].add(x);
		} else {
			throw new IndexOutOfBoundsException("Not vertex at that point"); 
		}
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i =0; i < adjList.length; i++) {
			s.append("Vertex " + i + ": ");
			for (int j =0; j < adjList[i].size(); j++) {
				s.append("" + adjList[i].get(j));
			}
			s.append("\n");
		}
		
		return s.toString();
	}
}
