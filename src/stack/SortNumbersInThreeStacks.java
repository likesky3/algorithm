package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import utils.WhiteBoard;

/** Interface Deque and its implementation (ArrayDeque, LinkedList) is more preferable than Stack
 * */
public class SortNumbersInThreeStacks {

	public static void main(String[] args) {
		SortNumbersInThreeStacks obj = new SortNumbersInThreeStacks();
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
	public int[] sort(Deque<Integer> nums) { 
		Deque<Integer> s1 = new ArrayDeque<>();
		Deque<Integer> s2 = new ArrayDeque<>();
		mergeSort(nums, nums.size(), s1, s2);
		int[] res = new int[nums.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = nums.pollFirst();
		}
		return res;
	}
	
	private void mergeSort(Deque<Integer> s1, int len1, Deque<Integer> s2,  Deque<Integer> s3) {
		if (len1 == 1) {
			return;
		}
		// pop half size of elements from s1 to s2
		for (int i = 0; i < len1 / 2; i++) {
			s2.offerFirst(s1.pollFirst());
		}
		// recursively sort s1, s2
		mergeSort(s1, len1 - len1 / 2, s2, s3);
		mergeSort(s2, len1 / 2, s1, s3);
		// merge sorted s1, s2 into s3
		merge(s1, len1 - len1 / 2, s2, len1 / 2, s3);
		// move elements back to s1
		while (!s3.isEmpty()) {
			s1.offerFirst(s3.pollFirst());
		}
	}
	
	private void merge(Deque<Integer> s1, int len1, Deque<Integer> s2, int len2, Deque<Integer> s3) {
		while (len1 > 0 || len2 > 0) {
			if (len2 == 0 || len1 > 0 && s1.peekFirst() < s2.peekFirst()) {
				s3.offerFirst(s1.pollFirst());
				len1--;
			} else {
				s3.offerFirst(s2.pollFirst());
				len2--;
			}
		}
	}	
	
	// use 1 extra stack
}
