package weiss.chap2;

import java.util.Arrays;

/**
 * Based on master theory: T(n) = 2T(n/2) + nlogn, -> T(n) = O(nlogn) 
 * */
public class P17MinimumPositiveSubarraySum {
	// if there does not exists positive subarray sum, return 0;
	public static int minimumPositiveSubarraySum(int[] nums) {
		return minimumPositiveSubarraySum(nums, 0, nums.length - 1);
	}
	
	private static int minimumPositiveSubarraySum(int[] nums, int left, int right) { 
		if (left == right) {
			return nums[left] <= 0 ? Integer.MAX_VALUE : nums[left]; // >< when <= 0, return MAX_VALUE, instead of 0
		}
		int mid = left + (right - left) / 2;
		int leftMinPos = minimumPositiveSubarraySum(nums, left, mid);
		int rightMinPos = minimumPositiveSubarraySum(nums, mid + 1, right);
		
		int midMinPosSum = Integer.MAX_VALUE;
		int[] rightSums = calculateRightSums(nums, mid + 1, right); // n/2
		Arrays.sort(rightSums); // ><
		int leftSum = 0;
		for(int i = mid; i >= left; i--) {
			leftSum += nums[i];
			int rightSumIdx = getSmallestLarger(rightSums, -leftSum); // logn
			if (rightSumIdx >= 0) {
				midMinPosSum = Math.min(midMinPosSum, leftSum + rightSums[rightSumIdx]);
			}
		}
		int res = Math.min(midMinPosSum, Math.min(leftMinPos, rightMinPos));
//		System.out.printf("left=%d, right=%d, res=%d\n", left, right, res);
		return res;
	}
	
	private static int[] calculateRightSums(int[] nums, int from, int to) {
		if (from > to) {
			return new int[0];
		}
		int[] res = new int[to - from + 1];
		res[0] = nums[from];
		for (int i = 1; i < res.length; i++) {
			res[i] = res[i - 1] + nums[from + i];
		}
		return res;
	}
	
	private static int getSmallestLarger(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] > target) { // ><
				right = mid;
			} else {
				left = mid;
			}
		}
		if (nums[left] > target) {
			return left;
		} else if (nums[right] > target) {
			return right; // ><
		} else {
			return -1;
		}
	}
	
	public static int minimumPositiveSubarraySum2(int[] nums) {
		int res = Integer.MAX_VALUE;
		int[] prefixSum = new int[nums.length + 1];
		for (int i = 0; i < nums.length; i++) {
			prefixSum[i + 1] = prefixSum[i] + nums[i];
		}
		
		for (int i = 0; i < nums.length; i++) {
			for (int j = i; j < nums.length; j++) {
				int rangeSum = prefixSum[j + 1] - prefixSum[i];
				if (rangeSum > 0 && rangeSum < res) {
					res = rangeSum;
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
//		int[] nums = {-3, 4, 0, -2, 2};
//		int[] tmp = Arrays.copyOf(nums, nums.length);
//		Arrays.sort(tmp);
//		int[] targets = {-4, 3, -1, -3, 1, 5};
//		for (int t : targets) {
//			int idx = getSmallestLarger(tmp, t);
//			if (idx >= 0) {
//				System.out.printf("target=%d, larger=%d\n", t, tmp[idx]);
//			} else {
//				System.out.printf("not found, target=%d\n", t);
//			}
//		}
		
		int costFast = 0, costSlow = 0;
		int runs = 10;
		for (int i = 0; i < runs; i++) {
			int length = 100000;
			int[] nums = new int[length];
			for (int j = 0; j < length; j++) {
				int abs = (int)(Math.random() * length);
				nums[j] = (int)(Math.random() * 2) == 0 ? abs : -abs;
			}
			long start = System.currentTimeMillis();
			int res1 = minimumPositiveSubarraySum(nums);
			costFast += System.currentTimeMillis() - start;
			start = System.currentTimeMillis();
			int res2 = minimumPositiveSubarraySum2(nums);
			costSlow += System.currentTimeMillis() - start;
			if (res1 != res2) {
				System.out.printf("two result not match: res1=%d, res2=%d\n", res1, res2);
				System.out.println("input=" + Arrays.toString(nums));
			}
		}
		System.out.printf("fast=%f, slow=%f\n", 1.0 * costFast / runs, 1.0 * costSlow / runs);
	}

}
