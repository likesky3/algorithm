package recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DivideInto3EqualSumParts {
	static class PartitionComp implements Comparator<List<Integer>> {

		@Override
		public int compare(List<Integer> o1, List<Integer> o2) {
			if (o1 == null && o2 == null) {
				return 0;
			} else if (o1 == null || o2 == null) {
				return o1 == null ? -1 : 1;
			}
			
			if (o1.size() != o2.size()) {
				return o1.size() < o2.size() ? -1 : 1;
			} else {
				Collections.sort(o1);
				Collections.sort(o2);
				for (int i = 0; i < o1.size(); i++) {
					Integer a = o1.get(i);
					Integer b = o2.get(i);
					if (a.compareTo(b) != 0) {
						return a.compareTo(b);
					}
				}
				return 0;
			}
		}
		
	}
	
	public static void divide(int[] nums) {
		int totalSum = 0;
		for (int n : nums) {
			totalSum += n;
		}
		
		if (totalSum % 3 == 0) {
			Set<List<List<Integer>>> result = new HashSet<>();
			boolean[] visited = new boolean[nums.length];
			recur(nums, visited, 0, result, new ArrayList<>(), new ArrayList<Integer>(), 0, totalSum / 3);
			for (List<List<Integer>> solu : result) {
				print(solu);
			}
		}
	}
	
	private static void recur(int[] nums, boolean[] visited, int start, Set<List<List<Integer>>> result, List<List<Integer>>  solu, List<Integer> part, int partSum, int sum) {
		if (partSum == sum) {
			List<Integer> copy = new ArrayList<Integer>(part);
			Collections.sort(copy);
			solu.add(copy);
			if (solu.size() == 1) {
				recur(nums, visited, 0, result, solu, new ArrayList<>(), 0, sum); // search for 2nd part
			} else { // solu.size == 2
				List<Integer> part3 = new ArrayList<>();
				for (int i = 0; i < nums.length; i++) {
					if (!visited[i]) {
						part3.add(nums[i]);
					}
				}
				Collections.sort(part3);
				solu.add(part3);
				List<List<Integer>> soluCopy  = new ArrayList<>(solu);
				Collections.sort(soluCopy, new PartitionComp());
				result.add(soluCopy);
				solu.remove(solu.size() - 1); // >< backtrace, restore status
			} 
			solu.remove(solu.size() - 1); // >< backtrace, restore status
			return;
		} /* else if (partSum > sum) { // >< partSum may decrease later
			return;
		} */ 
		
		while (start < nums.length && visited[start]) {
			start++;
		}
		if (start == nums.length) {
			return;
		}
		
		// choose nums[start]
		part.add(nums[start]);
		visited[start] = true;
		recur(nums, visited, start, result, solu, part, partSum + nums[start], sum);
		visited[start] = false;
		part.remove(part.size() - 1);
		
		// do not choose nums[start]
		recur(nums, visited, start + 1, result, solu, part, partSum, sum);
	}
	
	private static void print(List<List<Integer>> solu) {
		int i = 0;
		for (List<Integer> part : solu) {
			System.out.print("part-" + i++ + ": ");
			for (Integer elem : part) {
				System.out.print(elem + ",");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 5, -2};
		divide(nums);
	}
}
