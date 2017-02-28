package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CloneGraph {

	public static void main(String[] args) {
		CloneGraph obj = new CloneGraph();
		UndirectedGraphNode node = new UndirectedGraphNode(0);
		node.neighbors.add(node);
		node.neighbors.add(node);
		obj.cloneGraph(node);
	}
	
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        Map<UndirectedGraphNode, UndirectedGraphNode> old2new = new HashMap<>();
        old2new.put(node, copy);
        Queue<UndirectedGraphNode> toProcess = new ArrayDeque<>();
        toProcess.offer(node);
        Set<UndirectedGraphNode> visited = new HashSet<>();
        while (!toProcess.isEmpty()) {
            UndirectedGraphNode curr = toProcess.poll();
            visited.add(curr);
            UndirectedGraphNode currCopy = old2new.get(curr);
            for (UndirectedGraphNode neigh : curr.neighbors) {
                if (!old2new.containsKey(neigh)) {
                    UndirectedGraphNode neighCopy = new UndirectedGraphNode(neigh.label);
                    old2new.put(neigh, neighCopy);
                }
                currCopy.neighbors.add(old2new.get(neigh));
                if (!visited.contains(neigh)) {
                    toProcess.offer(neigh);
                }
            }
        }
        return copy;
    }
	
	static class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;
		public UndirectedGraphNode(int label) {
			this.label = label;
			neighbors = new ArrayList<>();
		}
	}

}
