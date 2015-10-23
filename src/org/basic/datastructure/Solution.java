package org.basic.datastructure;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;

//  Definition for singly-linked linkedst.
  class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
public class Solution {
	public static void main(String[] args) {
		Solution obj = new Solution();
		System.out.println(obj
				.longestCommonSubsequence1("abcdef", "acbdef"));
	}

	public String printLongestCommonSubsequence(String A, String B) {
		if (A == null || B == null)
			return "";
		int m = A.length(), n = B.length();
		int[][] dp = new int[m + 1][n + 1];
		int[][] path = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					path[i][j] = 2;

				}
				if (dp[i - 1][j] > dp[i][j]) {
					dp[i][j] = dp[i - 1][j];
					path[i][j] = 3;
				}
				if (dp[i][j - 1] > dp[i][j]) {
					dp[i][j] = dp[i][j - 1];
					path[i][j] = 1;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		printRecursively(path, A, m, n, sb);
		return sb.reverse().toString();
	}

	private void printRecursively(int[][] path, String A, int m, int n,
			StringBuilder sb) {
		if (m == 0 || n == 0)
			return;
		if (path[m][n] == 2) {
			sb.append(A.charAt(m - 1));
			printRecursively(path, A, m - 1, n - 1, sb);
		} else if (path[m][n] == 1) {
			printRecursively(path, A, m, n - 1, sb);
		} else {
			printRecursively(path, A, m - 1, n, sb);
		}
	}

	public String longestCommonSubsequence1(String A, String B) {
		if (A == null || B == null)
			return "";
		int m = A.length(), n = B.length();
		int[][] dp = new int[m + 1][n + 1];
		int[][][] path = new int[m + 1][n + 1][2];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					path[i][j][0] = i - 1;
					path[i][j][1] = j - 1;
				}
				if (dp[i - 1][j] > dp[i][j]) {
					dp[i][j] = dp[i - 1][j];
					path[i][j][0] = i - 1;
					path[i][j][1] = j;
				}
				if (dp[i][j - 1] > dp[i][j]) {
					dp[i][j] = dp[i][j - 1];
					path[i][j][0] = i;
					path[i][j][1] = j - 1;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int i = m, j = n;
		while (i > 0 && j > 0) {
			if (path[i][j][0] == i - 1 && path[i][j][1] == j - 1) {
				sb.append(A.charAt(i - 1));
			}
			i = path[i][j][0];
			j = path[i][j][1];
		}
		return sb.reverse().toString();
	}

	public TreeNode sortedListToBST(ListNode head) {
		return recur(head);
	}

	private TreeNode recur(ListNode head) {
		if (head == null)
			return null;
		if (head.next == null)
			return new TreeNode(head.val);
		ListNode befMid = findMid(head);
		ListNode afterMid = befMid.next.next;
		TreeNode root = new TreeNode(befMid.next.val);
		System.out.println(root.val);
		befMid.next = null;
		root.left = recur(head);
		root.right = recur(afterMid);
		return root;
	}
    private ListNode findMid(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            if (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> path1 = new LinkedList<TreeNode>();
        getPath(root, p, path1);
        LinkedList<TreeNode> path2 = new LinkedList<TreeNode>();
        getPath(root, q, path2);
        TreeNode lca = root;
        int i = path1.size() - 1, j = path2.size() - 1;
        while (i >= 0 && j >= 0) {
            if (path1.get(i) != path2.get(j))
                break;
            lca = path1.get(i);
            i--; j--;
        }
        System.out.println(lca.val);
        return lca;
    }
	private boolean getPath(TreeNode root, TreeNode x, LinkedList<TreeNode> path) {
		if (root == null) return false;
		path.push(root);
		if (root == x || getPath(root.left, x, path) || getPath(root.right, x, path))
			return true;
		else{
			path.pop();
			return false;
		}
	}
	// Wrong version
    private boolean getPath2(TreeNode root, TreeNode x, LinkedList<TreeNode> path) {
        if (root == null)
            return false;
        path.push(root);
        if (root == x) {
            return true;
        }
        if (getPath(root.left, x, path))
            return true;
        else {
            if (!path.isEmpty()) {
                path.pop();
            }
            if (getPath(root.right, x, path))
                return true;
            else if (!path.isEmpty()) {
                path.pop();
            }
        }
        return false;
    }
    
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        if (head.next.next == null)
            return head.val == head.next.val ? true : false;
        // split input list
        ListNode p = head, q = head;
        while (q != null) {
            if (q.next != null) {
                if (q.next.next != null) {
                    p = p.next;
                    q = q.next.next;
                } else { // size is even
                    break;
                }
            } else { // size is odd
                break;
            }
        }
        
        // reverse second half
        ListNode prev = null, curr = p.next, next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        // check if is palindrome
        q = prev;
        p = head;
        while (q != null) {
            if (p.val != q.val) return false;
            p = p.next;
            q = q.next;
        }
        return true;
    }
}