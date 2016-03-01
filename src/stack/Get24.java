package stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/** how many possible solutions
 * AB_C_D_
 * AB_CD__
 * ABC_D__
 * ABC__D_
 * ABCD___
 * 
 * my idea: enum all the possible patterns, verify each one, 
 * if correct, translate to expressions and add to the result.
 * 
 * hard points:
 * how to enum all this patterns
 * how to calculate 8383/-/ to 24
 * 
*/
// Not done yet. operators can be duplicate
public class Get24 {
	public static void main(String[] args) {
		Get24 obj = new Get24();
		obj.get24(new int[]{8, 3, 8, 3});
	}
	private static String[] operators = {"+", "-", "*", "/"};
	public List<String> get24(int[] nums) {
		Arrays.sort(nums);
		List<String> result = new ArrayList<>();
		// enum RPN expressions
		List<String[]> rpns = new ArrayList<>();
		enumRPNs(nums, rpns, 7);
//		print(rpns);
		// valid and eval RPN, if 24, translate to normal expression
		eval(rpns, result);
		return result;
	}
	private void enumRPNs(int[] nums, List<String[]> rpns, int n) {
		List<String> cands = new ArrayList<>();
		for (int num : nums) {
			cands.add(String.valueOf(num));
		}
		for (String op : operators)
			cands.add(op);
		
		String[] item = new String[n];
		for (int i = 0; i < nums.length; i++) {
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
			item[p] = cands.get(i);
			cands.remove(i);
			recur(rpns, cands, item, p + 1);
			cands.add(i, item[p]);
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
		for (String[] rpn : rpns) {
			double ans = evalRPN(rpn);
			if (Math.abs(ans - 24) < epsilon) {
				result.add(translate(rpn));
				
			}
			for (String s : rpn) {
				System.out.print(s + " ");
			}
			System.out.println(" = " + ans);
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
	private String translate(String[] rpn) {
		return null;
	}
}
