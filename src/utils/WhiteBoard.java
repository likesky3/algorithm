package utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class WhiteBoard {
	public static void main(String[] args) throws Exception {
		WhiteBoard obj = new WhiteBoard();
		int[] coins = {5, 2, 1};
		List<List<Integer>> result = obj.combinations(11, coins);
		for (List<Integer> item : result) {
			for (int elem : item) {
				System.out.print(elem + " ");
			}
			System.out.println();
		}
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

	public List<List<Integer>> combinations(int target, int[] coins) {
		List<List<Integer>> result = new ArrayList<>();
		recur(target, coins, 0, new ArrayList<>(), result);
		return result;
	}

	private void recur(int target, int[] coins, int p, List<Integer> item, List<List<Integer>> result) {
		if (target == 0) {
			List<Integer> copy = new ArrayList<>(item);
			for (int i = copy.size(); i < coins.length; i++) {
				copy.add(0);
			}
			result.add(copy);
			return;
		} else if (p == coins.length) {
			return;
		}
		
		int k = target / coins[p];
		for (int j = 0; j <= k; j++) {
			item.add(j);
			recur(target - j * coins[p], coins, p + 1, item, result);
			item.remove(item.size() - 1);
		}
	}
}
