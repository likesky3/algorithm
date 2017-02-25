package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import utils.TreeNode;

public class FindKClosest_V1 {
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
        if (root == null || k == 0) {
            return new ArrayList<>();
        }
        List<Integer> inorder = inorderTraverse(root);
        int left = findLargestSmallEqual(inorder, 0, inorder.size() - 1, target);
        int right = left + 1;
        int n = inorder.size();
        List<Integer> result = new ArrayList<>();
        for (int p = 0; p < k; p++) {
            if (right >= n || (left >= 0 && target - inorder.get(left) < inorder.get(right) - target)) {
                result.add(inorder.get(left--));
            } else {
                result.add(inorder.get(right++));
            }
        }
        return result;
    }
    private List<Integer> inorderTraverse(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                result.add(curr.val);
                curr = curr.right;
            }
        }
        return result;
    }
    private int findLargestSmallEqual(List<Integer> data, int left, int right, double target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (data.get(mid) <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (data.get(right) <= target) {
            return right;
        } else if (data.get(left) <= target) {
            return left;
        } else {
            return -1;
        }
    }
}
