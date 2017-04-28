package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import utils.WhiteBoard;

public class MedianTracker {

	public static void main(String[] args) {
		MedianTracker obj = new MedianTracker();
		
		List<Integer> input = new ArrayList<>();
		for (int i = 300; i >= 0; i--) {
			input.add(i);
		}
//		obj.test(input);

		obj.clear();
		input.clear();
		for (int i = 0; i < 300; i++) {
			input.add(i);
		}
//		obj.test(input);
		
		obj.clear();
		input.clear();
		int length = 100;
		for (int i = 0; i < length; i++) {
			input.add((int)(Math.random() * length));
		}
//		WhiteBoard.print(input);
		obj.test(input);
		// 0 4 5 0 3 2 1 9 0 4
		List<Integer> tmp = new ArrayList<>();
		int[] tmpArray = {0, 4, 5, 0, 3, 2, 1, 9, 0, 4};
		for (int i : tmpArray) {
			tmp.add(i);
		}
//		obj.getMedianSlowWay(tmp);
//		obj.test(tmp);

	}
	
	private void test(List<Integer> input) {
		List<Double> medians = new ArrayList<>();
		for (int i : input) {
			read(i);
			medians.add(getMedian());
		}
		List<Double> medians2 = getMedianSlowWay(input);
		System.out.println("test result =" + equals(medians, medians2));
	}
	
	private List<Double> getMedianSlowWay(List<Integer> input) {
//		Collections.sort(input);
		List<Double> result = new ArrayList<>();
		List<Integer> data = new ArrayList<>();
		for (int i = 1; i <= input.size(); i++) {
			data.add(input.get(i - 1));
			Collections.sort(data);
			if ((i & 1) == 0) {
				result.add((data.get(i / 2 - 1) + data.get(i / 2)) / 2.0);
//				System.out.println("odd median=" + result.get(result.size() - 1));
			} else {
				result.add(data.get(i / 2) * 1.0);
//				System.out.println("even median=" + result.get(result.size() - 1));
			}
		}
//		WhiteBoard.printDouble(result);
		return result;
	}
	
