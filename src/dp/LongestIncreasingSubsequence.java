package dp;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    // Method1 : dp, time O(n^2), space O(n)
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n]; // dp[i], longest increasing subsequence(LIS) ending with the ith num.
        Arrays.fill(dp, 1);
        int result = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] >= nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        result = Math.max(result, dp[n - 1]);
        return result;
    }
    // TODO:Method 2: time O(nlogn)
}
