package sort;

import java.util.List;

public class ReversePairs {
	public static void main(String[] args) {
		int[] nums = {5, 2, 6, 1};
		ReversePairs obj = new ReversePairs();
		int ans =  obj.reversePairs(nums);
		System.out.println(ans);
	}
	public int reversePairs(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int[] helper = new int[nums.length];
		return countWhileMergeSort(nums, 0, nums.length - 1, helper);
	}

	private int countWhileMergeSort(int[] nums, int left, int right, int[] helper) {
		if (left >= right) {
			return 0;
		}
		int mid = left + (right - left) / 2;
		int cnt = countWhileMergeSort(nums, left, mid, helper);
		cnt += countWhileMergeSort(nums, mid + 1, right, helper);
		cnt += merge(nums, left, mid, right, helper);
		return cnt;
	}

	private int merge(int[] nums, int left, int mid, int right, int[] helper) {
		// get reverse pairs count
		int cnt = 0;
		int i = left, j = mid + 1, k = left;
		while (i <= mid && j <= right) {
			if (nums[i] <= 2 * nums[j]) {
				i++;
			} else {
				cnt += mid + 1 - i;
				j++;
			}
		}
		// normal merge
		System.arraycopy(nums, left, helper, left, right - left + 1);
		i = left;
		j = mid + 1;
		while (i <= mid && j <= right) {
			if (helper[i] < helper[j]) {
				nums[k++] = helper[i++];
			} else {
				nums[k++] = helper[j++];
			}
		}
		while (i <= mid) {
			nums[k++] = helper[i++];
		}
		
//		System.out.printf("[%d, %d]=[%d, %d]=%d\n", left, right, nums[left], nums[right], cnt);
		return cnt;
	}
}
