package org.professional.dp;

import junit.framework.Assert;

import org.junit.Test;

public class SuperSumTest {
	@Test
	public void testCalculate() {
		SuperSum obj = new SuperSum();
		Assert.assertEquals(6, obj.calculate(1, 3));
		Assert.assertEquals(10, obj.calculate(2, 3));
		Assert.assertEquals(2002, obj.calculate(4, 10));
		Assert.assertEquals(167960, obj.calculate(10, 10));
	}
}

class SuperSum {
	public int calculate(int k, int n) {
		if (k == 0)
			return n;
		int[][] dp = new int[k + 1][n + 1];
		for (int i = 1; i <= n; i++)
			dp[0][i] = i;
		for (int i = 1; i <= k; i++) {
			for (int j = 1; j <= n; j++) {
				for (int jj = 1; jj <= j; jj++) {
					dp[i][j] += dp[i - 1][jj];
				}
			}
		}
		return dp[k][n];
	}
}