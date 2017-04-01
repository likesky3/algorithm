package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Key idea: to build up a look up table t[] that records the matching results of prefix and postfix in pattern string.
 * t[i] means p[0, t[i]] is the longest prefix which is equal to the postfix p[i - t[i], i];
 * Value in the table means the max length substring that exists in both prefix and postfix.
 * In the prefix this substring should start at index 0, in the postfix this substring should end at current index.
 * 
 * s: yyyyyxxxxxxx|i|.....
 * 		p: xxxxxxj|j+1|...
 *            
 * currently, we have matched p[0,j] with s, we are comparing s[i] with p[j + 1],
 * if s[i] == p[j + 1], then update i, j to next position;
 * else, if somehow we know that the first k letters of p are equal to the latter k letters of p[0, j],
 * then we can compare the s[i] with p[k + 1]. The matching table will tell us such information.
 * 
 * How can we get such a matching table?
 * Similar to finding matching of p in s, but this time we match p to iteself.
 * For each position i in p,we want to know the maximal matching prefix and postfix, prefix starts at 0, postfix ends at i.
 * Suppose currently, we are matching p[i] with p[j + 1], Similar to the analysis of matching s and p,
 * if p[i] == p[j + 1], then update i, j, which is i++, j++;
 * else, j jump to t[j] and continue to match p[i] with p[(j = t[j]) + 1].
 * Keep on this compare and jump process until either j == -1 or p[i] == p[j + 1].
 * */
public class KMP2 {
	public static void main(String[] args) {
		String p = "";
		for (int i = 0; i < 10; i++) {
			p += (char)('a' + (int)(Math.random() * 26));
		}
//		String p = "abbacdab";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			int insertNum = (int)Math.random() * 30 + 1;
			for (int j = 0; j < insertNum; j++) {
				sb.append((char)('a' + (int)(Math.random() * 26)));
			}
			sb.append(p);
		}
		KMP2 obj = new KMP2();
		String s = sb.toString();
		s = "aaaa";
		p = "aa";
		System.out.println("s=" + s);
		System.out.println("p=" + p);
		int[] t = obj.preprocess(p.toCharArray());
		List<Integer> matchPos = obj.getMatchIndexesUsingKMP(s, p);
		List<Integer> matchPos2 = obj.getMatchIndexesUsingJavaAPI(s, p);
		System.out.println(matchPos.size() + ", " + matchPos2.size());
//		for (int i = 0; i < matchPos.size(); i++) {
//			if (matchPos.get(i) != matchPos2.get(i)) {
//				System.out.printf("KMP fail, %d (kmp) vs %d\n", matchPos.get(i), matchPos2.get(i));
//			}
//		}
//		System.out.println("KMP succ");
		for (int i : matchPos) { 
			System.out.print(i + ":");
			System.out.print(s.substring(i, i + p.length()) + "\n");
		}
	}
	
	public List<Integer> getMatchIndexesUsingJavaAPI(String s, String p) {
		List<Integer> result = new ArrayList<>();
		int i = 0;
		while (i + p.length() <= s.length()) {
			i = s.indexOf(p, i);
			result.add(i);
			i++;
		}
		
		return result;
	}
	
	public List<Integer> getMatchIndexesUsingKMP(String s, String p) {
		List<Integer> result = new ArrayList<>();
		int[] t = preprocess(p.toCharArray());
		int j = -1;
		for (int i = 0; i < s.length(); i++) {
			while (j != -1 && s.charAt(i) != p.charAt(j + 1)) {
				j = t[j];
			}
			if (s.charAt(i) == p.charAt(j + 1)) {
				j++;
			}
			// check if whether the whole p has been matched
			if (j == p.length() - 1) {
				result.add(i - p.length() + 1);
				// if allow overlap
				j = t[j];
//				 if no overlap
//				 j = -1;
			}
		}
		return result;
	}
	
	private int[] preprocess(char[] p) {
		int[] t = new int[p.length];
		t[0] = -1; // very important!!!
		int j = -1;
		for (int i = 1; i < p.length; i++) {
			while (j != -1 && p[i] != p[j + 1]) {
				j = t[j];
			}
			if (p[i] == p[j + 1]) {
				j++;
			}
			t[i] = j;
		}
		return t;
	}

}
