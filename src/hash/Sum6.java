package hash;

import java.util.*;

import utils.InputCreator;
import utils.ListComparator;
import utils.WhiteBoard;

public class Sum6 {
	public List<List<Integer>> get6Sum(int[] nums, int target) {
		Set<List<Integer>> result = new HashSet<>();
		Map<Integer, List<List<Integer>>> map = new HashMap<>();
		int n = nums.length;
		Arrays.sort(nums);
		for (int i = 0; i + 2 < n; i++) {
			for (int j = i + 1; j + 1 < n; j++) {
				for (int k = j + 1; k < n; k++) {
					int sum3 = nums[i] + nums[j] + nums[k];
					List<List<Integer>> tuples = map.get(target - sum3);
					if (tuples != null) {
						for (List<Integer> tuple : tuples) {
							if (tuple.get(2) < i) {
								List<Integer> solu = Arrays.asList(nums[tuple.get(0)], nums[tuple.get(1)], nums[tuple.get(2)], nums[i], nums[j], nums[k]);
//								Collections.sort(solu);
								result.add(solu);
							}
						}
					}
					if (!map.containsKey(sum3)) {
						map.put(sum3, new ArrayList<>());
					}
					map.get(sum3).add(Arrays.asList(i, j, k));
				}
			}
		}
		return new ArrayList<>(result);
	}
	
	
	// naive O(n^5)
	public List<List<Integer>> get6SumSlow(int[] nums, int target) {
		Arrays.sort(nums);
		Set<List<Integer>> result = new HashSet<>();
		int n = nums.length;
		for (int i1 = 0; i1 + 5 < n; i1++) {
			for (int i2 = i1 + 1; i2 + 4 < n; i2++) {
				for (int i3 = i2 + 1; i3 + 3 < n; i3++) {
					for (int i4 = i3 + 1; i4 + 2 < n; i4++) {
						int sum4 = nums[i1] + nums[i2] + nums[i3] + nums[i4];
						List<Integer> vector = Arrays.asList(nums[i1], nums[i2], nums[i3], nums[i4]);
						twoSum(nums, i4 + 1, target - sum4, vector, result);
					}
				}
			}
		}
		return new ArrayList<>(result);
	}
	
	private void twoSum(int[] nums, int from, int target, List<Integer> vector, Set<List<Integer>> result) {
		int left = from;
		int right = nums.length - 1;
		while (left < right) {
			if (left > from && nums[left] == nums[left - 1]) {
				left++;
				continue;
			}
			int sum2 = nums[left] + nums[right];
			if (sum2 == target) {
				List<Integer> solu = new ArrayList<>(vector);
				solu.add(nums[left++]);
				solu.add(nums[right--]);
				result.add(solu);
			} else if (sum2 > target) {
				right--;
			} else {
				left++;
			}
		}
	}
	
	private static void print(List<List<Integer>> list) {
		for (List<Integer> item : list) {
			WhiteBoard.print(item);
		}
	}
	public static void main(String[] args) {
		Sum6 obj = new Sum6();
		int length = 10;
		for (int c = 0; c < 100; c++) {
			int[] nums = InputCreator.crateIntArray(10, 10);
//			int[] nums = {4, -3, -7, -8, 5, -8, -7, -8};
//			int[] nums = {4, -4, 2, 3, 5, 0, 4, 6, 0};
//			System.out.println("input: " + Arrays.toString(nums));
			List<List<Integer>> res1 = obj.get6Sum(nums, 10);
			Collections.sort(res1, new ListComparator());
//			System.out.println("O(n^2) result");
//			print(res1);
			List<List<Integer>> res2 = obj.get6SumSlow(nums, 10);
//			System.out.println("O(n^5) result");
//			print(res2);
			Collections.sort(res2, new ListComparator());
			if (res1.size() != res2.size() || !res1.equals(res2)) {
				System.out.println("unmatch");
				System.out.println("input: " + Arrays.toString(nums));
				continue;
			} else {
				System.out.println("ok@size=" + res1.size());
			}
		}
	}
}
