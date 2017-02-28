package queue;

public class MyArrayDeque<T> {

	public static void main(String[] args) {
		MyArrayDeque<Integer> deque = new MyArrayDeque<>(3);
		for (int i = 0; i < 5; i++) {
			deque.offerFirst(i);
		}
//		while (!deque.isEmpty()) {
//			System.out.println(deque.pollFirst());
//		}
//		for (int i = 0; i < 5; i++) {
//			deque.offerLast(i);
//		}
		while (!deque.isEmpty()) {
			System.out.println(deque.pollLast());
		}
	}
	
	public MyArrayDeque(int capacity) {
		array = new Object[capacity];
	}
	
	public void offerFirst(T val) {
		size++;
		head = head > 0 ? head - 1 : array.length - 1;
		if (head == tail) {
			doubleCapacity();
		}
		array[head] = val;
	}
	
	public void offerLast(T val) {
		size++;
		array[tail] = val;
		tail = tail + 1 < array.length ? tail + 1 : 0;
		if (tail == head) {
			doubleCapacity();
		}
	}
	
	public T pollFirst() {
		T result = (T)array[head];
		if (result == null) {
			return null;
		}
		size--;
		array[head] = null;
		head = head + 1 < array.length ? head + 1 : 0;
		return result;
	}
	
	public T pollLast() {
		tail = tail > 0 ? tail - 1 : array.length - 1;
		T result = (T)array[tail];
		if (result == null) {
			return null;
		}
		size--;
		array[tail] = null;
		return result;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private void doubleCapacity() {
		Object[] array2 = new Object[array.length * 2];
		System.arraycopy(array, head, array2, 0, array.length - head);
		System.arraycopy(array, 0, array2, array.length - head, tail);
		head = 0;
		tail = array.length;
		array = array2;
	}
	
	private Object[] array;
	// the index of the element at the head of the deque (which is element that would be removed by removeFirst(), pop())
	private int head; 
	// the index of which the next element would be added by addLast()
	private int tail;
	private int size;
}
