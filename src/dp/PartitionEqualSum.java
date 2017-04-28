package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.WhiteBoard;

/**
 * Consider the problem with different constrains:
 * 1) given input array only contains positive numbers
 * 2) given input may contains negative elements
 * 3) print all results
 * 
 * What's the constrains of using dp to solve this problem?
 * */
public class PartitionEqualSum {

	public boolean partitionEqualSum(int[] nums){
		// find min & max sum
		int minSum = 0, maxSum = 0;
		int totalSum = 0;
		for (int elem : nums) {
			if (elem <= 0) {
				minSum += elem;
			} else {
				maxSum += elem;
			}
			totalSum += elem;
		}
		if (Math.abs(totalSum) % 2 == 1) { // >< better than totalSum % 2, since -3 % 2 == -2, which wold lead wrong result
			return false;
		}
		int offset = minSum < 0 ? -minSum : 0;
		int n = nums.length;
		int halfSum = totalSum / 2;
		boolean[][] dp = new boolean[n + 1][maxSum - minSum + 1]; // >< not [halfSum + offset + 1][n + 1], not[halfSum - minSum + 1][n + 1]
		dp[0][0 + offset] = true;
		for (int i = 1; i <= n; i++) {
			for (int j = minSum + offset; j <= halfSum + offset; j++) { // >< upper limit do not need to be maxSum + offset
				dp[i][j] = dp[i - 1][j];
				if (!dp[i][j] && j - nums[i - 1] >= 0 && j - nums[i - 1] < dp[0].length) { // >< do not forget: i - nums[j - 1] < dp.length
					dp[i][j] = dp[i - 1][j - nums[i - 1]]; // // >< since nums[j - 1] may be a negative number, so i - nums[j - 1] may be greater than i
				}
			}
		}
		
		return dp[n][halfSum + offset];
	}
	
	/**
	 * not work when there is negative integers in input, dp[sum][range] may depends on unsolved dp[i][j]*/
	public boolean partitionEqualSum_Wrong(int[] nums){
		// find min & max sum
		int minSum = 0, maxSum = 0;
		int totalSum = 0;
		for (int elem : nums) {
			if (elem <= 0) {
				minSum += elem;
			} else {
				maxSum += elem;
			}
			totalSum += elem;
		}
		if (Math.abs(totalSum) % 2 == 1) { // >< better than totalSum % 2, since -3 % 2 == -2, which wold lead wrong result
			return false;
		}
		int offset = minSum < 0 ? -minSum : 0;
		int n = nums.length;
		int halfSum = totalSum / 2;
		boolean[][] dp = new boolean[maxSum - minSum + 1][n + 1]; // >< not [halfSum + offset + 1][n + 1], not[halfSum - minSum + 1][n + 1]
		dp[0 + offset][0] = true;
		for (int i = minSum + offset; i <= halfSum + offset; i++) { // >< upper limit is not maxSum + offset
//			dp[i][0] = false;
			for (int j = 1; j <= n; j++) {
//				System.out.printf("i=%d, j=%d, i-nums[j-1]=%d\n", i, j, i - nums[j - 1]);
				dp[i][j] = dp[i][j - 1];
				if (!dp[i][j] && i - nums[j - 1] >= 0 && i - nums[j - 1] < dp.length) { // >< do not forget: i - nums[j - 1] < dp.length
					dp[i][j] = dp[i - nums[j - 1]][j - 1]; // >< since nums[j - 1] may be a negative number, so i - nums[j - 1] may be greater than i
				}
			}
		}
		return dp[halfSum + offset][n];
	}
	
	public boolean partitionEqualSumDFS(int[] nums) {
		int totalSum = 0;
		for (int elem : nums) {
			totalSum += elem;
		}
		if (Math.abs(totalSum) % 2 == 1) {
			return false;
		}
		return dfs(nums, 0, 0, totalSum / 2);
	}
	
	private boolean dfs(int[] nums, int start, int currSum, int targetSum) {
		if (currSum == targetSum) {
			return true;
		} else if (start == nums.length) {
			return false;
		}
		return dfs(nums, start + 1, currSum + nums[start], targetSum) || dfs(nums, start + 1, currSum, targetSum);
	}
	
	// assumption: all elements in nums[] is positive
	public static void printPartitionSet(int[] nums) {
		if (nums == null || nums.length == 0) {
            return;
        }
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        if ((totalSum & 1) == 1) {
        	System.out.println("can not partition");
            return;
        }
        int n = nums.length;
        boolean[][] dp = new boolean[totalSum / 2 + 1][n + 1];
        Arrays.fill(dp[0], true);
        for (int i = 1; i <= totalSum / 2; i++) {
            dp[i][0] = false;
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1] || (i >= nums[j - 1] && dp[i - nums[j - 1]][j - 1]);
            }
        }
        if (dp[totalSum / 2][n]) {
        	print(nums, dp, totalSum / 2, n, new ArrayList<>());        		
        } else {
        	System.out.println("can not partition");
        }
	}
	
	private static void print(int[] nums, boolean[][] dp, int currSum, int range, List<Integer> path) {
		if (currSum == 0) {
			WhiteBoard.print(path);
			return;
		}
		if (dp[currSum][range - 1]) {
			print(nums, dp, currSum, range - 1, path);
		}
		
		if (currSum - nums[range - 1] >= 0 && dp[currSum - nums[range - 1]][range - 1]) {
			path.add(nums[range - 1]);
			print(nums, dp, currSum - nums[range - 1], range - 1, path);
			path.remove(path.size() - 1);
		}
	}
	
	private static void testPrint() {
		for (int i = 0; i < 5; i++) {
			int length = 5;
			int[] nums = new int[length];
			
			// create positive random array
			for (int j = 0; j < length; j++) { 
				nums[j] = (int) (Math.random() * length) + 1;
			}
			System.out.println("input: " + Arrays.toString(nums));
			printPartitionSet(nums);
			System.out.println();
		}
	}
	
	private static void testCanPartition() {
		PartitionEqualSum obj = new PartitionEqualSum();
//		for (int i = 0; i < 100; i++) {
		for (int i = 0; i < 1; i++) {
			int length = 5;
//			int[] nums = new int[length];
//			for (int j = 0; j < length; j++) {
//				int abs = (int) (Math.random() * length);
//				nums[j] = (int) (Math.random() * 2) == 0 ? abs : -abs;
//			}
			int[] nums = {0, 0, -3, 0, -3}; 
//			int[] nums = {0, -4, 0, -1, -1}; // once leads ArrayIndexOutOfBounds in dp[i][j] = dp[i - nums[j - 1]][j - 1]; 
			System.out.println("input=" + Arrays.toString(nums));
			boolean resDP = obj.partitionEqualSum(nums);
			boolean resDFS = obj.partitionEqualSumDFS(nums); 
			if (resDP != resDFS) {
				System.out.printf("two methods not match, resDP=%b, resDFS=%b\n ", resDP, resDFS);
			}
		}
	}
	
	public static void main(String[] args) {
//		testPrint();
		testCanPartition();
	}

}
