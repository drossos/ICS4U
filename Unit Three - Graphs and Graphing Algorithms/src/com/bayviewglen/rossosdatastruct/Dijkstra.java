package com.bayviewglen.rossosdatastruct;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dijkstra {
	private int[][] minPath;

	public Dijkstra(Graph g, int start) {
		minPath = Dijkstra(g, start);
	}

	public int[][] Dijkstra(Graph g, int start) {
		List unSettled = new ArrayList();
		// [distance to get there][if that is locked 1= locked. 0 not locked] [where it
		// came from]
		int[][] distance = new int[g.numVert][3];
		for (int i = 0; i < distance.length; i++) {
			distance[i][0] = Integer.MAX_VALUE;
		}
		unSettled.add(start);
		distance[start][0] = 0;
		 distance[start][1] = 1;
		int evalNode = start;
		int minDist = Integer.MAX_VALUE;
		while (!unSettled.isEmpty()) {
			minDist = Integer.MAX_VALUE;
			// START HERE AGAIN ITTERATING THROUGH
			for (int i = 0; i < g.adjList[evalNode].size(); i++) {
				if (distance[g.adjList[evalNode].get(i).nodeName][1] != 1) {
					if (distance[g.adjList[evalNode].get(i).nodeName][0] > distance[evalNode][0] + g.adjList[evalNode].get(i).edgeValue) {
						distance[g.adjList[evalNode].get(i).nodeName][0] = distance[evalNode][0] + g.adjList[evalNode].get(i).edgeValue;
						distance[g.adjList[evalNode].get(i).nodeName][2] = evalNode;
					}
					//fix this check for non reversable graphs TODO
					if (terminalNode(g.adjList[g.adjList[evalNode].get(i).nodeName], distance)) {
						if(distance[g.adjList[evalNode].get(i).nodeName][0] < minDist) {
							minDist = g.adjList[evalNode].get(i).nodeName;
							unSettled.add(g.adjList[evalNode].get(i).nodeName);
						}
					}
				}
			}	
				unSettled.remove(unSettled.indexOf(evalNode));
				evalNode = minDist;
				if (evalNode == Integer.MAX_VALUE) {
					break;
				}
				distance[evalNode][1] = 1;

			
		}

		return distance;

	}
	
	private boolean terminalNode(LinkedList<MapNode> linkedList, int[][] distance) {
		boolean nonTerm = false;
		
		for (int i =0 ; i < linkedList.size(); i++) {
			if (distance[linkedList.get(i).nodeName][1] != 1) {
				nonTerm = true;
			}
		}
		return nonTerm;
	}

	public int minDistance (int endPoint) {
		if (endPoint < minPath.length) {
			return minPath[endPoint][0];
		}
		throw new IndexOutOfBoundsException("That point outside of graph");
	}
}
