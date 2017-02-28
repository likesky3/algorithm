package graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import utils.GraphNode;

public class Dijkstra {

	public static void main(String[] args) {
		Dijkstra obj = new Dijkstra();
		GraphNode v1 = obj.makeGraph();
		obj.findShortestPath(v1);
	}
	
	public GraphNode makeGraph() {
		GraphNode v1 = new GraphNode("v1");
		GraphNode v2 = new GraphNode("v2");
		GraphNode v3 = new GraphNode("v3");
		GraphNode v4 = new GraphNode("v4");
		GraphNode v5 = new GraphNode("v5");
		GraphNode v6 = new GraphNode("v6");
		GraphNode v7 = new GraphNode("v7");
		v1.edges.add(new GraphNode.Edge(2, v2));
		v1.edges.add(new GraphNode.Edge(1, v4));
		v2.edges.add(new GraphNode.Edge(3, v2));
		v2.edges.add(new GraphNode.Edge(10, v5));
		v3.edges.add(new GraphNode.Edge(4, v1));
		v4.edges.add(new GraphNode.Edge(2, v3));
		v4.edges.add(new GraphNode.Edge(2, v5));
		v4.edges.add(new GraphNode.Edge(8, v6));
		v4.edges.add(new GraphNode.Edge(4, v7));
		v5.edges.add(new GraphNode.Edge(6, v7));
		v6.edges.add(new GraphNode.Edge(5, v3));
		v7.edges.add(new GraphNode.Edge(1, v6));
		return v1;
	}

	public void findShortestPath(GraphNode source) {
		PriorityQueue<Vertex> minHeap = new PriorityQueue<>(8, new Comparator<Vertex>() {
			public int compare(Vertex a, Vertex b) {
				return a.dist - b.dist;
			}
		});
		Map<GraphNode, Vertex> visited = new HashMap<>();
		Vertex v0 = new Vertex(0, source);
		minHeap.offer(v0);
		visited.put(source, v0);
		while (!minHeap.isEmpty()) {
			Vertex curr = minHeap.poll();
			curr.known = true;
//			System.out.printf("curr.label=%s, curr.dist=%d\n", curr.node.label, curr.dist);
			for (GraphNode.Edge e : curr.node.edges) {
				GraphNode neigh = e.neigh;
				if (!visited.containsKey(neigh)) {
					Vertex v = new Vertex(curr.dist + e.dist, neigh);
//					System.out.printf("neigh.label=%s, edge.dist =%d, neigh.dist=%d\n", neigh.label, e.dist, v.dist);
					visited.put(neigh, v);
					minHeap.offer(v);
				} else {
					Vertex v = visited.get(neigh);
					if (!v.known && curr.dist + e.dist < v.dist) { // update v's dist, decreaseKey in minHeap
						v.dist = curr.dist + e.dist;
					}
				}
			}
		}
		
		printShortestDist(visited);
	}
	
	private void printShortestDist(Map<GraphNode, Vertex> visited) {
		for (GraphNode node: visited.keySet()) {
			System.out.printf("label=%s, dist=%d\n", node.label, visited.get(node).dist);
		}
	}

	static class Vertex {
		int dist; // dist to the source node
		boolean known;
		GraphNode node;
		public Vertex(int dist, GraphNode node) {
			this.node = node;
			this.dist = dist;
		}
	}

}
