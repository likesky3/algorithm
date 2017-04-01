package iterator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

import utils.TreeNode;

public class BinaryTreeInorderIterator implements Iterator<TreeNode>{
	private TreeNode curr;
	private Deque<TreeNode> stack;
	public BinaryTreeInorderIterator(TreeNode root) throws Exception {
		if (root == null) {
			throw new Exception("It's nonsense to create an iterator for null,"
					+ " you wuold get NullPointerException if you use it later.");
		}
		curr = root;
		stack = new ArrayDeque<>();
		pushAllNodesInLeftPath(curr);
		// >< this block is repeated and has a meaningful semantic so we can make the code more succinct by using a helper method.
//		while (curr != null) {
//			stack.offerFirst(curr);
//			curr = curr.left;
//		}
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
		curr = stack.pollFirst();
		TreeNode res = curr;
		curr = curr.right;
		// >< this block is repeated and has a meaningful semantic so we can make the code more succinct by using a helper method.
		pushAllNodesInLeftPath(curr);
//		while (curr != null) {
//			stack.offerFirst(curr);
//			curr = curr.left;
//		}
		return res;
	}
	
	private void pushAllNodesInLeftPath(TreeNode curr) {
		while (curr != null) {
			stack.offerFirst(curr);
			curr = curr.left;
		}
	}
}
