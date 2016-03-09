public class CreateMaximumNumber {
    // greedy, time: O((m + n) * k * k), space: O(k)
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums2 == null) return null;
        int m = nums1.length, n = nums2.length;
        int min = Math.max(0, k - n), max = Math.min(m, k);
        int[] result = new int[k];
        for (int i = min; i <= max; i++) { 
            int[] tmp = merge(maxSubarray(nums1, i), maxSubarray(nums2, k - i));
            if (greater(tmp, 0, result, 0)) result = tmp;
        }
        return result;
    }
    // O(n)
    private int[] maxSubarray(int[] nums, int k) {
        int[] result = new int[k];
        if (k == 0) return result;
        int n = nums.length;
        for (int i = 0, len = 0; i < n; i++) {
            while (len > 0 && (len + n - i) > k && result[len - 1] < nums[i])
                len--;
            if (len < k)
                result[len++] = nums[i];
        }
        return result;
    }
    
    // dp, time: O(((m + n) * k^2 + k^2) * k) = O((m + n) * k^3), space: O(k^2)
    public int[] maxNumber1(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums2 == null) return null;
        int m = nums1.length, n = nums2.length;
        
        String[][] dp1 = new String[2][Math.min(m, k) + 1];
        List<int[]> maxSubarray1 = selectNums(nums1, dp1);
        String[][] dp2 = new String[2][Math.min(n, k) + 1];
        List<int[]> maxSubarray2 = selectNums(nums2, dp2);
        
        int[] result = new int[k];
        int min = Math.max(0, k - n), max = Math.min(m, k);
        for (int i = min; i <= max; i++) {
            int[] tmp = merge(maxSubarray1.get(i), maxSubarray2.get(k - i));
            if (greater(tmp, 0, result, 0))
                result = tmp;
        }
        return result;
    }
    // time: O(m * k * k), space: O(k^2)
    private List<int[]> selectNums(int[] nums, String[][] dp) {
        int k = dp[0].length - 1;
        int m = nums.length;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = "";
            }
        }
        int prev = 0, curr = 1;
        for (int i = 1; i <= m; i++) {
            int jMax = Math.min(k, i);
            for (int j = 1; j <= jMax; j++) {
                String tmp = dp[prev][j - 1] + String.valueOf(nums[i - 1]);
                if (tmp.compareTo(dp[prev][j]) > 0)
                    dp[curr][j] = tmp;
                else
                    dp[curr][j] = dp[prev][j];
            }
            prev = curr;
            curr = 1 - curr;
        }
        List<int[]> resultList = new ArrayList<>();
        
        for (int len = 0; len <= k; len++) {
            int[] result = new int[len];
            // System.out.println(dp[prev][len]);
            for (int i = 0; i < len; i++) {
                result[i] = dp[prev][len].charAt(i) - '0';
            }
            resultList.add(result);
        }
        return resultList;
    }
    // O(k^2)
    private int[] merge(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int k = m + n;
        int[] result = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; r++) {
            result[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        return result;
    }
    // O(n)
    private boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++; j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
}
