package hash;

// https://leetcode.com/problems/bulls-and-cows/
public class BullsAndCows {
	// Version 4: one pass.
	public String getHint(String secret, String guess) {
        int n = secret.length(), bulls = 0, cows = 0;
        int[] map = new int[10];
        for (int i = 0; i < n; i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g) {
                bulls++;
            } else {
                if (map[s] < 0) cows++;
                if (map[g] > 0) cows++;
                map[s]++;
                map[g]--;
            }
        }
        return bulls + "A" + cows + "B";
    }
	// Version 3, two pass, straight forward than version 4.
    public String getHint3(String secret, String guess) {
        int n = secret.length(), bulls = 0, cows = 0;
        int[] sMap = new int[10];
        int[] gMap = new int[10];
        // Count bulls.
        for (int i = 0; i < n; i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g) {
                bulls++;
            } else {
                sMap[s]++;
                gMap[g]++;
            }
        }
        // Count cows.
        for (int i = 0; i < 10; i++) {
            cows += Math.min(sMap[i], gMap[i]);
        }
        return bulls + "A" + cows + "B";
    }
    // Version 2, my version, 3 passes.
    public String getHint2(String secret, String guess) {
        int n = secret.length(), bulls = 0, cows = 0;
        int[] map = new int[256];
        for (int i = 0; i < n; i++) {
            map[secret.charAt(i)]++;
        }
        // Count bulls.
        for (int i = 0; i < n; i++) {
            char curr = guess.charAt(i);
            if (secret.charAt(i) == curr) {
                bulls++;
                map[curr]--;
            }
        }
        // Count cows.
        for (int i = 0; i < n; i++) {
            char curr = guess.charAt(i);
            if (secret.charAt(i) != curr && map[curr] > 0) {
                cows++;
                map[curr]--;
            }
        }
        return bulls + "A" + cows + "B";
    }
    // Version 1: fail at ("1122", "1222"), wrong answer is 3A1B, correct is 3A0B
    public String getHint1(String secret, String guess) {
        int n = secret.length(), bulls = 0, cows = 0;
        int[] map = new int[256];
        for (int i = 0; i < n; i++) {
            map[secret.charAt(i)]++;
        }
        for (int i = 0; i < n; i++) {
            char curr = guess.charAt(i);
            if (secret.charAt(i) == curr) {
                bulls++;
                map[curr]--;
            } else if (map[curr] > 0){
                cows++;
                map[curr]--;
            } // else curr is not a char in secret
        }
        return bulls + "A" + cows + "B";
    }
}
