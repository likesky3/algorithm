package queue;

public class QueueUsingArray<T> {
	public static void main(String[] args) {
		QueueUsingList<Integer> queue = new QueueUsingList<Integer>();
		for (int i = 0; i < 5; i++) {
			queue.offer(i);
			System.out.printf("peek %d, size=%d\n", queue.peek(), queue.size());
		}
		while (!queue.isEmpty()) {
			int top = queue.poll();
			System.out.printf("pop %d, size=%d\n", top, queue.size());
		}
	}
	
	public QueueUsingArray(int capacity) {
		array = new Object[capacity];
	}
	public boolean offer(T val) {
		if (isFull()) {
			enlarge(array.length * 2);
		}
		if (val == null) {
			return false;
		}
		size++;
		array[tail] = val;
		tail = tail + 1 == array.length ? 0 : tail + 1;
		return true;
	}
	
	public T poll() {
		if (size == 0) {
			return null;
		}
		size--;
		T ret = (T)array[head];
		head = head + 1 == array.length ? 0 : head + 1;
		return ret;
	}
	
	public T peek() {
		if (size == 0) {
			return null;
		}
		return (T)array[head];
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private boolean isFull() {
		return size == array.length;
	}
	
	public int size() {
		return size;
	}
	
	private void enlarge(int capacity) {
		
		
	}
	private Object[] array;
	private int head; // remove element from head
	private int tail; // insert element into tail
	private int size;
}
