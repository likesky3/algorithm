package dp;

/**
 * 给定一个整数矩阵（其中，有 n 行， m 列），请找出矩阵中的最长上升连续子序列。
 * （最长上升连续子序列可从任意行或任意列开始，向上/下/左/右任意方向移动）。
 * input:[[1 ,2 ,3 ,4 ,5],
  [16,17,24,23,6],
  [15,18,25,22,7],
  [14,19,20,21,8],
  [13,12,11,10,9]]
  output: 25
 * */
public class LongestIncreasingContinuousSubsequenceII {
    private final int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	// Method 1: time limit exceed. time: O(n*m*n*m), space: O(n*m)
	public int longestIncreasingContinuousSubsequenceII(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) return 0;
        int max = 1;
        int m = A.length, n = A[0].length;
        boolean[][] used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                used[i][j] = true;
                max = Math.max(max, helper(A, i, j, used, 1));
                used[i][j] = false;
            }
        }
        return max;
    }
    private int helper(int[][] A, int i, int j, boolean[][] used, int len) {
        int ret = len;
        for (int k = 0; k < delta.length; k++) {
            int i2 = i + delta[k][0];
            int j2 = j + delta[k][1];
            if (i2 < 0 || i2 >= A.length || j2 < 0 || j2 >= A[0].length 
                || used[i2][j2] || A[i2][j2] <= A[i][j]) {
                    continue;
            }
            used[i2][j2] = true;
            int tmp = helper(A, i2, j2, used, len + 1);
            ret = Math.max(ret, tmp);
            used[i2][j2] = false;
        }
        return ret;
    }
    // Method 2: 记忆式搜索（DFS + DP）, time: O(n*m), space: O(n*m)
    public int longestIncreasingContinuousSubsequenceII2(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) return 0;
        int max = 1;
        int m = A.length, n = A[0].length;
        boolean[][] used = new boolean[m][n];
        int[][] dp = new int[m][n]; // dp[i][j], longest increasing continuous subsequence started at A[i][j]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                used[i][j] = true;
                if (dp[i][j] == 0) {
                    helper2(A, i, j, used, dp);
                }
                max = Math.max(max, dp[i][j]);
                used[i][j] = false;
            }
        }
        return max;
    }
    public void helper2(int[][] A, int i, int j, boolean[][] used, int[][] dp) {
        dp[i][j] = 1;
        for (int k = 0; k < delta.length; k++) {
            int i2 = i + delta[k][0];
            int j2 = j + delta[k][1];
            if (i2 < 0 || i2 >= A.length || j2 < 0 || j2 >= A[0].length 
                || used[i2][j2] || A[i2][j2] <= A[i][j]) {
                    continue;
            }
            used[i2][j2] = true;
             if (dp[i2][j2] == 0) {
                helper2(A, i2, j2, used, dp);
             }
            dp[i][j] = Math.max(dp[i][j], dp[i2][j2] + 1);
            used[i2][j2] = false;
        }
    }
}
