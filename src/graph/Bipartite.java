package graph;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import utils.GraphNode;

public class Bipartite {

	public static void main(String[] args) {

	}

	/**
	 * We must check every node in the graph, can't just start from random node like graph.get(0).
	 * Because the graph may have several disconnected parts.*/
	public boolean isBipartite(List<GraphNode> graph) {
	    if (graph == null || graph.size() < 2) {
	      return false;
	    }
	    Map<GraphNode, Boolean> visited = new HashMap<>();
	    for (GraphNode v : graph) {
	      if (!BFS(v, visited)) {
	        return false;
	      }
	    }
	    return true;
	  }
	  
	  private boolean BFS(GraphNode node, Map<GraphNode, Boolean> visited) {
	    if (visited.containsKey(node)) {
	      return true;
	    }
	    
	    Queue<GraphNode> queue = new ArrayDeque<>();
	    queue.offer(node);
	    visited.put(node, true);
	    while (!queue.isEmpty()) {
	      GraphNode curr = queue.poll();
	      boolean currGroup = visited.get(curr);
	      for (GraphNode w : curr.neighbors) {
	        if (!visited.containsKey(w)) {
	          visited.put(w, !currGroup);
	          queue.offer(w);
	        } else if (visited.get(w) == currGroup) {
	          return false;
	        }
	      }
	    }
	    return true;
	  }

}
