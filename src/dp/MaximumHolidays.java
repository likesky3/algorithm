package dp;

import static org.junit.Assert.*;

import org.junit.Test;

// category: schedule
public class MaximumHolidays {

//	public static void main(String[] args) {
//		
//	}
	@Test
	public void testMaxHolidays() {
		MaximumHolidays obj = new MaximumHolidays();
		int[][] dist1 = {{0, 8},{8, 0}};
		int[][] holidays1 = {{1, 2}, {1, 2}};
		assertEquals(4, obj.maxHolidays(dist1, holidays1, 6));
		assertEquals(4, obj.maxHolidays2(dist1, holidays1, 6));

		int[][] dist2 = {{0, 2},{2, 0}};
		int[][] holidays2 = {{1, 2}, {2, 1}};
		assertEquals(4, obj.maxHolidays(dist2, holidays2, 6));
		assertEquals(4, obj.maxHolidays2(dist2, holidays2, 6));
		
		int[][] dist3 = {{0, 2},{2, 0}};
		int[][] holidays3 = {{2, 1}, {2, 1}};
		assertEquals(4, obj.maxHolidays(dist3, holidays3, 6));
		assertEquals(4, obj.maxHolidays2(dist3, holidays3, 6));
		
		// test follow up 1
		boolean[][] isHoliday1 = {{true, false, false},{false, true, true}};
		boolean[][] isHoliday2 = {{true, false},{false, true},{false, true}};
		assertEquals(2, obj.maxHolidays4(dist3, isHoliday1, 6, 1));
		assertEquals(3, obj.maxHolidays4(dist3, isHoliday2, 6, 1));
	}
	
	// 8:10 - 9:16
	// dist[i][j], flighting hours between city i & city j
	// holidays[i][j], at the ith week, holidays num at city j
	// at each weekend, you can travel from one city to another, as long as the flighting hours is less than 6
	// calculate maximum holidays one can get in one year(52 weeks)
	public int maxHolidays(int[][] dist, int[][] holidays, int maxDistAllowedInOneWeek) {
		if (dist == null || holidays == null) return 0;
		int m = holidays.length, n = dist.length;
		// local[i][j], max holidays get from the first i weeks, work among the first j cities, 
		// and the last week working at the (j - 1)th city (count start from 0)
		int[][] local = new int[m + 1][n + 1];
		// global[i][j], max holidays get from the first i weeks, work among the first j cities
//		int[][] global = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
//				for (int k = 0; k < j; k++) { // bug2
				for (int k = 0; k < n; k++) {
					if (dist[j - 1][k] <= maxDistAllowedInOneWeek) {
//						local[i][j] = Math.max(local[i][j], local[i - 1][k] + holidays[i - 1][j - 1]); // bug1
						local[i][j] = Math.max(local[i][j], local[i - 1][k + 1] + holidays[i - 1][j - 1]);
//						System.out.println(i + " " + j + " " + k + " " + local[i][j] + " " + holidays[i - 1][j - 1]);
					}
				}
//				global[i][j] = Math.max(global[i][j], local[i][j]);
			}
		}
		int result = 0;
		for (int i = 1; i <= n; i++) {
			result = Math.max(result, local[m][i]);
		}
		return result;
	}
	
	// Version 2: loacl[i][j], i, j is the actual index
	// time: O(m * n * n), space : O (m * n) which can be reduced to O(n)
	public int maxHolidays2(int[][] dist, int[][] holidays, int maxDistAllowedInOneWeek) {
		if (dist == null || holidays == null) return 0;
		int m = holidays.length, n = dist.length;
		// local[i][j], max holidays get from the first i + 1 weeks, work among the first j + 1 cities, 
		// and the last week working at the jth city (count start from 0)
		int[][] local = new int[m][n];
		for (int i = 0; i < n; i++) {
			local[0][i] = holidays[0][i];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if (dist[j][k] <= maxDistAllowedInOneWeek) {
						local[i][j] = Math.max(local[i][j], local[i - 1][k] + holidays[i][j]);
					}
				}
			}
		}
		
		int result = 0;
		for (int i = 0; i < n; i++) {
			result = Math.max(result, local[m - 1][i]);
		}
		return result;
	}
	// version 3: 对比一起做的题目，最里层循环可以先不算加法
	public int maxHolidays3(int[][] dist, int[][] holidays, int maxDistAllowedInOneWeek) {
		if (dist == null || holidays == null) return 0;
		int m = holidays.length, n = dist.length;
		// local[i][j], max holidays get from the first i + 1 weeks, work among the first j + 1 cities, 
		// and the last week working at the jth city (count start from 0)
		int[][] local = new int[m][n];
		for (int i = 0; i < n; i++) {
			local[0][i] = holidays[0][i];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 0; j < n; j++) {
				local[i][j] += holidays[i][j];
				int maxPrev = 0;
				for (int k = 0; k < n; k++) {
					if (dist[j][k] <= maxDistAllowedInOneWeek) {
						maxPrev = Math.max(maxPrev, local[i - 1][k]);
					}
				}
				local[i][j] += maxPrev;
			}
		}
		
		int result = 0;
		for (int i = 0; i < n; i++) {
			result = Math.max(result, local[m - 1][i]);
		}
		return result;
	}
	// follow up 1: if you change one time, you can’t change in < 30 days. isHoliday[country][day], 365 days one year.
	public int maxHolidays4(int[][] dist, boolean[][] isHoliday, int maxDistAllowedInOneWeek, int leastRemainSame) {
		if (dist == null || isHoliday == null) return 0;
		int m = isHoliday.length, n = dist.length; // m: total days, n: total cities
		int[][] dp = new int[m][n]; // dp[i][j], max holiday got when day i in city j
		// init
		// leastRemainSame, least days you should remain in the same city
		for (int i = 0; i < leastRemainSame; i++) {
			for (int j = 0; j < n; j++) {
				if (i > 0) {
					dp[i][j] = dp[i - 1][j] + (isHoliday[i][j] ? 1 : 0);
				} else {
					dp[0][j] = isHoliday[0][j] ? 1 : 0;
				}
//				System.out.println(i + " " + j + "=" + dp[i][j]);
			}
		}
		
		// induction
		for (int i = leastRemainSame; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dp[i][j] += isHoliday[i][j] ? 1 : 0;
				int maxPrev = 0;
				for (int k = 0; k < n; k++) {
					if (dist[j][k] <= maxDistAllowedInOneWeek) {
						int oneStay = 0;
						for (int d = 1; d < leastRemainSame; d++) {
							oneStay += isHoliday[i - d][k] ? 1 : 0;
						}
						maxPrev = Math.max(maxPrev, dp[i - leastRemainSame][k] + oneStay);
//						System.out.println(i + " " + j + " " +  k + "=" + dp[i - leastRemainSame][k] + " " + oneStay);
					}
				}
				dp[i][j] += maxPrev;
			}
		}
		int result = 0;
		for (int i = 0; i < n; i++) {
			result = Math.max(dp[m - 1][i], result);
		}
		return result;
	}
	
	// follow up 2: 一年某些天必须待在指定city, assign[i] = j, if j >= 0 means day i should work at city j, else any city is ok 
	public int maxHolidays6(int[][] dist, boolean[][] isHoliday, int maxDistAllowedInOneWeek, int leastRemainSame, int[] assign) {
		if (dist == null || isHoliday == null) return 0;
		int m = isHoliday.length, n = dist.length; // m: total days, n: total cities
		int[][] dp = new int[m][n]; // dp[i][j], max holiday got when day i in city j
		// init
		for (int i = 0; i < leastRemainSame; i++) {
			for (int j = 0; j < n; j++) {
				if (assign[i] >= 0 && assign[i] != j) {
					dp[i][j] = Integer.MIN_VALUE;
					continue;
				}
				if (i > 0) {
					dp[i][j] = dp[i - 1][j] + (isHoliday[i][j] ? 1 : 0);
				} else {
					dp[0][j] = isHoliday[0][j] ? 1 : 0;
				}
//				System.out.println(i + " " + j + "=" + dp[i][j]);
			}
		}
		
		// induction
		for (int i = leastRemainSame; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (assign[i] >= 0 && assign[i] != j) {
					dp[i][j] = Integer.MIN_VALUE;
					continue;
				}
				
				dp[i][j] += isHoliday[i][j] ? 1 : 0;
				int maxPrev = 0;
				for (int k = 0; k < n; k++) {
					if (dist[j][k] <= maxDistAllowedInOneWeek) {
						int oneStay = 0;
						for (int d = 1; d < leastRemainSame; d++) {
							oneStay += isHoliday[i - d][k] ? 1 : 0;
						}
						maxPrev = Math.max(maxPrev, dp[i - leastRemainSame][k] + oneStay);
//						System.out.println(i + " " + j + " " +  k + "=" + dp[i - leastRemainSame][k] + " " + oneStay);
					}
				}
				dp[i][j] += maxPrev;
			}
		}
