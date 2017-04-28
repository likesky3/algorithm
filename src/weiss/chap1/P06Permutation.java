package weiss.chap1;

import java.util.Arrays;

public class P06Permutation {
	public void permute(String str) {
		permute(str.toCharArray(), 0, str.length() - 1);
	}
	
	private void permute(char[] s, int from, int to) {
		if (from > to) {
			System.out.println(Arrays.toString(s));
			return;
		}
		
		for (int i = from; i <= to; i++) {
			swap(s, from, i);
			permute(s, from + 1, to);
			swap(s, from, i);
		}
	}
	
	private void swap(char[] s, int i, int j) {
		char tmp = s[i];
		s[i] = s[j];
		s[j] = tmp;
	}
	
	public static void main(String[] args) {
		P06Permutation obj = new P06Permutation();
		obj.permute("abcd");
	}
} 
