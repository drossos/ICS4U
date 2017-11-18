package com.bayviewglen.rossosdatastruct;

public class test {

	public static void main(String[] args) {
		Graph test = new Graph(5);
		test.addEdge(0, 4);
		test.addEdge(0, 2,2);
		test.addEdge(2,3,2);
		test.addEdge(0, 3,5);
		System.out.println(test.DFS(0, 3));
		//System.out.println(test.Dijkstra(0));
		
		Dijkstra d = new Dijkstra(test,0);
		
		System.out.println(d.minDistance(3));
	}

}
