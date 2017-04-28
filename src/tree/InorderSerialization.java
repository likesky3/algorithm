package tree;

import utils.*;

public class InorderSerialization {

	public static void main(String[] args) {
		InorderSerialization.test("1,2,3,#,#,4,#");
	}
	
	private static void test(String s) {
		TreeNode root = TreeNode.deserialize(s);
		DoubleLinkedNode dln = InorderSerialization.serialize(root);
		while (dln != null) {
			System.out.print(dln.val + " ");
			dln = dln.next;
		}
		System.out.println();
	}
	
	public static DoubleLinkedNode serialize(TreeNode root) {
		if (root == null) {
			return null;
		}
		DoubleLinkedNode[] head = new DoubleLinkedNode[1];
		DoubleLinkedNode[] prev = new DoubleLinkedNode[1];
		serial(root, head, prev);
		return head[0];
	}
	
	private static void serial(TreeNode root, DoubleLinkedNode[] head, DoubleLinkedNode[] prev) {
		if (root == null) {
			return;
		}
		serial(root.left, head, prev);
		DoubleLinkedNode curr = new DoubleLinkedNode(root.val);
		if (head[0] == null) {
			head[0] = curr;
		}
		curr.prev = prev[0];
		if (prev[0] != null) {
			prev[0].next = curr;
		}
		prev[0] = curr;
		serial(root.right, head, prev);
	}

}
