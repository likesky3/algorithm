package utils;

public class ListNode {
	public int value;
	public ListNode next;
	public ListNode (int val) {
		this.value = val;
	}
	
	public static ListNode buildAList(int[] nums) {
		ListNode dummy = new ListNode(0);
		for (int i = nums.length - 1; i >= 0; i--) {
			ListNode node = new ListNode(nums[i]);
			node.next = dummy.next;
			dummy.next = node;
		}
		return dummy.next;
	}
}
