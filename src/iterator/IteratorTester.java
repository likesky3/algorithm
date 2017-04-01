package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import graph.GraphUtils;
import utils.GraphNode;
import utils.ListNode;
import utils.TreeNode;

public class IteratorTester {

	public static void main(String[] args) throws Exception {
		/** Test ArrayIterator
		 * */ 
		String[] s = null;
		Iterator iter = null;
//		try {
//			iter = new ArrayIterator(s);
//			System.out.println("ArrayIterator:: test null=" + testArrayIterator(iter, s));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		s = new String[0];
		iter = new ArrayIterator(s);
		System.out.println("ArrayIterator:: test empty=" + testArrayIterator(iter, s));
		s = new String[]{"a"};
		iter = new ArrayIterator(s);
		System.out.println("ArrayIterator:: test size of 1=" + testArrayIterator(iter, s));
		s = new String[]{"a, b"};
		iter = new ArrayIterator(s);
		System.out.println("ArrayIterator:: test size of 2=" + testArrayIterator(iter, s));
		int size = (int)(Math.random() * 30);
		s = new String[size];
		for (int i = 0; i < size; i++) {
			s[i] = "" + (int)(Math.random() * size);
		}
		iter = new ArrayIterator(s);
		System.out.println("ArrayIterator:: test size of random=" + testArrayIterator(iter, s));
		System.out.println();
		/** Test ListIterator
		 * */
		ListNode head = null;
		ListIterator listIter = new ListIterator(head);
		System.out.println("ListIterator:: test null=" + testListIterator(listIter, null));
		head = ListNode.buildAList(new int[0]);
		listIter = new ListIterator(head);
		System.out.println("ListIterator:: test empty=" + testListIterator(listIter, new int[0]));
		int[] nums = {1};
		head = ListNode.buildAList(nums);
		listIter = new ListIterator(head);
		System.out.println("ListIterator:: test size of 1=" + testListIterator(listIter, nums));
		nums = new int[]{1, 2};
		head = ListNode.buildAList(nums);
		listIter = new ListIterator(head);
		System.out.println("ListIterator:: test size of 2=" + testListIterator(listIter, nums));
		size = (int)(Math.random() * 30);
		nums = new int[size];
		for (int i = 0; i < size; i++) {
			nums[i] = (int)(Math.random() * size);
		}
		head = ListNode.buildAList(nums);
		listIter = new ListIterator(head);
		System.out.println("ListIterator:: test size of random=" + testListIterator(listIter, nums));
		size = (int)(Math.random() * 30);
		System.out.println();
		
		/** Test BinaryTreePreorderIterator
		 * */
		TreeNode root = null;
		BinaryTreePreorderIterator preorderIter = new BinaryTreePreorderIterator(root);
		System.out.println("BinaryTreePreorderIterator:: test null=" + testTreePreorderIterator(preorderIter, root));
		root = TreeNode.deserialize("1");
		preorderIter = new BinaryTreePreorderIterator(root);
		System.out.println("BinaryTreePreorderIterator:: test size of 1=" + testTreePreorderIterator(preorderIter, root));
		root = TreeNode.deserialize("1,2");
		preorderIter = new BinaryTreePreorderIterator(root);
		System.out.println("BinaryTreePreorderIterator:: test size of 2=" + testTreePreorderIterator(preorderIter, root));
		root = TreeNode.deserialize("1,#,2");
		preorderIter = new BinaryTreePreorderIterator(root);
		System.out.println("BinaryTreePreorderIterator:: test size of 2=" + testTreePreorderIterator(preorderIter, root));
		root = TreeNode.deserialize("1,2,3");
		preorderIter = new BinaryTreePreorderIterator(root);
		System.out.println("BinaryTreePreorderIterator:: test size of 3=" + testTreePreorderIterator(preorderIter, root));
		root = TreeNode.deserialize("1,2,3,#,4,5,#");
		preorderIter = new BinaryTreePreorderIterator(root);
		System.out.println("BinaryTreePreorderIterator:: test size of 1=" + testTreePreorderIterator(preorderIter, root));
		System.out.println();
		
		/** Test BinaryTreeInorderIterator
		 * */
		root = null;
		BinaryTreeInorderIterator inorderIter = new BinaryTreeInorderIterator(root);
		System.out.println("BinaryTreeInorderIterator:: test null=" + testTreeInorderIterator(inorderIter, root));
		root = TreeNode.deserialize("1");
		inorderIter = new BinaryTreeInorderIterator(root);
		System.out.println("BinaryTreeInorderIterator:: test size of 1=" + testTreeInorderIterator(inorderIter, root));
		root = TreeNode.deserialize("1,2");
		inorderIter = new BinaryTreeInorderIterator(root);
		System.out.println("BinaryTreeInorderIterator:: test size of 2=" + testTreeInorderIterator(inorderIter, root));
		root = TreeNode.deserialize("1,#,2");
		inorderIter = new BinaryTreeInorderIterator(root);
		System.out.println("BinaryTreeInorderIterator:: test size of 2=" + testTreeInorderIterator(inorderIter, root));
		root = TreeNode.deserialize("1,2,3");
		inorderIter = new BinaryTreeInorderIterator(root);
		System.out.println("BinaryTreeInorderIterator:: test size of 3=" + testTreeInorderIterator(inorderIter, root));
		root = TreeNode.deserialize("1,2,3,#,4,5,#");
		inorderIter = new BinaryTreeInorderIterator(root);
		System.out.println("BinaryTreeInorderIterator:: test size of 1=" + testTreeInorderIterator(inorderIter, root));
		System.out.println();


		
		/** Test BinaryTreePostorderIterator
		 * */
		root = null;
		BinaryTreePostorderIterator postorderIter = new BinaryTreePostorderIterator(root);
		System.out.println("BinaryTreePostorderIterator:: test null=" + testTreePostorderIterator(postorderIter, root));
		root = TreeNode.deserialize("1");
		postorderIter = new BinaryTreePostorderIterator(root);
		System.out.println("BinaryTreePostorderIterator:: test size of 1=" + testTreePostorderIterator(postorderIter, root));
		root = TreeNode.deserialize("1,2");
		postorderIter = new BinaryTreePostorderIterator(root);
		System.out.println("BinaryTreePostorderIterator:: test size of 2=" + testTreePostorderIterator(postorderIter, root));
		root = TreeNode.deserialize("1,#,2");
		postorderIter = new BinaryTreePostorderIterator(root);
		System.out.println("BinaryTreePostorderIterator:: test size of 2=" + testTreePostorderIterator(postorderIter, root));
		root = TreeNode.deserialize("1,2,3");
		postorderIter = new BinaryTreePostorderIterator(root);
		System.out.println("BinaryTreePostorderIterator:: test size of 3=" + testTreePostorderIterator(postorderIter, root));
		root = TreeNode.deserialize("1,2,3,#,4,5,#");
		postorderIter = new BinaryTreePostorderIterator(root);
		System.out.println("BinaryTreePostorderIterator:: test size of 1=" + testTreePostorderIterator(postorderIter, root));
		System.out.println();


		/** Test ConnetedGraphIterator
		 * */
		GraphNode g = null;
		ConnectedGraphIterator graphIter = null;
//		graphIter = new ConnectedGraphIterator(g);
//		System.out.println("ConnectedGraphIterator:: test null=" + testConnectedGraphIterator(graphIter, g));
		g = GraphUtils.createGraph(1);
		graphIter = new ConnectedGraphIterator(g);
		System.out.println("ConnectedGraphIterator:: test null=" + testConnectedGraphIterator(graphIter, g));
		g = GraphUtils.createGraph(3);
		graphIter = new ConnectedGraphIterator(g);
		System.out.println("ConnectedGraphIterator:: test null=" + testConnectedGraphIterator(graphIter, g));
		g = GraphUtils.createGraph(10);
		graphIter = new ConnectedGraphIterator(g);
		System.out.println("ConnectedGraphIterator:: test null=" + testConnectedGraphIterator(graphIter, g));
	}
	
