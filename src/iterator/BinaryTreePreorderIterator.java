package iterator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

import utils.TreeNode;

public class BinaryTreePreorderIterator implements Iterator<TreeNode>{
	
	private TreeNode curr;
	private Deque<TreeNode> stack;
	public BinaryTreePreorderIterator(TreeNode root) throws Exception {
		if (root == null) {
			throw new Exception("It's nonsense to create an iterator for null,"
					+ " you wuold get NullPointerException if you use it later.");
		}
		curr = root;
		stack = new ArrayDeque<>();
	}
	@Override
	public boolean hasNext() {
		return !stack.isEmpty() || curr != null;
	}
	
	@Override
	public TreeNode next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		if (curr == null) {
			curr = stack.pollFirst();
		}
		TreeNode res = curr;
		if (curr.right != null) {
			stack.offerFirst(curr.right);
		}
		curr = curr.left;
		return res;
	}
}
