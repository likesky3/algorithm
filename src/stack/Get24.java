package stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** how many possible solutions
 * Given 4 numbers, print all expressions getting result 24 using +, -, *, /, (, ).
For example, 8 8 3 3 => (8 / ( 3 - 8/3)) = 24,
2 2 6 6 => ((2+6)*6/2) = 24, ((2+6)/(2/6)) = 24, ...
hint1: calculate how large the possible solution can be?
hint2: one way to solve is to try reverse polish notation.
 * my idea: enum all the possible patterns, verify each one, 
 * if correct, translate to expressions and add to the result.
 * 
 * hard points:
 * how to enum all this patterns
 * how to calculate 8383/-/ to 24
 * deduplicate
 * each operator can be used as any times 
*/
// Method 1
public class Get24 {
    public static void main(String[] args) {
        Get24 obj = new Get24();
        obj.get24(new int[]{2, 2, 6, 6});
    }
    
    public List<String> get24(int[] nums) {
        Arrays.sort(nums);
        
        // enum RPN expressions
        List<String[]> rpns = new ArrayList<>();
        enumRPNs(nums, rpns, 7);
        
        // valid and eval RPN, if 24, translate to inorder expression
        List<String> result = new ArrayList<>();
        eval(rpns, result);
        
        for (String s : result) {
            System.out.println(s);
        }
        return result;
    }
    private void enumRPNs(int[] nums, List<String[]> rpns, int n) {
        List<String> cands = new ArrayList<>();
        for (int num : nums) {
            cands.add(String.valueOf(num));
        }
        for (int i = 0; i < 3; i++)
            cands.add("#");
        String[] item = new String[n];
        for (int i = 0; i < nums.length; i++) {
            if (i >  0 && nums[i] == nums[i - 1]) // prun duplicates
                continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) // prun duplicates
                    continue;
                item[0] = String.valueOf(nums[i]);
                item[1] = String.valueOf(nums[j]);
                cands.remove(item[0]);
                cands.remove(item[1]);
                recur(rpns, cands, item, 2);
                if (item[0] != item[1]) {
                    String tmp = item[0];
                    item[0] = item[1];
                    item[1] = tmp;
                    recur(rpns, cands, item, 2);
                }
                cands.add(item[0]);
                cands.add(item[1]);
            }
        }
    }
    
    private void recur(List<String[]> rpns, List<String> cands, String[] item, int p) {
        if (p == item.length) {
            rpns.add(item.clone());
            return;
        }
        for (int i = 0; i < cands.size(); i++) {
            if (cands.get(i).equals("#")) {
                for (String op : ops) {
                    item[p] = op;
                    cands.remove(i);
                    recur(rpns, cands, item, p + 1);
                    cands.add(i, "#");
                }
            } else {
                item[p] = cands.get(i);
                cands.remove(i);
                recur(rpns, cands, item, p + 1);
                cands.add(i, item[p]);
            }
        }
    }
    private void print(List<String[]> rpns) {
        System.out.println("total=" + rpns.size());
        for (String[] item : rpns) {
            for (String s : item) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
    private double epsilon = 0.000001;
    private void eval(List<String[]> rpns, List<String> result) {
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (String[] rpn : rpns) {
            sb.delete(0, sb.length());
            for (String s : rpn) {
                sb.append(s);
            }
            if (set.add(sb.toString())) { // deduplicate
	            double ans = evalRPN(rpn);
	            if (Math.abs(ans - 24) < epsilon) {
	                result.add(translate(rpn));
	            }
            }
        }
    }
    private double evalRPN(String[] rpn) {
        Deque<Double> stack = new ArrayDeque<>();
        for (String s : rpn) {
            switch (s) {
            case "+":
                if (stack.size() < 2) return 0;
                stack.push(stack.pop() + stack.pop());
                break;
            case "-":
                if (stack.size() < 2) return 0;
                stack.push(-stack.pop() + stack.pop());
                break;
            case "*":
                if (stack.size() < 2) return 0;
                stack.push(stack.pop() * stack.pop());
                break;
            case "/":
                if (stack.size() < 2) return 0;
                double num2 = stack.pop();
                double num1 = stack.pop();
                stack.push(num1 / num2);
                break;
            default:
                stack.push(Double.valueOf(s));
            }
        }
        if (stack.size() == 1)
            return stack.pop();
        else
            return 0;
    }
    private static Set<String> ops = new HashSet<>();
    static {
        ops.add("+");
        ops.add("-");
        ops.add("*");
        ops.add("/");
    }
    private String translate(String[] rpn) {
        Deque<String> stack = new ArrayDeque<>();
        for (String s : rpn) {
            if (ops.contains(s)) {
                String num2 = stack.pop();
                String num1 = stack.pop();
                stack.push("(" + num1 + s + num2 + ")");
            } else {
                stack.push(s);
            }
        }
        return stack.pop();
    }
}
