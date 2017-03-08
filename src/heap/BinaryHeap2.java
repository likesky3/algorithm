package heap;

import java.util.Collection;

public class BinaryHeap2<T> {

	public static void main(String[] args) {

	}
	
	private Object[] array;
	private int size;
	private static final int DEFAULT_CAPACITY = 16;
	public BinaryHeap2() {
		array = new Object[DEFAULT_CAPACITY];
	}
	
	public BinaryHeap2(int capacity) {
		array = new Object[capacity];
	}
	
	public BinaryHeap2(Collection<T> collection) {
		array = new Object[collection.size()];
		collection.toArray(array);
		heapify();
	}
	
	private void heapify() {
		
	}
	
	public void offer(T val) {
		
	}
	
	public T poll() {
		return null;
	}
	
	public T peek() {
		return null;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	private void percolateDown() {
		
	}
	
	private void percolateUp() {
		
	}
	
	private void enlarge() {
		
	}
	
}
