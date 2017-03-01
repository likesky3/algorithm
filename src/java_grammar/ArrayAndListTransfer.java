package java_grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayAndListTransfer {

	public static void main(String[] args) {
		List<int[]> res = new ArrayList<>();
		int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
		for (int[] person : people) {
			res.add(person);
		}
		int[][] resArray = res.toArray(new int[people.length][]);
		
		List<Integer> list2 = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			list2.add(i);
		}
//		int[] array2 = list2.toArray(new int[list2.size()]); // compiler ERROR
		Integer[] array2 = list2.toArray(new Integer[list2.size()]);
		
		// Arrays.asList() Returns a fixed-size list backed by the specified array.  (Changes to
	    //  the returned list "write through" to the array.) 
		// The returned list is not ArrayList instance, but a list which is a static class of Arrays.
		List<Integer> list3 = Arrays.asList(1, 2, 3); // Arrays.asList() returns a fixed sized list
//		list3.add(4); // Running time ERROR:  java.lang.UnsupportedOperationException
		
		List<Integer> list4 = Arrays.asList(array2); 
		list4.set(0, -1);
		System.out.println(list4.get(0) + " " + array2[0]); // same value
		list4.add(0, -2);
		
		
	}

}
