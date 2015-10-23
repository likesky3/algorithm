package org.basic.datastructure;

public class FibonacciGenerator {
	public static void main(String[] args) {
		FibonacciGenerator obj = new FibonacciGenerator(2, 3);
		for (int i = 0; i < 10; i++)
			System.out.println(obj.next());
	}

	public FibonacciGenerator(int j, int k) {
		firstArray = new int[k];
		for (int i = 0; i < k; i++)
			firstArray[i] = i;
		first = 0;
		int offset = k - j;
		secondArray = new int[j];
		for (int i = 0; i < j; i++) {
			secondArray[i] = firstArray[i + offset];
		}
		second = 0;
		idx = 0;
		this.j = j;
		this.k = k;
	}

	public int next() {
		if (idx < k) return firstArray[idx++];
		
		int result = firstArray[first] + secondArray[second];
		firstArray[first] = result;
		first = (first + 1) % k;
		secondArray[second] = result;
		second = (second + 1) % j;
		return result;
	}

	private int[] firstArray; // size k
	private int[] secondArray; // size j
	private int first, second;
	private int j, k;
	private int idx;
}
