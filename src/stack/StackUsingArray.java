package stack;

public class StackUsingArray<T> {

	public static void main(String[] args) {
		StackUsingArray<Integer> stack = new StackUsingArray(5);
		System.out.printf("size=%d, isEmpty=%b\n", stack.size(), stack.isEmpty());
		for (int i = 0; i < 8; i++) {
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
		
	}
	
	public StackUsingArray(int capacity) {
		array = new Object[capacity];
	}
	
	public void push(T val) {
		if (head + 1 == array.length) {
			enlarge(2 * array.length);
		}
		array[head++] = val;
	}
	
	public T pop() {
		if (head == 0) {
			return null;
		}
		return (T) array[--head];
	}
	
	public T peek() {
		if (head == 0) {
			return null;
		}
		return (T)array[head - 1];
	}
	
	public boolean isEmpty() {
		return head == 0;
	}
	
	public int size() {
		return head;
	}
	
	private void enlarge(int capacity) {
		Object[] newArray = new Object[capacity];
		System.arraycopy(array, 0, newArray, 0, array.length);
		array = newArray;
	}
	
	private static final int DEFAULT_CAPACITY = 11;
	private Object[] array; // stack array
	private int head; // head points to the next available position, head - 1 is the last item we add into stack
}
