package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** how many possible solutions
 * Given 4 numbers, print all expressions getting result 24 using +, -, *, /, (, ).
For example, 8 8 3 3 => (8 / ( 3 - 8/3)) = 24,
2 2 6 6 => ((2+6)*6/2) = 24, ((2+6)/(2/6)) = 24, ...
hint1: calculate how large the possible solution can be?
hint2: one way to solve is to try reverse polish notation.

Given 2 numbers, 2 * 4 = 8
Given 3 numbers, C(3, 2) * 2 * 4 * 8 = 192
Given 4 numbers, C(4, 1) * 2 * 4 * 192 = 6144

idea: first permulate number, then select operators
E2 # E2: (a # b) # (c # d)
a # E3: a # (b # (c # d)), a # ((b # c) # d)
E3 # a: (a # (b # c)) # d, ((a # b) # c) # d
*/
public class Get24_2 {

	public static void main(String[] args) {
		Get24_2 obj = new Get24_2();
        obj.get24(new int[]{2, 2, 6, 6});
        System.out.println();
        obj.get24(new int[]{3, 8, 8, 3});
	}
	
	public List<String> get24(int[] nums) {
		Arrays.sort(nums);
		boolean[] visited = new boolean[4];
		int[] perm = new int[nums.length];
		Set<String> result = new HashSet<>();
		helper(nums, 0, visited, perm, result);
		for (String s : result) {
			System.out.println(s);
		}
		List<String> resultList = new ArrayList<>();
		resultList.addAll(result);
		return resultList;
	}
	
	private void helper(int[] nums, int k, boolean[] visited, int[] perm, Set<String> result) {
		if (k == 4) {
			buildResult(perm, result);
		} else {
			for (int i = 0; i < nums.length; i++) { 
				if (visited[i] || i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])
					continue;
				perm[k] = nums[i];
				visited[i] = true;
				helper(nums, k + 1, visited, perm, result);
				visited[i] = false;
			}
		}
	}
	private void buildResult(int[] perm, Set<String> result) {
		enum22(perm, result);
		enum1_3(perm[0], enum3Nums(perm[1], perm[2], perm[3]), result);
		enum3_1(enum3Nums(perm[0], perm[1], perm[2]), perm[3], result);
	}
	private void enum22(int[] perm, Set<String> result) {
		Map<String, Double> map1 = enum2Nums(perm[0], perm[1]);
		Map<String, Double> map2 = enum2Nums(perm[2], perm[3]);
		for (String A : map1.keySet()) {
			double valA = map1.get(A);
			for (String B : map2.keySet()) {
				double valB = map2.get(B);
				if (Math.abs(valA + valB - 24) < epsilon) {
					result.add("(" + A + ")+(" + B + ")");
				}
				if (Math.abs(valA - valB - 24) < epsilon) {
					result.add("(" + A + ")-(" + B + ")");
				}
				if (Math.abs(valA * valB - 24) < epsilon) {
					result.add("(" + A + ")*(" + B + ")");
				}
				if (Math.abs(valA / valB - 24) < epsilon) {
					result.add("(" + A + ")/(" + B + ")");
				}
			}
		}
	}
	private Map<String, Double> enum3Nums(int num1, int num2, int num3) {
		Map<String, Double> map3Nums = new HashMap<>();
		Map<String, Double> map2Nums = enum2Nums(num1, num2); 
		for (String twoNumExpr : map2Nums.keySet()) {
			double twoNumVal = map2Nums.get(twoNumExpr);
			map3Nums.put("(" + twoNumExpr + ")+" + num3, twoNumVal + num3);
			map3Nums.put("(" + twoNumExpr + ")-" + num3, twoNumVal - num3);
			map3Nums.put("(" + twoNumExpr + ")*" + num3, twoNumVal * num3);
			map3Nums.put("(" + twoNumExpr + ")/" + num3, twoNumVal / num3);
		}
		map2Nums = enum2Nums(num2, num3);
		for (String twoNumExpr : map2Nums.keySet()) {
			double twoNumVal = map2Nums.get(twoNumExpr);
			map3Nums.put("" + num1 + "+(" + twoNumExpr + ")", num1 + twoNumVal);
			map3Nums.put("" + num1 + "-(" + twoNumExpr + ")", num1 - twoNumVal);
			map3Nums.put("" + num1 + "*(" + twoNumExpr + ")", num1 * twoNumVal);
			map3Nums.put("" + num1 + "/(" + twoNumExpr + ")", num1 / twoNumVal);
		}
		
		return map3Nums;
	}
	private Map<String, Double> enum2Nums(int num1, int num2) {
		Map<String, Double> map2Nums = new HashMap<>();
		map2Nums.put("" + num1 + "+" + num2, (double)num1 + num2);
		map2Nums.put("" + num1 + "-" + num2, (double)num1 - num2);
		map2Nums.put("" + num1 + "*" + num2, (double)num1 * num2);
		map2Nums.put("" + num1 + "/" + num2, (double)num1 / num2);
		return map2Nums;
	}
	private void enum3_1(Map<String, Double> map3Nums, int num4, Set<String> result) {
		String num4Str = String.valueOf(num4);
		for (String threeNumsExpr : map3Nums.keySet()) {
			double threeNumsVal = map3Nums.get(threeNumsExpr);
			if (Math.abs(num4 + threeNumsVal - 24) < epsilon) {
				result.add("(" + threeNumsExpr + ")+" + num4Str); 
			}
			if (Math.abs(threeNumsVal - num4 - 24) < epsilon) {
				result.add("(" + threeNumsExpr + ")-" + num4Str);
			}
			if (Math.abs(num4 * threeNumsVal - 24) < epsilon) {
				result.add("(" + threeNumsExpr + ")*" + num4Str);
			}
			if (Math.abs(threeNumsVal / num4 - 24) < epsilon) {
				result.add("(" + threeNumsExpr + ")/" + num4Str);
			}
		}
	}
	private void enum1_3(int num4,Map<String, Double> map3Nums, Set<String> result) {
		String num4Str = String.valueOf(num4);
		for (String threeNumsExpr : map3Nums.keySet()) {
			double threeNumsVal = map3Nums.get(threeNumsExpr);
			if (Math.abs(num4 + threeNumsVal - 24) < epsilon) {
				result.add(num4Str + "+(" + threeNumsExpr + ")");
				result.add("(" + threeNumsExpr + ")+" + num4Str); // need or not?
			}
			if (Math.abs(num4 - threeNumsVal - 24) < epsilon) {
				result.add(num4Str + "-(" + threeNumsExpr + ")");
			}
			if (Math.abs(threeNumsVal - num4 - 24) < epsilon) {
				result.add("(" + threeNumsExpr + ")-" + num4Str);
			}
			if (Math.abs(num4 * threeNumsVal - 24) < epsilon) {
				result.add(num4Str + "*(" + threeNumsExpr + ")");
				result.add("(" + threeNumsExpr + ")*" + num4Str);
			}
			if (Math.abs(num4 / threeNumsVal - 24) < epsilon) {
				result.add(num4Str + "/(" + threeNumsExpr + ")");
			}
			if (Math.abs(threeNumsVal / num4 - 24) < epsilon) {
				result.add("(" + threeNumsExpr + ")/" + num4Str);
			}
		}
	}
	private static double epsilon = 0.000001;
	private static Set<String> operatos = new HashSet<>();
	static {
		operatos.add("+");
		operatos.add("-");
		operatos.add("*");
		operatos.add("/");
	}
}
