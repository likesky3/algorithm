package weiss.chap1;

import java.util.Arrays;

import sort.QuickSort;

/**
 * select kth largest element in the array
 * Arrays.sort(int[] nums) is much faster than my implementation
 * */
public class P01SelectionProblem {
	public int selectKthLargest(int[] nums, int k) {
		quickPartition(nums, 0, nums.length - 1, nums.length - k - 1);
		return nums[nums.length - k - 1];
	}
	
	private void quickPartition(int[] nums, int left, int right, int k) {
		while (left < right) {
			int mid = partition(nums, left, right);
			if (mid == k) {
				return;
			} else if (mid < k) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
	}
	
//	private void quickPartitionRecursive(int[] nums, int left, int right, int k) {
//		if (left >= right) {
//			return;
//		}
//		int mid = partition(nums, left, right);
//		if (mid == k) {
//			return;
//		} else if (mid < k) {
//			quickPartition(nums, mid + 1, right, k);
//		} else {
//			quickPartition(nums, left, mid - 1, k);
//		}
//	}
	
	private int partition(int[] nums, int left, int right) {
		int pivotIdx = getPivotIdx(left, right);
		int pivot = nums[pivotIdx];
		swap(nums, pivotIdx, right);
		int i = left;
		int j = right - 1;
		while (i <= j) {
			if (nums[i] < pivot) {
				i++;
			} else if (nums[j] >= pivot) {
				j--;
			} else {
				swap(nums, i++, j--);
			}
		}
		swap(nums, i, right);
		return i;
	}
	
	private int getPivotIdx(int left, int right) {
		return left + (int)(Math.random() * (right - left + 1));
	}
	
	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	
	public static void main(String[] args) {
		P01SelectionProblem obj = new P01SelectionProblem();
		int initCost = 0;
		int selectCost = 0;
		int quickSortCost = 0;
		for (int count = 0; count < 10; count++) {
			int length = 10000;
			int[] nums = new int[length];
			long start = System.currentTimeMillis();
			for (int i = 0; i < length; i++) {
				nums[i] = (int) Math.random() * length;
			}
			initCost += System.currentTimeMillis() - start;

			start = System.currentTimeMillis();
			int k = nums.length / 2;
			int res1 = obj.selectKthLargest(nums, k);
			selectCost += System.currentTimeMillis() - start;

			start = System.currentTimeMillis();
			Arrays.sort(nums);
//			QuickSort.quickSort(nums);
			quickSortCost += System.currentTimeMillis() - start;
			if (res1 != nums[nums.length - (k + 1) - 1]) {
				System.out.println("wrong result");
			}
		}
		System.out.printf("init=%d, select=%d, sort=%d\n", initCost, selectCost, quickSortCost);
	}
}
