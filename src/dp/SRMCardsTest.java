package dp;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

public class SRMCardsTest {
	@Test
	public void testMaxTurns() {
		SRMCards obj = new SRMCards();
		int[] cards1 = {1, 2};
		Assert.assertEquals(1, obj.maxTurns(cards1));
		Assert.assertEquals(1, obj.maxTurns2(cards1));
		int[] cards2 = {491, 492, 495, 497, 498, 499};
		Assert.assertEquals(4, obj.maxTurns(cards2));
		Assert.assertEquals(4, obj.maxTurns2(cards2));
		int[] cards3 = {100, 200, 300, 400};
		Assert.assertEquals(4, obj.maxTurns(cards3));
		Assert.assertEquals(4, obj.maxTurns2(cards3));
		int[] cards4 = {11, 12, 102, 13, 100, 101, 99, 9, 8, 1};
		Assert.assertEquals(6, obj.maxTurns(cards4));
		Assert.assertEquals(6, obj.maxTurns2(cards4));
		int[] cards5 = {118, 321, 322, 119, 120, 320};
		Assert.assertEquals(4, obj.maxTurns(cards5));
		Assert.assertEquals(4, obj.maxTurns2(cards5));
		int[] cards6 = {10, 11, 12, 13, 14, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		Assert.assertEquals(7, obj.maxTurns(cards6));
		Assert.assertEquals(7, obj.maxTurns2(cards6));
	}
}

class SRMCards {
	public int maxTurns(int[] cards) {
		if (cards == null)
			return 0;
		Arrays.sort(cards);
		int[] dp = new int[cards.length];
		boolean[] manualRemoval = new boolean[cards.length];
		dp[0] = 1;
		manualRemoval[0] = true;
		for (int i = 1; i < cards.length; i++) {
			if ((cards[i] == cards[i - 1] + 1) && manualRemoval[i - 1]) { 
				dp[i] = dp[i - 1];
				manualRemoval[i] = false;
			} else {
				dp[i] = dp[i - 1] + 1;
				manualRemoval[i] = true;
			}
		}
		return dp[cards.length - 1];
	}
	
	// cards[i]  ????????????cards[i - 1]????????????????????????dp???????????????i - 1???cards[i]?????????????????????????????????i - 2??????
	public int maxTurns3(int[] cards) {
		if (cards == null)
			return 0;
		Arrays.sort(cards);
		int num = cards.length;
		int[][] dp = new int[num][2];
		dp[0][0] = -1;
		dp[0][1] = 1;
		for (int i = 1; i < num; i++) {
			if (cards[i] == cards[i - 1] + 1) {
				dp[i][0] = dp[i - 1][1];
				if (i >= 2)
					dp[i][1] = Math.max(dp[i - 2][0], dp[i - 2][1]) + 1; 
				else 
					dp[i][1] = 1;
			} else {
				dp[i][0] = -1;
				dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]) + 1;
			}
		}
		return Math.max(dp[num - 1][0], dp[num - 1][1]);
	}
	
	public int maxTurns2(int[] cards) {
		if (cards == null)
			return 0;
		if (cards.length == 1)
			return 1;
		Arrays.sort(cards);
		int num = cards.length;
		int[] local = new int[num];
		int[] global = new int[num];
		local[0] = 1;
		global[0] = 1;
		local[1] = cards[1] == (cards[0] + 1) ? 1 : 2;
		global[1] = local[1];
		for (int i = 2; i < num; i++) {
			if (cards[i] == cards[i - 1] + 1) {
				local[i] = global[i - 2] + 1;
				global[i] = Math.max(local[i], global[i - 1]);
			} else {
				local[i] = local[i - 1] + 1;
				global[i] = Math.max(local[i], global[i - 1] + 1);
			}
		}
		return global[num - 1];
	}
}
