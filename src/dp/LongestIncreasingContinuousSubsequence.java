package dp;

/**
 * 给定一个整数数组（下标从 0 到 n-1， n 表示整个数组的规模），请找出该数组中的最长上升连续子序列。（
 * 最长上升连续子序列可以定义为从右到左或从左到右的序列。）
 * */
public class LongestIncreasingContinuousSubsequence {
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
}
