package dp;

import junit.framework.Assert;

import org.junit.Test;


public class FibonacciDiv2Test {
	@Test
	public void testFindInFiabonacciDiv2() {
		FibonacciDiv2 obj = new FibonacciDiv2();
		Assert.assertEquals(0, obj.find(0));
		Assert.assertEquals(0, obj.find(1));
		Assert.assertEquals(0, obj.find(13));
		Assert.assertEquals(2, obj.find(15));
		Assert.assertEquals(2, obj.find(19));
		Assert.assertEquals(167960, obj.find(1000000));
	}
}

class FibonacciDiv2 {
	public int find(int N) {
		if (N <= 1)
			return 0;
		int[] dp = {0, 1, 1};
 		while (dp[2] < N) {
 			dp[0] = dp[1];
 			dp[1] = dp[2];
 			dp[2] = dp[1] + dp[0];
 		}
 		if (dp[2] == N)
 			return 0;
 		int diff1 = N - dp[1];
 		int diff2 = dp[2] - N;
 		return diff1 < diff2 ? diff1 : diff2;
	}
	
}