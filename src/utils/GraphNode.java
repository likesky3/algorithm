package utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class GraphNode {
	public String label;
	public int key;
	public int val; // another name for key
	public List<GraphNode> neighbors;
	public List<Edge> edges;
	public GraphNode (int val) {
		this.val = val;
		this.key = val;
		neighbors = new ArrayList<>();
		edges = new ArrayList<>();
	}
	
	public GraphNode(String label) {
		this(0);
		this.label = label;
	}
	
	public static class Edge {
		public int dist;
		public GraphNode from, to;
		public Edge(int dist, GraphNode from, GraphNode to) {
			this.dist = dist;
			this.from = from;
			this.to = to;
		}
	}
}