	private boolean equals(List<Double> one, List<Double> two) {
		for (int i = 0; i < one.size(); i++) {
//			System.out.printf("i=%d: toTest=%f, standard=%f\n", i, one.get(i), two.get(i));
			if (!one.get(i).equals(two.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	private static final int MAX_CAPACITY = 3;
	PriorityQueue<Integer> smallerHeap; // a max heap
	PriorityQueue<Integer> largerHeap; // a min heap
	List<Integer> smallerDisk;
	List<Integer> largerDisk;
	Integer maxInSmallerDisk = null;
	Integer minInLargerDisk = null;
	int val;
	public MedianTracker() {
		smallerHeap = new PriorityQueue<>(10, Collections.reverseOrder());
		largerHeap = new PriorityQueue<>();
		smallerDisk = new ArrayList<>();
		largerDisk = new ArrayList<>();
	}
	
	public void read(int val) {
		// check sperately, because smallerHeap and largerHeap don't always same
		if (smallerHeap.size() == MAX_CAPACITY) {
			moveSmallerToDisk();
		}
		if (largerHeap.size() == MAX_CAPACITY) {
			moveLargerToDisk();
		}
//		System.out.printf("val=%d\n", val);
		if (smallerHeap.size() + smallerDisk.size() == largerHeap.size() + largerDisk.size()) {
			if (largerHeap.isEmpty() || val <= largerHeap.peek()) {
				smallerHeap.offer(val);
			} else {
				smallerHeap.offer(largerHeap.poll());
				largerHeap.offer(val);
				if (minInLargerDisk != null && largerHeap.peek() > minInLargerDisk) {
					rebuildLargerHeap();
				}
			}
		} else {
			if (val >= smallerHeap.peek()) {
				largerHeap.offer(val);
			} else {
				largerHeap.offer(smallerHeap.poll());
				smallerHeap.offer(val);
				// in this branch, peek of smallerHeap may become smaller
				if (maxInSmallerDisk != null && smallerHeap.peek() < maxInSmallerDisk) {
					rebuildSmallerHeap();
				}
			}
		}
	}
	
	private void rebuildSmallerHeap() {
//		System.out.println("call rebuildSmaller");
		// actually should be external sorting process
		while (!smallerHeap.isEmpty()) {
			smallerDisk.add(smallerHeap.poll());
		}
		Collections.sort(smallerDisk);
		// put the largest max_capacity / 2 elements into heap
		for (int i = smallerDisk.size() - 1; i >= smallerDisk.size() - MAX_CAPACITY / 2; i--) {
			smallerHeap.offer(smallerDisk.get(i));
		}
		maxInSmallerDisk = smallerDisk.get(smallerDisk.size() - MAX_CAPACITY / 2 - 1);
		smallerDisk = copyOf(smallerDisk, 0, smallerDisk.size() - MAX_CAPACITY / 2);
	}
	
	private void rebuildLargerHeap() {
//		System.out.println("call rebuildLarger");
		// actually should be external sorting process
		while (!largerHeap.isEmpty()) {
			largerDisk.add(largerHeap.poll());
		}
		Collections.sort(largerDisk);
		// put the smallest max_capacity / 2 elements into heap
		for (int i = 0; i < MAX_CAPACITY / 2; i++) {
			largerHeap.offer(largerDisk.get(i));
		}
		minInLargerDisk = largerDisk.get(MAX_CAPACITY / 2);
		largerDisk= copyOf(largerDisk, MAX_CAPACITY / 2, largerDisk.size() - MAX_CAPACITY / 2);
	}
	
	private List<Integer> copyOf(List<Integer> list, int startIdx, int len) {
		List<Integer> result = new ArrayList<>();
		for (int i = startIdx; i < startIdx + len; i++) {
			result.add(list.get(i));
		}
		return result;
	}
	
	// move half size of heap to disk
	private void moveSmallerToDisk() {
		List<Integer> list = new ArrayList<>();
		while (!smallerHeap.isEmpty()) {
			list.add(smallerHeap.poll());
		}
		
		// list is in descending order
		int mid = list.size() / 2;
		for (int i = 0; i < mid; i++) {
			smallerHeap.offer(list.get(i));
		}
		// update maxInSmallerDisk and move half elements into disk
		if (maxInSmallerDisk == null || list.get(mid) > maxInSmallerDisk) {
			maxInSmallerDisk = list.get(mid);
		} 
		for (int i = mid; i < list.size(); i++) {
			smallerDisk.add(list.get(i));
		}
	}
	
	private void moveLargerToDisk() {
		List<Integer> list = new ArrayList<>();
		while (!largerHeap.isEmpty()) {
			list.add(largerHeap.poll());
		}
		int mid = list.size() / 2;
		for (int i = 0; i < mid; i++) {
			largerHeap.offer(list.get(i));
		}
		if (minInLargerDisk == null || list.get(mid) < minInLargerDisk) {
			minInLargerDisk = list.get(mid);
		}
		for (int i = mid; i < list.size(); i++) {
			largerDisk.add(list.get(i));
		}
	}
	
	public Double getMedian() {
		int totalSize = smallerHeap.size() + largerHeap.size() + smallerDisk.size() + largerDisk.size();
//		if (totalSize == 11) {
//		System.out.printf("smallheap=%d, largeHeap=%d, smallDisk=%d, largeDisk=%d, maxOfSmall=%d, minOfLarger=%d\n",
//				smallerHeap.size(), largerHeap.size(), smallerDisk.size(), largerDisk.size(), smallerHeap.peek(), largerHeap.peek());
	//	}
		if (totalSize == 0) {
			return null;
		} else if (totalSize % 2 == 0) {
			return (smallerHeap.peek() + largerHeap.peek()) / 2.0;
		} else {
			return 1.0 * smallerHeap.peek();
		}
	}
	
	public void clear() {
		smallerHeap.clear();
		largerHeap.clear();
		smallerDisk.clear();
		largerDisk.clear();
		maxInSmallerDisk = null;
		minInLargerDisk = null;
	}

}
