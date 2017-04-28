package hash;

import java.util.*;

import utils.InputCreator;
import utils.ListComparator;
import utils.WhiteBoard;

public class sum5 {
	static class Tuple {
		int first, second, third;
		public Tuple(int x, int y, int z) {
			first = x;
			second = y;
			third = z;
		}
	}
	
	static class Pair {
		int first, second;
		public Pair(int x, int y) {
			first = x;
			second = y;
		}
	}
	
	public static List<List<Integer>> solve5SumV1(int[] nums, int target) {
		int n = nums.length;
		Set<List<Integer>> result = new HashSet<>();
		Map<Integer, List<Pair>> map = getPairMap(nums);
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				for (int k = j + 1; k < n; k++) {
					int tupleSum = nums[i] + nums[j] + nums[k];
					List<Pair> pairs = map.get(target - tupleSum);
					if (pairs != null) {
						for (Pair p : pairs) {
							if (k < p.first || p.second < i) {
								List<Integer> vector = Arrays.asList(nums[p.first], nums[p.second], nums[i], nums[j], nums[k]);
								Collections.sort(vector);
								result.add(vector);
							}
						}
					}
				}
			}
		}
		return new ArrayList<>(result);
	}
	
	private static Map<Integer, List<Pair>> getPairMap(int[] nums) {
		Map<Integer, List<Pair>> pairMap= new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int pairSum = nums[i] + nums[j];
				if (!pairMap.containsKey(pairSum)) {
					pairMap.put(pairSum, new ArrayList<>());
				}
				pairMap.get(pairSum).add(new Pair(i, j));
			}
		}
		return pairMap;
	}
	
	// 1 + 4 sum
	public static List<List<Integer>> solve5SumV3(int[] nums, int target) {
		int n = nums.length;
		Arrays.sort(nums);
		Set<List<Integer>> result = new HashSet<>();
		Map<Integer, List<Pair>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			// four sum
			for (int j = i + 1; j < n; j++) {
				for (int k = j + 1; k < n; k++) {
					int pairSum = nums[j] + nums[k];
					List<Pair> pairs = map.get(target - pairSum - nums[i]);
					if (pairs != null) {
						for (Pair p : pairs) {
							if (i < p.first && p.second < j) {
								List<Integer> vector = Arrays.asList(nums[i], nums[p.first], nums[p.second], nums[j], nums[k]);
//								Collections.sort(vector);
								result.add(vector);
							}
						}
					}
					if (!map.containsKey(pairSum)) {
						map.put(pairSum, new ArrayList<>());
					}
					map.get(pairSum).add(new Pair(j, k));
				}
			}
		}
		return new ArrayList<>(result);
	}
	
	public static List<List<Integer>> solve5SumV2(int[] nums, int target) {
		int n = nums.length;
		Map<Integer, List<Tuple>> tupleSumMap = getTupleSumMap(nums);
		Set<List<Integer>> result = new HashSet<>();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int pairSum = nums[i] + nums[j];
				List<Tuple> tuples = tupleSumMap.get(target - pairSum);
				if (tuples == null) {
					continue;
				}
				for (Tuple tuple : tuples) {
					if (isValid(tuple, i, j)) {
						List<Integer> vector = Arrays.asList(nums[tuple.first], nums[tuple.second], nums[tuple.third], nums[i], nums[j]);
						Collections.sort(vector);
						result.add(vector);
					}
				}
			}
		}
		return new ArrayList<>(result);
	}
	
	private static boolean isValid(Tuple t, int left, int right) {
		return right < t.first || left > t.third;
	}
	
	private static Map<Integer, List<Tuple>> getTupleSumMap(int[] nums) {
		int n = nums.length;
		Map<Integer, List<Tuple>> tupleSumMap = new HashMap<>();
		for (int i = 0; i + 2 < n; i++) {
			for (int j = i + 1; j + 1 < n; j++) {
				for (int k = j + 1; k < n; k++) {
					int tupleSum = nums[i] + nums[j] + nums[k];
					if (!tupleSumMap.containsKey(tupleSum)) {
						tupleSumMap.put(tupleSum, new ArrayList<>());
					}
					tupleSumMap.get(tupleSum).add(new Tuple(i, j,k));
				}
			}
		}
		return tupleSumMap;
	}
	
	private static void print(List<List<Integer>> list) {
		for (List<Integer> item : list) {
			WhiteBoard.print(item);
		}
	}
	
	public static void main(String[] args) {
		int cost1 = 0, cost2 = 0;
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			int[] nums = InputCreator.crateIntArray(100, 10);
//			int[] nums = {6, -9, -4, 8, -5, -2, -2, 4, -1, 5};
			long start = System.currentTimeMillis();
			List<List<Integer>> res1 = solve5SumV1(nums, 10);
			cost1 += System.currentTimeMillis() - start;
			Collections.sort(res1, new ListComparator());
			start = System.currentTimeMillis();
			List<List<Integer>> res2 = solve5SumV2(nums, 10);
			cost2 += System.currentTimeMillis() - start;
			Collections.sort(res2, new ListComparator());
			if (res1.size() != res2.size() || !res1.equals(res2)) {
				System.out.println("not match @ " + Arrays.toString(nums));
				print(res1);
				System.out.println("=====");
				print(res2);
			} else {
//				System.out.println("ok @ res.size=" + res1.size());
			}
		}
		System.out.printf("cost1=%d, cost2=%d\n", cost1, cost2);
	}

}
