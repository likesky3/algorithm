package utils;

import java.util.*;

import hash.MyHashMap;

public class WhiteBoard {
	public static void main(String[] args) throws Exception {
		WhiteBoard obj = new WhiteBoard();
		Solution test = new Solution();
		// ListNode head = ListNode.buildAList(new int[]{4, 1, 3, 5, 2});
		System.out.println(Runtime.getRuntime().maxMemory());
		System.out.println(Runtime.getRuntime().totalMemory());
	}

	private static final int MAXIMUM_CAPACITY = 1 << 30;

	public static final int tableSizeFor(int cap) {
		int n = cap - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}

	public static final int[] test() {
		return new int[] { 1, 2 };
	}

	public static void test(String a, int b) {

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

	public static <T> void print(Iterable<T> list) {
		int i = 0;
		for (T n : list) {
			i++;
			System.out.print(n + " ");
			if (i == 20) {
				System.out.println();
				i = 0;
			}
		}
		System.out.println();
	}

	public static void printDouble(Iterable<Double> list) {
		for (Double n : list) {
			System.out.print(n + " ");
		}
		System.out.println();
	}
}

class Solution {
	  
}
