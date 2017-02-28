package queue;

public class MyLinkedList<T> {
	static class LinkedNode<T>{
		LinkedNode prev, next;
		T val;
		public LinkedNode(T val) {
			this.val = val;
		}
	}
	
	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		// as stack
		for (int i = 0; i < 3; i++) {
			list.offerFirst(i);
		}
//		while (!list.isEmpty()) {
//			System.out.println(list.pollFirst());
//		}
		while (!list.isEmpty()) {
			System.out.println(list.pollLast());
		}
		
	}
	
	public MyLinkedList() {
		head = new LinkedNode(null);
		tail = new LinkedNode(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}
	
	public void offerFirst(T val) {
		size++;
		LinkedNode node = new LinkedNode(val);
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
		node.prev = head;
	}
	
	public void offerLast(T val) {
		size++;
		LinkedNode node = new LinkedNode(val);
		node.prev = tail.prev;
		tail.prev.next = node;
		node.next = tail;
		tail.prev = node;
	}
	
	public T pollFirst() {
		if (size == 0) {
			return null;
		}
		size--;
		LinkedNode node = head.next;
		head.next = node.next;
		node.next.prev = head;
		node.next = null;
		node.prev = null;
		return (T)node.val;
	}
	
	public T pollLast() {
		if (size == 0) {
			return null;
		}
		size--;
		LinkedNode node = tail.prev;
		tail.prev = node.prev;
		node.prev.next = tail;
		node.prev = null;
		node.next = null;
		return (T)node.val;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private LinkedNode head, tail;
	private int size;
}
