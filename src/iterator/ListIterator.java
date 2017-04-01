package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import utils.ListNode;

public class ListIterator implements Iterator<ListNode>{
	
	ListNode iter;
	public ListIterator (ListNode head) throws Exception {
		if (head == null) {
			throw new Exception("It's nonsense to create an iterator for null,"
					+ " you wuold get NullPointerException if you use it later.");
		}
		iter = new ListNode(0);
		iter.next = head;
	}
	
	// look forward one step
	@Override
	public boolean hasNext() {
		return iter.next != null;
	}

	@Override
	public ListNode next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return iter = iter.next;
	}

}
