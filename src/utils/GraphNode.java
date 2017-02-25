package utils;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
	public int key;
	public int val; // another name for key
	public List<GraphNode> neighbors;
	public GraphNode (int val) {
		this.val = val;
		this.key = val;
		neighbors = new ArrayList<>();
	}
}
