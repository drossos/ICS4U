package com.bayviewglen.rossosdatastruct;

public class test {

	public static void main(String[] args) {
		Graph test = new Graph(5);
		test.addEdge(0, 2);
		test.addEdge(2, 4);
		test.addEdge(0, 4,6);
		System.out.println(test.DFS(0, 4));
		System.out.println(test.Dijkstra(0, 4));

	}

}
