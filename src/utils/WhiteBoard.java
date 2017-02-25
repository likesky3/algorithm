package utils;

import java.util.*;
import recursion.MergeSort;

public class WhiteBoard {
	public static void main(String[] args) {
		WhiteBoard obj = new WhiteBoard();
		MergeSort sort = new MergeSort();
		int[] array = { 5, 3, 4, 2, 1, 1, 2, 1, 8, 4, 4, 9, 13, 5, 8 };
		print(array);
		int[][] matrix = { { 1, 2 }, { 2, 3 } };
	}

	public static void print(int[] array) {
		for (int n : array) {
			System.out.print(n + ",");
		}
		System.out.println();
	}

	
}
