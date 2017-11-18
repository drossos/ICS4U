package com.bayviewglen.rossosdatastruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	public int numVert;
	public int numEdge;
	public LinkedList<MapNode>[] adjList;

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

	public int[][] Dijkstra(Graph g, int start) {
			List unSettled = new ArrayList();
			// [distance to get there][if that is locked 1= locked. 0 not locked] [where it came from]
			int[][] distance = new int[numVert][3];
			for (int i = 0; i < distance.length; i++) {
				distance[i][0] = Integer.MAX_VALUE;
			}
			unSettled.add(start);
			distance[start][0] = 0;
			// distance[start][1] = 1;
			int evalNode  = start;
			int minDist = Integer.MAX_VALUE;
			while (!unSettled.isEmpty()) {
				minDist = Integer.MAX_VALUE;
				//START HERE AGAIN ITTERATING THROUGH 
				for (int i = 0; i < adjList[evalNode].get(i).nodeName; i++) {
					if (distance[adjList[evalNode].get(i).nodeName][1] != 1) {
						unSettled.add(adjList[evalNode].get(i).nodeName);
						if (distance[adjList[evalNode].get(i).nodeName][0] > distance[i][0] + adjList[evalNode].get(i).edgeValue) {
						distance[adjList[evalNode].get(i).nodeName][0] = distance[i][0] + adjList[evalNode].get(i).edgeValue;
						distance[adjList[evalNode].get(i).nodeName][2] = evalNode;
						}
						
						if (adjList[evalNode].size() != 0) {
						minDist = Math.min(distance[adjList[evalNode].get(i).nodeName][0] , minDist);
						}
					}
					
					evalNode = minDist;
					unSettled.remove(evalNode);
					
				}
			}
			
			return distance;
		
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
