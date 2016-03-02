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
*/
public class Get24_2 {

	public static void main(String[] args) {
		Get24_2 obj = new Get24_2();
        obj.get24(new int[]{2, 2, 6, 6});
        System.out.println();
        obj.get24(new int[]{3, 8, 8, 3});
	}
	
	public List<String> get24(int[] nums) {
		List<Integer> cands = new ArrayList<>();
		Arrays.sort(nums);
		for (int num : nums) cands.add(num);
		List<String> result = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			cands.remove(i);
			Map<String, Double> map2Nums = new HashMap<String, Double>();
			Map<String, Double> map3Nums = new HashMap<String, Double>();
			enum3Nums(nums, map2Nums, map3Nums, cands);
			eval(nums[i], map2Nums, map3Nums, result);
			cands.add(i, nums[i]);
		}
		for (String s : result) {
			System.out.println(s);
		}
		return result;
	}
	
	private void enum3Nums(int[] nums, Map<String, Double> map2Nums,
			Map<String, Double> map3Nums, List<Integer> cands) {
		for (int i = 0; i < cands.size(); i++) {
			if (i > 0 && cands.get(i) == cands.get(i - 1))
				continue;
			int num3 = cands.get(i);
			cands.remove(i);
			int num1 = cands.get(0);
			int num2 = cands.get(1);
			enum2Nums(num1, num2, map2Nums); 
			for (String twoNumExpr : map2Nums.keySet()) {
				double twoNumVal = map2Nums.get(twoNumExpr);
				map3Nums.put("" + num3 + "+(" + twoNumExpr + ")", num3 + twoNumVal);
				map3Nums.put("(" + twoNumExpr + ")+" + num3, twoNumVal + num3);
				map3Nums.put("" + num3 + "-(" + twoNumExpr + ")", num3 - twoNumVal);
				map3Nums.put("(" + twoNumExpr + ")-" + num3, twoNumVal - num3);
				map3Nums.put("" + num3 + "*(" + twoNumExpr + ")", num3 * twoNumVal);
				map3Nums.put("(" + twoNumExpr + ")*" + num3, twoNumVal * num3);
				map3Nums.put("" + num3 + "/(" + twoNumExpr + ")", num3 / twoNumVal);
				map3Nums.put("(" + twoNumExpr + ")/" + num3, twoNumVal / num3);
			}
			cands.add(i, num3);
		}
	}
	private Map<String, Double> enum2Nums(int num1, int num2, Map<String, Double> map2Nums) {
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("" + num1 + "+" + num2, (double)num1 + num2);
		map.put("" + num2 + "+" + num1, (double)num2 + num1);
		map.put("" + num1 + "-" + num2, (double)num1 - num2);
		map.put("" + num2 + "-" + num1, (double)num2 - num1);
		map.put("" + num1 + "*" + num2, (double)num1 * num2);
		map.put("" + num2 + "*" + num1, (double)num2 * num1);
		map.put("" + num1 + "/" + num2, (double)num1 / num2);
		map.put("" + num2 + "/" + num1, (double)num2 / num1);
		return map;
	}
	
	private void eval(int num4, Map<String, Double> map2Nums, Map<String, Double> map3Nums, List<String> result) {
		String num4Str = String.valueOf(num4);
		for (String A : map2Nums.keySet()) {
			double valA = map2Nums.get(A);
			for (String B : map2Nums.keySet()) {
				if (A.equals(B))
					continue;
				double valB = map2Nums.get(B);
				if (Math.abs())
			}
		}
			
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
