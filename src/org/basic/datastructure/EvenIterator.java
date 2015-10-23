package org.basic.datastructure;

import java.util.HashMap;
import java.util.Iterator;

public class EvenIterator {

	public static void main(String[] args) {
//		List<Integer> list = new ArrayList<Integer>();
//		for (int i = 0; i < 10; i++) list.add(i);
//		EvenIterator iter = new EvenIterator(list.iterator());
//		while (iter.hasNext()) System.out.println(iter.next());
		int hour = 11, minute = 24;
		String abc = String.format("%02d:%02d", hour, minute);
		System.out.println(abc);
	}
	
	private String addOne(String num) {
		char[] a = num.toCharArray();
		int carry = 1;
		for (int i = a.length - 1; i >= 0;  i--) {
	int sum = a[i] - '0'+ carry;
		a[i] = (char) (sum % 10 + '0');
	carry = sum / 10;
	if (carry == 0) break;
	}
	StringBuilder sb = new StringBuilder();
	if (carry == 1) sb.append(1);
	sb.append(a);
	return sb.toString();
	}

	public EvenIterator(Iterator<Integer> iter) {
		this.iter = iter;
		while (iter.hasNext()) {
			next = iter.next();
			if (next % 2 == 0)
				break;
		}
		if (next != null && next % 2 == 1)
			next = null;
	}

	public int next() {
		int result = next;
		while (iter.hasNext()) {
			next = iter.next();
			if (next % 2 == 0)
				break;
		}
		if (next == result || next % 2 == 1)
			next = null;
		return result;
	}

	public boolean hasNext() {
		return next != null;
	}

	private Iterator<Integer> iter;
	private Integer next;

}
