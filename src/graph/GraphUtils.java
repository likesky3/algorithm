package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import utils.GraphNode;

public class GraphUtils {
	public static GraphNode createGraph(int nodesNum) {
		GraphNode[] graph = new GraphNode[nodesNum];
		for (int i = 0; i < nodesNum; i++) {
			graph[i] = new GraphNode(i);
		}
		for (int i = 1; i < nodesNum; i++) {
			int neighNum = Math.max(1, (int)(Math.random() * (i + 1)));
			GraphNode curr = graph[i];
			boolean[] added = new boolean[i];
			for (int j = 0; j < neighNum; j++) {
				int idx = (int)(Math.random() * i);
				if (!added[idx]) {
					added[idx] = true;
					GraphNode neigh = graph[idx];
					curr.neighbors.add(neigh);
					neigh.neighbors.add(curr);
				} else {
					j--;
				}
			}
		}
		return graph[0];
	}
	
	public static List<Integer> bfsTraversal(GraphNode g) {
		List<Integer> result = new ArrayList<>();
		Deque<GraphNode> queue = new ArrayDeque<>();
		queue.offer(g);
		Set<GraphNode> visited = new HashSet<>();
		visited.add(g);
		while (!queue.isEmpty()) {
			GraphNode curr = queue.poll();
			result.add(curr.val);
			for (GraphNode neigh : curr.neighbors) {
				if (!visited.contains(neigh)) {
					queue.offer(neigh);
					visited.add(neigh);
				}
			}
//			print(curr, queue);
		}
		return result;
	}
	
	public static void print(GraphNode curr, Deque<GraphNode> queue) {
		System.out.println("curr=" + curr.val);
		Iterator<GraphNode> iter = queue.iterator();
		while (iter.hasNext()) {
			GraphNode n = iter.next();
			System.out.print(n.val + "," + n.toString() + "; ");
//			System.out.print(n.val + "," + n.toString() + "; ");
		}
		System.out.println();
	}
}
