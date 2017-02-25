package stack;

import java.util.LinkedList;

public class StackWithMin {

	public static void main(String[] args) {

	}
	
	public StackWithMin() {
		baseStack = new LinkedList<>();
		minStack = new LinkedList<>();
	}
	
	public Integer pop() {
		if (baseStack.isEmpty()) {
			return null;
		}
		Node min = minStack.peek();
		Integer val = baseStack.pop();
		if (val == min.value && baseStack.size() == min.size) {
			minStack.pop();
		}
		return val;
	}
	
	public Integer peek() {
		if (baseStack.isEmpty()) {
			return null;
		}
		return baseStack.peek();
	}
	
	public Integer min() {
		if (baseStack.isEmpty()) {
			return null;
		}
		return minStack.peek().value;
	}
	
	public void push(int val) {
		if (minStack.isEmpty() || val < minStack.peek().value) {
			minStack.push(new Node(val, baseStack.size()));
		}
		baseStack.push(val);
	}
	
	private LinkedList<Integer> baseStack;
	private LinkedList<Node> minStack;
	private class Node {
		int value;
		int size; // size of baseStack before value was pushed into
		public Node(int value, int size) {
			this.value = value;
			this.size = size;
		}
	}

}
