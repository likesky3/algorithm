package org.professional.dp;


public class ReversedLinkList {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		ReversedLinkList obj = new ReversedLinkList();
		head = obj.reverseList(head);
		System.out.println("done");
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}
	
	public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        
        ListNode prev = head;
        ListNode curr = prev.next;
        prev.next = null;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

}
