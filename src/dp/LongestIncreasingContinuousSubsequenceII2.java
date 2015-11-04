package dp;

public class LongestIncreasingContinuousSubsequenceII2 {
	private final int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	int[][] A;
	int[][] dp;
	boolean[][] used;
	
	public int solve(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) return 0;
        this.A = A;
        int max = 1;
        int m = A.length, n = A[0].length;
        used = new boolean[m][n];
        dp = new int[m][n]; // dp[i][j], longest increasing continuous subsequence started at A[i][j]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                used[i][j] = true;
                if (dp[i][j] == 0) {
                	max = Math.max(max, memSearch(i, j));
                }
                
                used[i][j] = false;
            }
        }
        return max;
    }
    public int memSearch(int i, int j) {
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
            	 dp[i][j] = Math.max(dp[i][j], memSearch(i2, j2) + 1);
            }
            used[i2][j2] = false;
        }
        return dp[i][j];
    }
    
}
