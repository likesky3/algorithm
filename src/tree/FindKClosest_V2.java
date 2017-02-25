package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import utils.TreeNode;

/**
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 * */
public class FindKClosest_V2 {
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        if (root == null || k == 0) {
            return result;
        }
        Deque<TreeNode> preStack = new ArrayDeque<>();
        Deque<TreeNode> succStack = new ArrayDeque<>();
        initializePredecessors(root, target, preStack);
        initializeSuccessors(root, target, succStack);
        if (!preStack.isEmpty() && !succStack.isEmpty() && preStack.peek().val == succStack.peek().val) {
            getNextPredecessor(preStack, target);
        }
        while (k-- > 0) {
            if (preStack.isEmpty()) {
                result.add(getNextSuccessor(succStack, target));
            } else if (succStack.isEmpty()) {
                result.add(getNextPredecessor(preStack, target));
            } else {
                if (target - preStack.peek().val < succStack.peek().val - target) {
                    result.add(getNextPredecessor(preStack, target));
                } else {
                    result.add(getNextSuccessor(succStack, target));
                }
            }
        }
        return result;
    }
    
	// only push node whose value <= target, preStack.peek() has the largest smaller or eqaual node in the end.
    private void initializePredecessors(TreeNode root, double target, Deque<TreeNode> preStack) {
        while (root != null) {
            if (root.val == target) {
                preStack.push(root);
                break;
            } else if (root.val < target) {
                preStack.push(root);
                root = root.right;
            } else {
                root = root.left;
            }
        }
    }
    
    // only push node whose value >= value, succStack.peek() has the smallest larger or equal node in the end.
    private void initializeSuccessors(TreeNode root, double target, Deque<TreeNode> succStack) {
        while (root != null) {
            if (root.val == target) {
                 succStack.push(root);
                break;
            } else if (root.val < target) {
                root = root.right;
            } else {
                succStack.push(root);
                root = root.left;
            }
        }
    }
    
    // search for the next largest smaller or equal node after pop()
    private int getNextPredecessor(Deque<TreeNode> preStack, double target) {
        TreeNode curr = preStack.pop();
        int ret = curr.val;
        curr = curr.left;
        while (curr != null) {
            preStack.push(curr);
            curr = curr.right;
        }
        return ret;
    }
    
    private int getNextSuccessor(Deque<TreeNode> succStack, double target) {
        TreeNode curr = succStack.pop();
        int ret = curr.val;
        curr = curr.right;
        while (curr != null) {
            succStack.push(curr);
            curr = curr.left;
        }
        return ret;
    }
}
