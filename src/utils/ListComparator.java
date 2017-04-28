package utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListComparator implements Comparator<List<Integer>> {

	@Override
	public int compare(List<Integer> o1, List<Integer> o2) {
		if (o1 == null && o2 == null) {
			return 0;
		} else if (o1 == null || o2 == null) {
			return o1 == null ? -1 : 1;
		}

		if (o1.size() != o2.size()) {
			return o1.size() < o2.size() ? -1 : 1;
		} else {
			Collections.sort(o1);
			Collections.sort(o2);
			for (int i = 0; i < o1.size(); i++) {
				Integer a = o1.get(i);
				Integer b = o2.get(i);
				if (a.compareTo(b) != 0) {
					return a.compareTo(b);
				}
			}
			return 0;
		}
	}

}
