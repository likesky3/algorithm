package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

Fix bug @ Get24_2_2
*/
public class Get24_2_3 {

    public static void main(String[] args) {
        Get24_2_3 obj = new Get24_2_3();
//        obj.get24(new int[]{2, 2, 6, 6});
        obj.get24(new int[]{1,2,3,4,5});
//        obj.get24(new int[]{3, 8, 8, 3, 10, 7});
    }
    
    public List<String> get24(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<Double> doubleNums = new ArrayList<>(n); 
        List<String> expressions = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            doubleNums.add((double)nums[i]);
            expressions.add(String.valueOf(nums[i]));
        }
        Set<String> resultSet = new HashSet<>();
        buildResult(doubleNums, expressions, resultSet);
        List<String> result = new ArrayList<>();
        result.addAll(resultSet);
//        for (String s : result) {
//            System.out.println(s);
//        }
//        System.out.println();
        System.out.println(result.size());
        return result;
    }
    
    private void buildResult(List<Double> nums, List<String> expressions, Set<String> result) {
        if (nums.size() == 1) {
            if (Math.abs(nums.get(0) - 24) < 1e-6)
                result.add(expressions.get(0));
        } else {
            int n = nums.size();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (String op :operators) {
                        double num1 = nums.get(i), num2 = nums.get(j);
                        String num1str = expressions.get(i), num2str = expressions.get(j);
                        double ans = eval2Nums(num1, num2, op);
                        nums.remove(i);
                        nums.remove(j - 1); // attention here
                        expressions.remove(i);
                        expressions.remove(j - 1); // attention here
                        nums.add(ans);
                        expressions.add("(" + num1str + op + num2str + ")");
                        buildResult(nums, expressions, result);
                        expressions.remove(expressions.size() - 1);
                        nums.remove(nums.size() - 1);
                        if (!num1str.equals(num2str)) {
                            ans = eval2Nums(num2, num1, op);
                            nums.add(ans);
                            expressions.add("(" + num2str + op + num1str + ")");
                            buildResult(nums, expressions, result);
                            expressions.remove(expressions.size() - 1);
                            nums.remove(nums.size() - 1);
                        }
                        nums.add(i, num1);
                        nums.add(j, num2);
                        expressions.add(i, num1str);
                        expressions.add(j, num2str);
                    }
                }
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
    
    private static Set<String> operators = new HashSet<>();
    static {
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
    }
}
