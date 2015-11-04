package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class TakeMostActivities {
	public int solve(int[][] activities) {
		int n = activities.length;
		Pair[] pairs = new Pair[n];
		for (int i = 0; i < n; i++) {
			pairs[i] = new Pair(activities[i][0], activities[i][1]);
		}
		Arrays.sort(pairs, comp);
		int count = 0;
		int lastEnd = -1;
		for (int i = 0; i < n; i++) {
			if (pairs[i].start > lastEnd) {
				count++;
				lastEnd = pairs[i].end;
			}
		}
		return count;
	}
	private Comparator<Pair> comp = new Comparator<Pair>() {
		public int compare(Pair p1, Pair p2) {
			return p1.end - p2.end;
		}
	};
}
class Pair {
	int start, end;
	public Pair(int start, int end) {
		this.start = start;
		this.end = end;
	}
}
