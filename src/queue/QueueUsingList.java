package queue;

// FIFO, add in the tail, leave in the head
public class QueueUsingList<T> {

	public static void main(String[] args) {
		QueueUsingArray<Integer> queue = new QueueUsingArray<Integer>(2);
		for (int i = 0; i < 5; i++) {
			queue.offer(i);
			System.out.printf("peek %d, size=%d\n", queue.peek(), queue.size());
			int top = queue.poll();
			System.out.printf("pop %d, size=%d\n", top, queue.size());
		}
	}
	
	public boolean offer(T val) {
		size++;
		ListNode node = new ListNode(val);
		if (tail == null) { // remember corner case
			head = tail = node;
			return true;
		}
		tail.next = node;
		tail = tail.next;
		return true;
	}
	
	public T poll() {
		if (head == null) {
			return null;
		}
		size--;
		T ret = (T) head.val;
		head = head.next;
		if (head == null) { // remember corner case
			tail = head;
		}
		return ret;
	}
	
	public T peek() {
		if (head == null) {
			return null;
		}
		return (T)head.val;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int size() {
		return size;
	}
	
	private ListNode head, tail;
	private int size;
	
	static class ListNode<T> {
		T val;
		ListNode next;
		public ListNode(T val) {
			this.val = val;
		}
	}
}
