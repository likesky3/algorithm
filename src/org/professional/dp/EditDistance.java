package org.professional.dp;

public class EditDistance {
	public int getLeastEditDistance(String s, String t, int deletionCost, int insertionCost, int substutitionCost) {
		int min = Integer.MAX_VALUE;
		int lengthT = t.length();
		for (int i = 0; i <= lengthT; i++) {
			min = Math.min(min, getEditDistance(s, t.substring(i), deletionCost, insertionCost, substutitionCost));
		}
		return min;
	}
	public int getLeastEditDistance2(String s, String t, int deletionCost, int insertionCost, int substutitionCost) {
		if (s == null)
			s = "";
		if (t== null)
			t = "";
		int lengthS = s.length();
		int lengthT = t.length();
		int[][] dp = new int[s.length() + 1][t.length() + 1];
		for (int i = 0; i <= lengthT; i++) {
			dp[0][i] = 0; // match begin from t[i], not care about t[0, i - 1]
		}
		for (int i = 1; i <= lengthS; i++) {
			dp[i][0] = checkBounds(dp[i - 1][0] + deletionCost);
			for (int j = 1; j <= lengthT; j++) {
				int currS = i - 1;
				int currT = j - 1;
				if (s.charAt(currS) == t.charAt(currT)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					int minCost = Math.min(Integer.MAX_VALUE, checkBounds(dp[i - 1][j - 1] + substutitionCost));
					minCost = Math.min(minCost, checkBounds(dp[i - 1][j] + deletionCost));
					minCost = Math.min(minCost, checkBounds(dp[i][j - 1] + insertionCost));
					dp[i][j] = minCost;
				}
			}
		}
		int minCost = Integer.MAX_VALUE;
		for (int i = 0; i <= lengthT; i++) {
			minCost = Math.min(minCost, dp[lengthS][i]);
		}
		return minCost;
	}
	
	public int getEditDistance(String s, String t, int deletionCost, int insertionCost, int substutitionCost) {
		if (s == null)
			s = "";
		if (t== null)
			t = "";
		int lengthS = s.length();
		int lengthT = t.length();
		int[][] dp = new int[s.length() + 1][t.length() + 1];
		dp[0][0] = 0;
		for (int i = 1; i <= lengthT; i++) {
			dp[0][i] = checkBounds(dp[0][i - 1] + insertionCost);
		}
		for (int i = 1; i <= lengthS; i++) {
			dp[i][0] = checkBounds(dp[i - 1][0] + deletionCost);
			for (int j = 1; j <= lengthT; j++) {
				int currS = i - 1;
				int currT = j - 1;
				if (s.charAt(currS) == t.charAt(currT)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					int minCost = Math.min(Integer.MAX_VALUE, checkBounds(dp[i - 1][j - 1] + substutitionCost));
					minCost = Math.min(minCost, checkBounds(dp[i - 1][j] + deletionCost));
					minCost = Math.min(minCost, checkBounds(dp[i][j - 1] + insertionCost));
					dp[i][j] = minCost;
				}
			}
		}
		int minCost = Integer.MAX_VALUE;
		for (int i = 0; i <= lengthT; i++) {
			minCost = Math.min(minCost, dp[lengthS][i]);
		}
		return minCost;
	}
	
	private int checkBounds(int n) {
		return n < 0 ? Integer.MAX_VALUE : n;
	}
	
	public static void main(String[] args) {
		EditDistance obj = new EditDistance();
		obj.getEditDistance("abc", "edf", 1, 1, 1);
	}
}
