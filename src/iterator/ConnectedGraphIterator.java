package iterator;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

import utils.GraphNode;

/** tranverse the graph in BFS style.
 * */
public class ConnectedGraphIterator implements Iterator<GraphNode>{
	private Queue<GraphNode> queue;
	private Set<GraphNode> visited;
	public ConnectedGraphIterator(GraphNode graph) throws Exception {
		if (graph == null) {
			throw new Exception("Cannot create iterator for a null graph.");
		}
		queue = new ArrayDeque<>();
		queue.offer(graph);
		visited = new HashSet<>();
		visited.add(graph);
	}
	
	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}
	
	@Override
	public GraphNode next() {
		GraphNode curr = queue.poll();
		for (GraphNode neigh : curr.neighbors) {
			// >< remember how to use HashMap/HashSet more efficiently, we can modify this as if (!visited.add(neigh)) queue.offer(neigh);
//			if (!visited.contains(neigh)) {
//				queue.offer(neigh);
//				visited.add(neigh);
//			}
			
			if (!visited.add(neigh)) {
				queue.offer(neigh);
			}
		}
		return curr;
	}
}
