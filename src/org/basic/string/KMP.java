package org.basic.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class KMP {

	public static void main(String[] args) {
		KMP obj = new KMP();
//		for(int i : obj.preprocessPattern("abac".toCharArray()))
//			System.out.println(i);
//		obj.match("iiiiiabcdef".toCharArray(), "abcd".toCharArr
		Deque<Integer> stack = new ArrayDeque<Integer>();
		stack.pop();
	}
	
	public int[] preprocessPattern(char[] B) {
		int m = B.length;
		int[] p = new int[m + 1]; //p[j] 指示当前A[i]应该和B中那个位置开始比较，从0开始
		int j = 0;// 
		for (int i = 1; i < m; i++) {
			while (j > 0 && B[j] != B[i]) j = p[j];
			if (B[j] == B[i]) j++;
			p[i] = j;
		}
		return p;
	}
	// 扫描字符串A，并更新可以匹配到B的什么位置, 这通过更加 j 实现
	public void match(char[] A, char[] B) {
		int[] p = preprocessPattern(B);
		int n = A.length, m = B.length;
		int j = 0;
		for (int i = 0; i < n; i++) {
			while (j > 0 && B[j] != A[i]) j = p[j];
			if (B[j] == A[i]) j++;
			if (j == m) {
				String tmp = new String(A, i - m + 1, m);
				System.out.println("pattern find @ " + (i - m + 1) + " " + tmp);
				j = p[j];
			}
		}
	}

}
