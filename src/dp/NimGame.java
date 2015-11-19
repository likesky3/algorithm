package dp;

import java.util.HashMap;

public class NimGame {
	// Version1: Time limit exceed.
    public boolean canWinNim1(int n) {
        if (n < 4) return true;
        boolean[] dp = new boolean[4]; // dp[i] at n means the other player can win facing n - i stones.
        dp[1] = dp[2] = dp[3] = true;
        boolean canWin = false;
        for (int i = 4; i <= n; i++) {
            canWin = !(dp[1] && dp[2] && dp[3]);
            dp[3] = dp[2];
            dp[2] = dp[1];
            dp[1] = canWin;
        }
        return canWin;
    }
    // Version2: memorable search. Got StackOverflowError at large input.
    HashMap<Integer, Boolean> cache = new HashMap<Integer, Boolean>();
    public boolean canWinNim(int n) {
        return memSearch(n);
    }
    private boolean memSearch(int n) {
        if (cache.containsKey(n)) return cache.get(n);
        if (n < 4) {
            cache.put(n, true);
            return true;
        } else {
            if (memSearch(n - 1) && memSearch(n - 2) && memSearch(n - 3)) {
                cache.put(n, false);
                return false;
            } else {
                cache.put(n, true);
                return true;
            }
        }
    }
}
