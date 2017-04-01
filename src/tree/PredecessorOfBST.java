package tree;

import utils.TreeNode;

public class PredecessorOfBST {

	public static void main(String[] args) {
		PredecessorOfBST obj = new PredecessorOfBST();
		TreeNode[] nodes = new TreeNode[8];
		for (int i = 1; i < 8; i++) {
			nodes[i] = new TreeNode(i);
		}
		nodes[4].left = nodes[2];
		nodes[4].right = nodes[6];
		nodes[2].left = nodes[1];
		nodes[2].right = nodes[3];
		nodes[6].left = nodes[5];
		nodes[6].right = nodes[7];
		TreeNode curr = null;
		for (int i = 1; i < 8; i++) {
			System.out.println((curr = obj.predecessor(nodes[4], nodes[i])) == null ? "null" : curr.val);
			System.out.println((curr = obj.predecessor2(nodes[4], nodes[i])) == null ? "null" : curr.val);
		}
	}
	
	// recursively
	public TreeNode predecessor(TreeNode root, TreeNode p) {
		if (root == null || p == null) {
			return null;
		}
		if (p.val <= root.val) {
			return predecessor(root.left, p);
		} else {
			TreeNode pred = predecessor(root.right, p);
			return pred == null ? root : pred;
		}
	}
	
	// iteratively
	public TreeNode predecessor2(TreeNode root, TreeNode p) {
		if (root == null || p == null) {
			return null;
		}
		if (p.left != null) {
			return findLargest(p.left);
		}
		TreeNode prev = null;
		TreeNode curr = root;
		while (curr != null) {
			if (p.val < curr.val) {
				curr = curr.left;
			} else if (p.val > curr.val) {
				prev = curr;
				curr = curr.right;
			} else {
				break;
			}
		}
		return prev;
	}
	
	private TreeNode findLargest(TreeNode curr) {
		while (curr.right != null) {
			curr = curr.right;
		}
		return curr;
	}

}
