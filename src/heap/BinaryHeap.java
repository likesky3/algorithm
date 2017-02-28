package heap;

public class BinaryHeap<T extends Comparable<? super T>> {
	public static void main(String[] args) throws Exception {
		Integer[] array = { 5, 3, 4, 2, 1, 1, 2, 1, 8, 4, 4, 9, 13, 5, 8 };
		BinaryHeap<Integer> minHeap = new BinaryHeap(array);
		int size = minHeap.size();
		System.out.println(minHeap.size() + ", min=" + minHeap.peek());
		for (int i = 0; i < size; i++) {
			System.out.print(minHeap.poll() + " ");
		}
		System.out.println();
		for (int i = 0; i < size; i++) {
			minHeap.offer(Integer.valueOf((int) (Math.random() * size)));
		}
		for (int i = 0; i < size; i++) {
			System.out.print(minHeap.poll() + " ");
		}
		System.out.println();
	}
	public BinaryHeap() {
		this(DEFAULT_CAPACITY);
	}
	
	public BinaryHeap(int capacity) {
		array = new Object[capacity];
		size = 0;
	}
	
	public BinaryHeap(T[] items) {
		array = items;
		size = items.length;
		buildHeap();
	}
	
	@SuppressWarnings("unchecked")
	public void offer(T x) {
		if (size == array.length) { 
			enlargeArray(array.length * 2);
		}
		// Percolate up
		int hole = size++;
		int parent = (hole - 1) >> 1; // (hole -1) / 2; (-3 >> 1) == -2, >> 1是绝对的向下取整
		while(hole > 0 && x.compareTo((T) array[parent]) < 0) {
			array[hole] = array[parent];
			hole = parent;
			parent = (parent - 1) >> 1;
		}
		array[hole] = x;
	}
	
	@SuppressWarnings("unchecked")
	public T peek() {
		return size == 0 ? null : (T) array[0];
	}
	
	public T poll() throws Exception {
		if (isEmpty()) {
			throw new Exception("UnderFlowException");
		}
		T minItem = peek();
		array[0] = array[--size];
		percolateDown(0);
		return minItem;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void makeEmpty() {
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	private static final int DEFAULT_CAPACITY = 10;
	private int size; // Number of elements in heapl
	// The heap array, we do not store actual data in array[0], 
	// array[0] is used as a sentinel
	private Object[] array; 
	
	
	@SuppressWarnings("unchecked")
	private void percolateDown(int hole) {
		int child = hole * 2 + 1;
		T tmp = (T) array[hole];
		for (; child < size; hole = child) {
			child = hole * 2 + 1;
			if (child + 1 < size && ((Comparable<? super T>) array[child + 1]).compareTo((T) array[child]) < 0) {
				child++;
			}
			if (child < size && ((Comparable<? super T>) array[child]).compareTo(tmp) < 0) {
				array[hole] = array[child];
			} else {
				break;
			}
		}
		array[hole] = tmp;
	}
	
	private void buildHeap() {
		for (int i = (size - 2) / 2; i >= 0; i--) {
			percolateDown(i);
		}
	}
	
	private void enlargeArray(int newSize) {
		Object[] oldArray = array;
		array = new Object[newSize];
		System.arraycopy(oldArray, 0, array, 0, oldArray.length);
	}
	
}
