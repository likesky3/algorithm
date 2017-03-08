package string;

import java.util.ArrayList;
import java.util.List;

public class StringReplaceInPlace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String replace(String input, String s, String t) {
		// BUG1: t.length() == 0 should not put here, make this error 2 times
		if (input.length() == 0 || input.length() < s.length()) {
			return input;
		}
		if (s.length() < t.length()) {
			return replaceLonger(input, s, t);
		} else {
			return replaceShorter(input, s, t);
		}
	}

	// update from right to left
	private String replaceLonger(String input, String s, String t) {
		List<Integer> matches = getMatchPos(input, s);
		if (matches.isEmpty()) { // no s in input
			return input;
		}
		int extraLen = matches.size() * (t.length() - s.length());
		char[] array = new char[input.length() + extraLen];
		copyArray(array, 0, input);
		int slow = input.length() - 1;
		int fast = array.length - 1; //BUG2: fast = array.length
		int p = matches.size() - 1;
		int tLen = t.length();
		while (slow >= 0) {
			if (p < 0 || slow != matches.get(p)) {
				array[fast--] = array[slow--];
			} else {
				for (int i = tLen - 1; i >= 0; i--) {
					array[fast--] = t.charAt(i);
				}
				p--;
				slow -= s.length();
			}
		}
		return new String(array);
	}

	private List<Integer> getMatchPos(String input, String s) {
		List<Integer> matches = new ArrayList<>();
		int i = 0;
		while (i + s.length() <= input.length()) {
			if (equals(input, i, s)) {
				matches.add(i + s.length() - 1); // for convience in later
				i += s.length();
			} else {
				i++;
			}
		}
		return matches;
	}

	private void copyArray(char[] array, int start, String t) {
		for (int i = 0; i < t.length(); i++) {
			array[start + i] = t.charAt(i);
		}
	}

	private boolean equals(String input, int start, String s) {
		for (int i = 0; i < s.length(); i++) {
			if (input.charAt(start + i) != s.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	// update from left to right
	private String replaceShorter(String input, String s, String t) {
		char[] array = input.toCharArray();
		int slow = 0; // scan the origin input
		int fast = 0; // letters before fast should be returned
		while (slow < array.length) {
			if (slow + s.length() <= input.length() && equals(input, slow, s)) {
				copyArray(array, fast, t); // BUG 3, fast, not slow, remember what they represent
				slow += s.length();
				fast += t.length();
			} else {
				array[fast++] = array[slow++];
			}
		}
		return new String(array, 0, fast);
	}

}
