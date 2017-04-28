package utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
	public int key;
	public int val;
	public TreeNode left, right;
	public TreeNode(int value) {
		key = value;
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
        for (int i = 0; i < array.length; i++) {
        	array[i] = array[i].trim();
        }
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
    
    public static List<Integer> preorder(TreeNode root) {
    	List<Integer> result = new ArrayList<>();
    	TreeNode curr = root;
    	Deque<TreeNode> stack = new ArrayDeque<>();
    	while (!stack.isEmpty() || curr != null) {
    		if (curr != null) {
    			if (curr.right != null) {
    				stack.offerFirst(curr.right);
    			}
    			result.add(curr.val);
    			curr = curr.left;
    		} else {
    			curr = stack.pollFirst();
    		}
    	}
    	return result;
    }
    
    public static List<Integer> inorder(TreeNode root) {
    	List<Integer> result = new ArrayList<>();
    	TreeNode curr = root;
    	Deque<TreeNode> stack = new ArrayDeque<>();
    	while (!stack.isEmpty() || curr != null) {
    		if (curr != null) {
    			stack.offerFirst(curr);
    			curr = curr.left;
    		} else {
    			curr = stack.pollFirst();
    			result.add(curr.val);
    			curr = curr.right;
    		}
    	}
    	return result;
    }
    
    public static List<Integer> postorder(TreeNode root) {
    	List<Integer> result = new ArrayList<>();
    	if (root == null) {
    	    return result;
    	}
    	TreeNode prev = null, curr = null;
    	Deque<TreeNode> stack = new ArrayDeque<>();
    	stack.offerFirst(root);
    	while (!stack.isEmpty()) {
    	    curr = stack.peekFirst();
    		// go down
    		if (prev == null || prev.left == curr || prev.right == curr) {
    			if (curr.left != null) {
    				stack.offerFirst(curr.left);
    			} else if (curr.right != null) {
    				stack.offerFirst(curr.right);
    			} else {
    				result.add(curr.val);
    				stack.pollFirst(); // remove curr from stack
    			}
    		} else if (curr.left == prev && curr.right == null || curr.right == prev) { // go up from left with no right child or go up from right
    			result.add(curr.val);
    			stack.pollFirst();
    		} else { // go up from left and root has right child
    			stack.offerFirst(curr.right);
    		}
    		prev = curr; // update prev
    	}
    	return result;
    } 
}

