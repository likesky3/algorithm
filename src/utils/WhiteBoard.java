package utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class WhiteBoard {
	public static void main(String[] args) throws Exception {
		WhiteBoard obj = new WhiteBoard();
		int[][] people = { { 7, 0 }, { 4, 4 }, { 7, 1 }, { 5, 0 }, { 6, 1 }, { 5, 2 } };
		int[] nums = { 5, 3, 4, 2 };
		print(nums);
		String[] expr = {"(", "a", "+", "b", ")", "*", "c", "-", "d", "/", "e"};
		
	}

	static class A {
		private int x;

		public A(int x) {
			this.x = x;
		}

		public void print(A a) {
			System.out.println(a.x); // Visibility ok!!!
		}

	}

	public static void print(int[] array) {
		for (int n : array) {
			System.out.print(n + ",");
		}
		System.out.println();
	}

	public static void print(char[] array) {
		for (char n : array) {
			System.out.print(n + ",");
		}
		System.out.println();
	}

	
}