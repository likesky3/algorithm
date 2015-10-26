package dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个整数数组（下标从 0 到 n-1， n 表示整个数组的规模），请找出该数组中的最长上升连续子序列。（
 * 最长上升连续子序列可以定义为从右到左或从左到右的序列。）
 * */
public class LongestIncreasingContinuousSubsequence {
    @Test
    public void testLICS() {
        LongestIncreasingContinuousSubsequence obj = new LongestIncreasingContinuousSubsequence();
        int[] A = {1,4,2,5,3};
        Assert.assertEquals(2, obj.longestIncreasingContinuousSubsequence(A));
    }
    // Method 1: dp, time: O(n), space: O(1)
	public int longestIncreasingContinuousSubsequence(int[] A) {
        // Write your code here
        if (A == null || A.length == 0) return 0;
        int asendLen = 1, desendLen = 1;
        int asendLast = A[0], desendLast = A[0];
        int maxLen = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] >= asendLast) {
                asendLen++;
            } else {
                maxLen = Math.max(maxLen, asendLen);
                asendLen = 1;
            }
            asendLast = A[i];
            if (A[i] <= desendLast) {
                desendLen++;
            } else {
                maxLen = Math.max(maxLen, desendLen);
                desendLen = 1;
            }
            desendLast = A[i];
        }
        maxLen = Math.max(maxLen, Math.max(asendLen, desendLen));
        return maxLen;
    }
	// Method 2: in a more dp style, time: O(n), space: O(n)
    public int longestIncreasingContinuousSubsequence2(int[] A) {
        // Write your code here
        if (A == null || A.length == 0) return 0;
        int n = A.length;
        int[] asend = new int[n]; // asend[i], LICS ending at the ith num 
        int[] desend = new int[n];
        asend[0] = 1;
        desend[0] = 1;
        int result = 1;
        for (int i = 1; i < n; i++) {
            asend[i] = A[i] >= A[i - 1] ? (asend[i - 1] + 1) : 1;
            result = Math.max(result, asend[i]);
            desend[i] = A[i] <= A[i - 1] ? (desend[i - 1] + 1) : 1;
            result = Math.max(result, desend[i]);
        }
        result = Math.max(result, asend[n - 1]);
        result = Math.max(result, desend[n - 1]);
        return result;
    }
}
