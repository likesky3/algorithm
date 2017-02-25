package stack;

import java.util.LinkedList;

public class DequeUsingStacks {

	public static void main(String[] args) throws Exception {
//		LinkedList<Integer> deque = new LinkedList<>();
		DequeUsingStacks deque= new DequeUsingStacks();
		deque.offerFirst(1);
		deque.offerFirst(2);
		deque.offerFirst(3);
		System.out.println(deque.pollFirst());
		deque.offerLast(-1);
		deque.offerLast(-2);
		System.out.println(deque.pollLast());
		
	}
	// if use 2 stacks, amortized time complexity of pop() is O(n)
	// if use 3 stacks, one extra stack is used to do load balancing, then amortized time of pop() is O(1)
	private LinkedList<Integer> headStack;
	private LinkedList<Integer> tailStack;
	private LinkedList<Integer> buffer;
	public DequeUsingStacks() {
		headStack = new LinkedList<>();
		tailStack = new LinkedList<>();
		buffer = new LinkedList<>();
	}
	
	// Assumption: pollFirst(), pollLast(), peekFirst(), peekLast() will be called only when the Deque is not empty.
	public int pollFirst() throws Exception {
		if (isEmpty()) {
			throw new Exception("Empty stack, operation is wrong.");
		}
		if (headStack.isEmpty()) {
			loadBalancing(tailStack, headStack);
		}
		return headStack.pop();
	}
	
	public int pollLast() throws Exception {
		if (isEmpty()) {
			throw new Exception("Empty stack !!!");
		}
		if (tailStack.isEmpty()) {
			loadBalancing(headStack, tailStack);
		}
		return tailStack.pop();
	}
	
	public int peekFirst() throws Exception {
		if (isEmpty()) {
			throw new Exception("Empty stack, operation is wrong.");
		}
		if (headStack.isEmpty()) {
			loadBalancing(tailStack, headStack);
		}
		return headStack.peek();
	}
	
	public int peekLast() throws Exception {
		if (isEmpty()) {
			throw new Exception("Empty stack !!!");
		}
		if (tailStack.isEmpty()) {
			loadBalancing(headStack, tailStack);
		}
		return tailStack.peek();
	}
	
	public void offerFirst(int val) {
		headStack.push(val);
	}
	
	public void offerLast(int val) {
		tailStack.push(val);
	}
	
	public boolean isEmpty() {
		return headStack.isEmpty() && tailStack.isEmpty();
	}
	
	// s1 is not empty, s2 is empty
	private void loadBalancing(LinkedList<Integer> s1, LinkedList<Integer> s2) {
		int halfSize = s1.size() / 2;
		// move half size of elements from s1 to buffer
		for (int i = 0; i < halfSize; i++) {
			buffer.push(s1.pop());
		}
		// move the rest half from s1 to s2
		while (!s1.isEmpty()) {
			s2.push(s1.pop());
		}
		// restore elements from buffer to s1
		while (!buffer.isEmpty()) {
			s1.push(buffer.pop());
		}
	}

}