	public static boolean testArrayIterator(Iterator<String> iter, String[] s) {
		int i = 0;
		while (iter.hasNext()) {
			if (!s[i++].equals(iter.next())) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean testListIterator(Iterator<ListNode> iter, int[] nums) {
		int i = 0;
		while (iter.hasNext()) {
			if (iter.next().value != nums[i++]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean testTreePreorderIterator(Iterator<TreeNode> iter, TreeNode root) {
		List<Integer> iterResult = new ArrayList<>();
		while (iter.hasNext()) {
			iterResult.add(iter.next().val);
		}
		List<Integer> standardResult = TreeNode.preorder(root);
		return compareTwoLists(iterResult, standardResult);
	}
	
	public static boolean testTreeInorderIterator(Iterator<TreeNode> iter, TreeNode root) {
		List<Integer> iterResult = new ArrayList<>();
		while (iter.hasNext()) {
			iterResult.add(iter.next().val);
		}
		List<Integer> standardResult = TreeNode.inorder(root);
		return compareTwoLists(iterResult, standardResult);
	}
	
	public static boolean testTreePostorderIterator(Iterator<TreeNode> iter, TreeNode root) {
		List<Integer> iterResult = new ArrayList<>();
		while (iter.hasNext()) {
			iterResult.add(iter.next().val);
		}
		List<Integer> standardResult = TreeNode.postorder(root);
		return compareTwoLists(iterResult, standardResult);
	}
	
	public static boolean testConnectedGraphIterator(Iterator<GraphNode> iter, GraphNode g) {
		List<Integer> iterResult = new ArrayList<>();
		while (iter.hasNext()) {
			iterResult.add(iter.next().val);
		}
		List<Integer> standardResult = GraphUtils.bfsTraversal(g);
		return compareTwoLists(iterResult, standardResult);
	}
	
	private static boolean compareTwoLists(List<Integer> iterResult, List<Integer> standardResult) { 
		if (iterResult.size() != standardResult.size()) {
			return false;
		}
		for (int i = 0; i < iterResult.size(); i++) {
			if (iterResult.get(i) != standardResult.get(i)) {
				return false;
			}
		}
		return true;
	}
}
