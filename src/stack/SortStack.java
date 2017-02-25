package stack;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

/** Interface Deque and its implementation (ArrayDeque, LinkedList) is more preferable than Stack
 * */
public class SortStack {

	public static void main(String[] args) {
		SortStack obj = new SortStack();
//		Stack<Integer> stack = new Stack<>();
//		Random rand = new Random();
//		for (int i = 0; i < 10; i++) {
//			stack.push(rand.nextInt(30));
//		}
//		System.out.println("bef sort:");
//		print(stack);
//		stack = obj.sort(stack);
//		System.out.println("after sort:");
//		print(stack);
		
		// test mergeSort()
		LinkedList<Integer> stack1 = new LinkedList<>();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			stack1.push(rand.nextInt(30));
		}
		print(stack1);
		obj.sort(stack1);
		print(stack1);
	}
	
	public static void print(Stack<Integer> stack) {
		Stack<Integer> buf = new Stack<>();
		while (!stack.isEmpty()) {
			System.out.print(stack.peek() + ",");
			buf.push(stack.pop());
		}
		System.out.println();
		while (!buf.isEmpty()) {
			stack.push(buf.pop());
		}
	}
	
	public static void print(LinkedList<Integer> stack) {
		Stack<Integer> buf = new Stack<>();
		while (!stack.isEmpty()) {
			System.out.print(stack.peek() + ",");
			buf.push(stack.pop());
		}
		System.out.println();
		while (!buf.isEmpty()) {
			stack.push(buf.pop());
		}
	}
	
	// Method 1: linlin's idea, selection sort, use 2 extra stacks
	public Stack<Integer> sort(Stack<Integer> stack) {
		if (stack == null || stack.size() <= 1) {
			return stack;
		}
		Stack<Integer> stack2 = new Stack<>();
		Stack<Integer> stack3 = new Stack<>();
		int n = stack.size();
		while (stack2.size() < n) {
			stack2.push(stack.pop());
			while (!stack.isEmpty()) {
				int curr = stack.pop();
				if (curr > stack2.peek()) {
					stack3.push(curr);
				} else {
					stack3.push(stack2.pop());
					stack2.push(curr);
				}
			}
			Stack<Integer> tmp = stack;
			stack = stack3;
			stack3 = tmp;
		}
		return stack2;
	}
	
	// Method 2: better idea, merge sort
	public void sort(LinkedList<Integer> stack1) {
		if (stack1 == null) {
			return;
		}
		LinkedList<Integer> stack2 = new LinkedList<>();
		LinkedList<Integer> stack3 = new LinkedList<>();
		mergeSort(stack1, stack2, stack3, stack1.size());
	}
	public void mergeSort(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3, int length) {
		if (length <= 1) {
			return;
		}
		int halfLen1 = length / 2;
		int halfLen2 = length - length / 2;
		// move half size of s1 to s2
		for (int i = 0; i < halfLen1; i++) {
			s2.push(s1.pop()); // s2.offerFirst(s1.pollFirst())
		}
		// recursively call mergeSort to sort s1 and s2
		mergeSort(s1, s3, s2, halfLen2);
		mergeSort(s2, s3, s1, halfLen1);
		// merge sorted s1 and s2, store the result in s3
		int i = 0, j = 0;
		while (i < halfLen2 && j < halfLen1) {
			if (s1.peek() < s2.peek()) {
				s3.push(s1.pop());
				i++;
			} else {
				s3.push(s2.pop());
				j++;
			}
		}
		while (i < halfLen2) {
			s3.push(s1.pop());
			i++;
		}
		while (j < halfLen1) {
			s3.push(s2.pop());
			j++;
		}
		// after merging, elements are in descending order from top to botton in s3,
		// pop elements back into s1 from s3, so that they are in ascending order from top to bottom
		for (i = 0; i < length; i++) {
			s1.push(s3.pop());
		}
	}
	
	// use 1 extra stack
}
