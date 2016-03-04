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

Key idea: if you have K numbers, 
use whatever method(select two numbers, one operation) to make it K-1 numbers.

Hard points:
1. How do I get (a(bc))d ?
*/
public class Get24_2_2 {

    public static void main(String[] args) {
        Get24_2_2 obj = new Get24_2_2();
        obj.get24(new int[]{2, 2, 6, 6});
        obj.get24(new int[]{3, 8, 8, 3, 10, 7});
    }
    
    public List<String> get24(int[] nums) {
        int n = nums.length;
        boolean[] visited = new boolean[n];
        Set<String> resultSet = new HashSet<>();
        helper(nums, 0, visited, new int[n], resultSet);
        List<String> result = new ArrayList<>();
        result.addAll(resultSet);
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println();
        return result;
    }
    private void helper(int[] nums, int k, boolean[] visited, int[] items, Set<String> result) {
        if (k == nums.length) {
            buildResult(items, 0, result);
        } else {
            for (int i = 0; i < nums.length; i++) { 
                if (visited[i] || i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])
                    continue;
                items[k] = nums[i];
                visited[i] = true;
                helper(nums, k + 1, visited, items, result);
                visited[i] = false;
            }
        }
    }
    
    private Map<String, Double> buildResult(int[] nums, int idx, Set<String> result) {
        Map<String, Double> map = new HashMap<>();
        if (idx == nums.length - 1) {
            map.put(String.valueOf(nums[idx]), (double)nums[idx]);
        } else if (idx + 2 == nums.length) { // the last two number
            enum2Nums(nums[idx], nums[idx + 1], String.valueOf(nums[idx]),  String.valueOf(nums[idx + 1]), 
                    map, idx == 0, result);
        } else {
            // nums[i] # (nums[i + 1]...nums[n - 1])
            Map<String, Double> map2 = buildResult(nums, idx + 1, result);
            for (String b : map2.keySet()) {
                enum2Nums(nums[idx], map2.get(b), String.valueOf(nums[idx]), b, 
                        map, idx == 0, result);
            }
            // (nums[i] # nums[i + 1]) # (nums[i + 2]...nums[n - 1])
            Map<String, Double> map3 = new HashMap<>();
            enum2Nums(nums[idx], nums[idx + 1], String.valueOf(nums[idx]), String.valueOf(nums[idx + 1]), map3, false, result);
            map2.clear();
            map2 = buildResult(nums, idx + 2, result);
            for (String a : map3.keySet()) {
                for (String b : map2.keySet()) {
                    enum2Nums(map3.get(a), map2.get(b), a, b, map, idx == 0, result);
                }
            }
        }
        return map;
    }
    private void enum2Nums(double num1, double num2, String num1Str, String num2Str,
            Map<String, Double> map, boolean isLastStep, Set<String> result) {
        for (String op : operators) {
            double ans = eval2Nums(num1, num2, op);
            if (!isLastStep) {
                map.put("(" + num1Str + op + num2Str + ")", ans);
            } else if (Math.abs(ans - 24) < 1e-6) {
                result.add(num1Str + op + num2Str);
            }
            if (num1 == num2)
                continue;
            ans = eval2Nums(num2, num1, op);
            if (!isLastStep) {
                map.put("(" + num2Str + op + num1Str + ")", ans);
            } else if (Math.abs(ans - 24) < 1e-6) {
                result.add(num2Str + op + num1Str);
            }
        }
    }
    private double eval2Nums(double num1, double num2, String op) {
        switch (op) {
        case "+":
            return num1 + num2;
        case "-":
            return num1 - num2;
        case "*":
            return num1 * num2;
        case "/":
            if (Math.abs(num2) < 1e-9)
                return Double.MAX_VALUE;
            else
                return num1 / num2;
        default:
            return Double.MAX_VALUE;
        }
    }
    
    private static double epsilon = 0.000001;
    private static Set<String> operators = new HashSet<>();
    static {
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
    }
}
