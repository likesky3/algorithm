package stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * how many possible solutions Given 4 numbers, print all expressions getting
 * result 24 using +, -, *, /, (, ). For example, 8 8 3 3 => (8 / ( 3 - 8/3)) =
 * 24, 2 2 6 6 => ((2+6)*6/2) = 24, ((2+6)/(2/6)) = 24, ... 
 * hint1: calculate how large the possible solution can be? 
 * hint2: one way to solve is to try reverse
 * polish notation. 
 * 
 * my idea: enum all the possible patterns, verify each one, if
 * correct, translate to expressions and add to the result.
 * 
 * hard points: 
 * how to enum all this patterns 
 * how to calculate 8383/-/ to 24
 * deduplicate: where it comes from?!!!!!!!!!!!!!! 
 * each operator can be used as any times
 * 
 * questions to interviewers: is ((2*6)+(6*2)) and ((6*2)+(6*2)) same ?
 */
// Method 1
public class Get24 {
	public static void main(String[] args) {
		Get24 obj = new Get24();
		obj.get24(new int[] { 2, 2, 6, 6 });
		obj.get24(new int[] { 3, 8, 8, 3 });
	}

	private double epsilon = 0.000001;
	private static Set<String> ops = new HashSet<>();
	static {
		ops.add("+");
		ops.add("-");
		ops.add("*");
		ops.add("/");
	}
	
	public List<String> get24(int[] nums) {
		List<String> cands = new ArrayList<>();
		for (int num : nums)
			cands.add(String.valueOf(num));
		for (int i = 0; i < 3; i++)
			cands.add("#");
		boolean[] visited = new boolean[cands.size()];
		String[] item = new String[cands.size()];
		Set<String> validRPNs = new HashSet<>();
		enumRPNs(validRPNs, cands, visited, item, 0);
		
		List<String> result = new ArrayList<>();
		result.addAll(validRPNs);
		for (String s : result) {
			System.out.println(s);
		}
		return result;
	}

	private void enumRPNs(Set<String> validRPNs, List<String> cands, boolean[] visited,
			String[] item, int p) {
		if (p == item.length) {
			eval(item, validRPNs);
			return;
		}
		for (int i = 0; i < cands.size(); i++) {
			if (visited[i]) continue;
			if (cands.get(i).equals("#")) {
				for (String op : ops) {
					item[p] = op;
//					cands.remove(i);
					visited[i] = true;
					enumRPNs(validRPNs, cands, visited, item, p + 1);
					visited[i] = false;
//					cands.add(i, "#");
				}
			} else {
				item[p] = cands.get(i);
				visited[i] = true;
				enumRPNs(validRPNs, cands, visited, item, p + 1);
				visited[i] = false;
			}
		}
	}

	// eval RPN, if 24, translate to inorder expression
	private void eval(String[] rpn, Set<String> validRPNs) {
		StringBuilder sb = new StringBuilder();
		for (String s : rpn) {
			sb.append(s);
		}
		if (!validRPNs.contains(sb.toString())) { // deduplicate
			double ans = evalRPN(rpn);
			if (Math.abs(ans - 24) < epsilon) {
				validRPNs.add(translate(rpn));
			}
		}
	}

	private double evalRPN(String[] rpn) {
		Deque<Double> stack = new ArrayDeque<>();
		for (String s : rpn) {
			switch (s) {
			case "+":
				if (stack.size() < 2)
					return 0;
				stack.push(stack.pop() + stack.pop());
				break;
			case "-":
				if (stack.size() < 2)
					return 0;
				stack.push(-stack.pop() + stack.pop());
				break;
			case "*":
				if (stack.size() < 2)
					return 0;
				stack.push(stack.pop() * stack.pop());
				break;
			case "/":
				if (stack.size() < 2)
					return 0;
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

/**
 * Suggestions from cyb: There are two ways to enumRPNs, one is first select
 * operators, then permute; the other is first permute, then select operators.
 * Either is ok, here uses the second one. In L56-L61, two loops are used to add
 * first two numbers, that helps performance but not necessary. As evalRPN
 * excludes invalid RPNs. 
 * In places like L64-65, you use cands.remove()/add() to
 * remove used items. Notice that remove/add for ArrayList is O(n). So a better
 * way is to use an bool[] used array to record used situation.
 * You first enumerates all RPNs, then eval all RPNs. It might take much memory. So one
 * improvement can be eval immediately after enumerating one RPN. Notice div 0
 * when eval / operation.
 */
