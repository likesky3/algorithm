package org.professional.dp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.professional.dp.FindMinCoins.CoinChoice;
// 有可能存在多种解吗？

public class FindMinCoinsTest {
	@Test
	public void testFindMinCoinsNum() throws Exception {
		FindMinCoins obj = new FindMinCoins();
		int[] coins1 = {3, 5};
		ArrayList<CoinChoice> combination = new ArrayList<CoinChoice>();
		assertEquals(1, obj.findMinCoinsNum(3, coins1, combination));
		assertEquals(0, obj.findMinCoinsNum(4, coins1, combination));
		assertEquals(2, obj.findMinCoinsNum(6, coins1, combination));
		assertEquals(2, obj.findMinCoinsNum(8, coins1, combination));
		assertEquals(3, obj.findMinCoinsNum(15, coins1, combination));
	}
	
	@Test
	public void testCheckCombination() throws Exception {
		FindMinCoins obj = new FindMinCoins();
		int[] coins1 = {3, 5};
		ArrayList<CoinChoice> combination = new ArrayList<CoinChoice>();
		obj.findMinCoinsNum(3, coins1, combination);
		assertEquals(true, obj.checkCoinCombination(3, combination));
		obj.findMinCoinsNum(140, coins1, combination);
		assertEquals(true, obj.checkCoinCombination(140, combination));
	}
	
	@Test
	public void testCheckAllSolution() throws Exception {
		FindMinCoins obj = new FindMinCoins();
		int[] coins1 = {3, 5};
		obj.checkAllCombinations(15, coins1);
		obj.checkAllCombinations(140, coins1);
	}
}

class FindMinCoins {
	class CoinChoice {
		int coin;
		int count;
		public CoinChoice(int coin, int count) {
			this.coin = coin;
			this.count = count;
		}
	}
	public int findMinCoinsNum(int sum, int[] coins, ArrayList<CoinChoice> combination) throws Exception{
		if (sum <= 0 || coins == null || coins.length == 0) {
			return 0;
		}
		if (combination == null) {
			throw new Exception("Error input, combination should not be null");
		}
		
		// dp[i] : 组成 i 需要的最少硬币数
		// dp[i] = min(dp[i], dp[i - coins[j]] + 1);
		int[] dp = new int[sum + 1];
		dp[0] = 0;
		int coinTypes = coins.length;
		for (int i = 1; i <= sum; i++) {
			dp[i] = Integer.MAX_VALUE;
			for (int j = 0; j < coinTypes; j++) {
				if (i >= coins[j] && dp[i - coins[j]] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				}
			}
		}
		if (dp[sum] == Integer.MAX_VALUE) // no solution
			return 0;
		
		int[] data = new int[coinTypes];
		int curr = sum;
		while (curr > 0) {
			for (int j = 0; j < coinTypes; j++) {
				if (curr >= coins[j] && dp[curr] == dp[curr - coins[j]] + 1) {
					data[j]++;
					curr -= coins[j];
					break;
				}
			}
		}
		combination.clear();
		for (int i = 0; i < coinTypes; i++) {
			if (data[i] > 0) {
				combination.add(new CoinChoice(coins[i], data[i]));
			}
		}
		return dp[sum];
	}
	
	public boolean checkCoinCombination(int target, ArrayList<CoinChoice> combination) {
		if (target <= 0 || combination == null)
			return false;
		int sum = 0;
		for (CoinChoice item : combination) {
			sum += item.coin * item.count;
		}
		return target == sum;
	}
	
	public int getMinCoinsCombinations(int sum, int[] coins, ArrayList<ArrayList<CoinChoice>> combination) throws Exception{
		if (sum <= 0 || coins == null || coins.length == 0) {
			return 0;
		}
		if (combination == null) {
			throw new Exception("Error input, combination should not be null");
		}
		
		// dp[i] : 组成 i 需要的最少硬币数
		// dp[i] = min(dp[i], dp[i - coins[j]] + 1);
		int[] dp = new int[sum + 1];
		dp[0] = 0;
		int coinTypes = coins.length;
		for (int i = 1; i <= sum; i++) {
			dp[i] = Integer.MAX_VALUE;
			for (int j = 0; j < coinTypes; j++) {
				if (i >= coins[j] && dp[i - coins[j]] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				}
			}
		}
		if (dp[sum] == Integer.MAX_VALUE) // no solution
			return 0;
		
		combination.clear();
		int[] oneSolution = new int[coinTypes];
		recur(sum, coins, dp, oneSolution, combination);
		return dp[sum];
	}
	
	private void recur(int target, int[] coins, int[] dp, int[] oneSolution, ArrayList<ArrayList<CoinChoice>> combinations) {
		if (target == 0) {
			ArrayList<CoinChoice> item = new ArrayList<CoinChoice>();
			for (int i = 0; i < oneSolution.length; i++) {
				if (oneSolution[i] > 0)
					item.add(new CoinChoice(coins[i], oneSolution[i]));
			}
			combinations.add(item);
			return;
		}
		int coinType = coins.length;
		for (int i = 0; i < coinType; i++) {
			if (target >= coins[i] && dp[target] == dp[target - coins[i]] + 1) {
				oneSolution[i]++;
				recur(target - coins[i], coins, dp, oneSolution, combinations);
				oneSolution[i]--;
			}
		}
	}
	
	public void checkAllCombinations(int sum, int[] coins) throws Exception {
		ArrayList<ArrayList<CoinChoice>> combination = new ArrayList<ArrayList<CoinChoice>>();
		getMinCoinsCombinations(sum, coins, combination);
		for (ArrayList<CoinChoice> solution : combination) {
			for (CoinChoice coin : solution) {
				System.out.println(coin.coin + " " + coin.count);
			}
			System.out.println();
		}
		System.out.println("==================");
	}
}
