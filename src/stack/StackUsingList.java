package stack;

public class StackUsingList<T> {

	public static void main(String[] args) {
		StackUsingList<Integer> stack = new StackUsingList();
		System.out.printf("size=%d, isEmpty=%b\n", stack.size(), stack.isEmpty());
		for (int i = 0; i < 5; i++) {
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
	
	private ListNode head;
	private int size;
	public void push(T val) {
		ListNode newHead = new ListNode(val);
		newHead.next = head;
		head = newHead;
		size++;
	}
	
	public T pop() {
		if (head == null) {
			return null;
		} else {
			size--;
			T ret = (T) head.val;
			head = head.next;
			return ret;
		}
	}
	
	public T peek() {
		if (head == null) {
			return null;
		} else {
			return (T)head.val;
		}
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int size() {
		return size;
	}
	
	
	static class ListNode<T> {
		T val;
		ListNode next;
		public ListNode(T val) {
			this.val  = val;
		}
	}

}