//		int result = 0;
		int result = Integer.MIN_VALUE; // which can be used to detect "dead lock" here  
		for (int i = 0; i < n; i++) {
			result = Math.max(dp[m - 1][i], result);
		}
		return result;
	}
	
	// follow up 3: forbidden[i][j], means day i mustn't work at city j
	public int maxHolidays6(int[][] dist, boolean[][] isHoliday, int maxDistAllowedInOneWeek, int leastRemainSame, boolean[][] forbidden) {
		if (dist == null || isHoliday == null) return 0;
		int m = isHoliday.length, n = dist.length; // m: total days, n: total cities
		int[][] dp = new int[m][n]; // dp[i][j], max holiday got when day i in city j
		// init
		for (int i = 0; i < leastRemainSame; i++) {
			for (int j = 0; j < n; j++) {
				if (forbidden[i][j]) {
					dp[i][j] = Integer.MIN_VALUE;
					continue;
				}
				if (i > 0) {
					dp[i][j] = dp[i - 1][j] + (isHoliday[i][j] ? 1 : 0);
				} else {
					dp[0][j] = isHoliday[0][j] ? 1 : 0;
				}
//				System.out.println(i + " " + j + "=" + dp[i][j]);
			}
		}
		
		// induction
		for (int i = leastRemainSame; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (forbidden[i][j]) {
					dp[i][j] = Integer.MIN_VALUE;
					continue;
				}
				
				dp[i][j] += isHoliday[i][j] ? 1 : 0;
				int maxPrev = 0;
				for (int k = 0; k < n; k++) {
					if (dist[j][k] <= maxDistAllowedInOneWeek) {
						int oneStay = 0;
						for (int d = 1; d < leastRemainSame; d++) {
							oneStay += isHoliday[i - d][k] ? 1 : 0;
						}
						maxPrev = Math.max(maxPrev, dp[i - leastRemainSame][k] + oneStay);
//						System.out.println(i + " " + j + " " +  k + "=" + dp[i - leastRemainSame][k] + " " + oneStay);
					}
				}
				dp[i][j] += maxPrev;
			}
		}
//		int result = 0;
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			result = Math.max(dp[m - 1][i], result);
		}
		return result;
	}
}
