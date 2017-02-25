package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import utils.TreeNode;

/** simulate parent pointer*/
public class FindKClosest_V3 {
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        if (root == null || k == 0) {
            return result;
        }
        Deque<TreeNode> preStack = new ArrayDeque<>();
        Deque<TreeNode> succStack = new ArrayDeque<>();
        findNearest(root, target, preStack, true);
        findNearest(root, target, succStack, false); 
        if (!preStack.isEmpty() && !succStack.isEmpty() && preStack.peek().val == succStack.peek().val) {
            getNextPre(preStack, target);
        }
        while (k-- > 0) {
            if (preStack.isEmpty()) {
                result.add(getNextSucc(succStack, target));
            } else if (succStack.isEmpty()) {
                result.add(getNextPre(preStack, target));
            } else {
                if (target - preStack.peek().val < succStack.peek().val - target) {
                    result.add(getNextPre(preStack, target));
                } else {
                    result.add(getNextSucc(succStack, target));
                }
            }
        }
        return result;
    }
    
    private void findNearest(TreeNode root, double target, Deque<TreeNode> stack, boolean isPre) {
        while (root != null) {
            stack.push(root);
            if (root.val == target) {
                break;
            } else if (root.val < target) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        if (isPre) {
            while (!stack.isEmpty() && stack.peek().val > target) {
                stack.pop();
            }
        } else {
            while (!stack.isEmpty() && stack.peek().val < target) {
                stack.pop();
            }
        }
    }
    
    private int getNextPre(Deque<TreeNode> preStack, double target) {
        TreeNode curr = preStack.pop();
        int ret = curr.val;
        curr = curr.left;
        while (curr != null) {
            preStack.push(curr);
            curr = curr.right;
        }
        while (!preStack.isEmpty() && preStack.peek().val > target) {
            preStack.pop();
        }
        return ret;
    }
    
    private int getNextSucc(Deque<TreeNode> succStack, double target) {
        TreeNode curr = succStack.pop();
        int ret = curr.val;
        curr = curr.right;
        while (curr != null) {
            succStack.push(curr);
            curr = curr.left;
        }
        while (!succStack.isEmpty() && succStack.peek().val < target) {
            succStack.pop();
        }
        return ret;
    }
}
