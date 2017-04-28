package weiss.chap1;

import java.util.Arrays;

public class P14OrderedCollection <T extends Comparable<? super T>>{
	private Object[] array;
	private int size;
	public P14OrderedCollection(int capacity) {
		array = new Object[capacity];
		size = 0;
	}
	
	public P14OrderedCollection() {
		this(16);
	}
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void makeEmpty() {
		size = 0;
	}
	
	public void insert(T item) {
		if (size == array.length) {
			enlarge();
		}
		int idx = binarySearch(item);
		if (idx < 0) {
			idx = -idx;
		}
		for (int i = size; i > idx; i--) {
			array[i] = array[i - 1];
		}
		array[idx] = item;
		size++;
	}
	
	public boolean remove(T item) {
		if (item == null) {
			return false;
		}
		int idx = binarySearch(item);
		if (idx < 0) {
			return false;
		}
		for (int i = idx + 1; i < size; i++) {
			array[i - 1] = array[i];
		}
		size--;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public T findMin() {
		return size == 0 ? null : (T)array[0];
	}
	
	@SuppressWarnings("unchecked")
	public T findMax() {
		return size == 0 ? null : (T)array[size - 1];
	}
	
	private void enlarge() {
		
	}
	
	@SuppressWarnings("unchecked")
	private int binarySearch(T item) {
		int left = 0;
		int right = size - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (array[mid].equals(item)) {
				return mid;
			} else if (item.compareTo((T)(array[mid])) < 0) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -left;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(array);
	}
	
	public static void main(String[] args) {
		int capacity = 10;
		P14OrderedCollection<Integer> collection = new P14OrderedCollection<>(capacity);
		for (int i = 0; i < capacity; i++) {
			collection.insert((int)(Math.random() * capacity));
		}
		System.out.println(collection);
		
//		System.out.println(collection.findMax());
		System.out.println();
		while (!collection.isEmpty()) {
			Integer min = collection.findMin();
			collection.remove(min);
			System.out.println(min);
		}
		
	}

}
