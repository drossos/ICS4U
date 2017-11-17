package com.bayviewglen.rossosdatastruct;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	private int numVert;
	private int numEdge;
	private LinkedList<MapNode>[] adjList;

	public Graph() {
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
		if (x >= 0 && x < numVert + 1 && y >= 0 && y < numVert + 1) {
			adjList[x].add(new MapNode(y));
			adjList[y].add(new MapNode(x));
		} else {
			throw new IndexOutOfBoundsException("Not vertex at that point");
		}
	}

	public void addEdge(int x, int y, int edgeValue) {
		if (x >= 0 && x < numVert + 1 && y >= 0 && y < numVert + 1) {
			adjList[x].add(new MapNode(y, edgeValue));
			adjList[y].add(new MapNode(x, edgeValue));
		} else {
			throw new IndexOutOfBoundsException("Not vertex at that point");
		}
	}

	public boolean DFS(Integer x, Integer endingPoint) {
		boolean[] visted;
		if (x >= numVert) {
			return false;
		} else {
			Stack<Integer> s = new Stack<Integer>();
			visted = new boolean[numVert];
			visted[x] = true;
			s.push(x);

			while (!s.isEmpty()) {
				int curr = s.pop();
				for (int i = 0; i < adjList[curr].size(); i++) {
					if (!visted[adjList[curr].get(i).nodeName]) {
						visted[adjList[curr].get(i).nodeName] = true;
						s.push(adjList[curr].get(i).nodeName);
					}
				}
			}

		}

		return (visted[endingPoint]);
	}

	public int Dijkstra(int start, int end) {
		if (!DFS(start, end)) {
			return -1;
		} else {
			// [distance to get there][if that is locked 1= locked. 0 not locked]
			int[][] distance = new int[numVert][2];
			for (int i = 0; i < distance.length; i++) {
				distance[i][0] = Integer.MAX_VALUE;
			}
			distance[start][0] = 0;
			// distance[start][1] = 1;
			//Queue q = new PriorityQueue();
			int curr, currShortest = 0;
			curr = start;
			boolean first = true;
			while (distance[end][1] != 1) {
				first = true;
				distance[curr][1] = 1;
				for (int i = 0; i < adjList[curr].size(); i++) {
					if (distance[adjList[curr].get(i).nodeName][1] != 1) {
						distance[adjList[curr].get(i).nodeName][0] = distance[curr][0] + adjList[curr].get(i).edgeValue;
						if (first) {
							currShortest = adjList[curr].get(i).nodeName;
							first = false;
						} else {
							if (distance[adjList[curr].get(i).nodeName][0] < distance[currShortest][0] ||distance[adjList[curr].get(i).nodeName][0] == distance[currShortest][0] && adjList[curr].get(i).nodeName == end) {
								currShortest = adjList[curr].get(i).nodeName;
							}
						}
					}
				}
					curr = currShortest;
				
			}
			return distance[end][0];
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < adjList.length; i++) {
			s.append("Vertex " + i + ": ");
			for (int j = 0; j < adjList[i].size(); j++) {
				s.append("" + adjList[i].get(j));
			}
			s.append("\n");
		}

		return s.toString();
	}
}
