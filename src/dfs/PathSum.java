/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 // 体会hasPathSum2 的简洁性，明确递归结束条件。
public class Solution {
    public boolean hasPathSum1(TreeNode root, int sum) {
        return recur(root, 0, sum);   
    }
    private boolean recur(TreeNode root, int pathSum, int sum) {
        if (root == null) {
            return false;            
        } 
        pathSum += root.val;
        if (root.left == null && root.right == null) {
            return pathSum == sum;
        } else {
            if (root.left != null && recur(root.left, pathSum, sum))
                return true;
            if (root.right != null && recur(root.right, pathSum, sum))
                return true;
            return false;
        }
    }
    
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == sum;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
