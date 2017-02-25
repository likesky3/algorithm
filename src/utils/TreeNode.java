package utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int value) {
		val = value;
		left = null;
		right = null;
	}
	
	public static void printSerializedTree(TreeNode root) {
		System.out.println(serialize(root));
	}
	
	public static String serialize(TreeNode root) {
        if (root == null) {
			return "";
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		StringBuilder data = new StringBuilder();
		boolean hasNextLevel = true;
		while (hasNextLevel) {
			hasNextLevel = false;
			int currLevelLength = queue.size();
			while (currLevelLength > 0) {
				TreeNode curr = queue.poll();
				currLevelLength--;
				if (curr != null) {
					data.append(curr.val).append(",");
					queue.offer(curr.left);
					queue.offer(curr.right);
					if (curr.left != null || curr.right != null) {
						hasNextLevel = true;
					}
				} else {
					data.append("#,");
				}
			}
		}
		return data.substring(0, data.length() - 1);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] array = data.split(",");
		TreeNode root = new TreeNode(Integer.parseInt(array[0]));
		TreeNode curr = root;
		List<TreeNode> nodes = new ArrayList<>();
		nodes.add(root);
		int i = 0, j = 1;
		while (j < array.length) {
			curr.left = array[j].equals("#") ? null : new TreeNode(Integer.parseInt(array[j]));
			if (j + 1 < array.length) {
				curr.right = array[j + 1].equals("#") ? null : new TreeNode(Integer.parseInt(array[j + 1]));
			}
			if (curr.left != null) {
			    nodes.add(curr.left);
			}
			if (curr.right != null) {
			    nodes.add(curr.right);
			}
			i++;
			j += 2;
			curr = nodes.get(i);
		}
		return root;
    }
}

