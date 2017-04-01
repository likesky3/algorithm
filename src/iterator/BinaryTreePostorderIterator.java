package iterator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

import utils.TreeNode;

public class BinaryTreePostorderIterator implements Iterator<TreeNode>{
	private TreeNode prev, curr;
	private Deque<TreeNode> stack;
	public BinaryTreePostorderIterator(TreeNode root) throws Exception {
		if (root == null) {
			throw new Exception("It's nonsense to create an iterator for null,"
					+ " you wuold get NullPointerException if you use it later.");
		}
		prev = null;
		curr = null;
		stack = new ArrayDeque<>();
		if (root != null) {
			stack.offerFirst(root);
		}
	}
	
	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	@Override
	public TreeNode next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		TreeNode res = null;
		while (res == null) {
			curr = stack.peekFirst();
			// go down
			if (prev == null || prev.left == curr || prev.right == curr) {
				if (curr.left != null) {
					stack.offerFirst(curr.left);
				} else if (curr.right != null) {
					stack.offerFirst(curr.right);
				} else {
					res = stack.pollFirst();
				}
			} else if (curr.left == prev && curr.right == null || curr.right == prev) {
				// go up from left and root has no right child or go up from right
				res = stack.pollFirst();
			} else {
				// go up from left and root has right child, so visit right
				// child first
				stack.offerFirst(curr.right);
			}
			prev = curr;
		}
		return res;
	}
}
