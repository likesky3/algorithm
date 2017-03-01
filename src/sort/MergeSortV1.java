package sort;

import utils.WhiteBoard;

public class MergeSortV1 {

	public static void main(String[] args) {
		MergeSortV1 obj = new MergeSortV1();
//		System.out.println(obj.split("A1B2D3C4EF"));
		System.out.println(obj.mixOneByOne("ABCDEF123456"));
	}

	// A1B2C3D4 -> ABCD1234
	public String split(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		char[] helper = new char[s.length()];
		char[] arr = s.toCharArray();
		mergeSort(arr, 0, arr.length - 1, helper);
		return new String(arr);
	}

	private void mergeSort(char[] arr, int left, int right, char[] helper) {
		if (left >= right) {
			return;
		}
		int mid = left + (right - left) / 2;
		mergeSort(arr, left, mid, helper);
		mergeSort(arr, mid + 1, right, helper);
		merge(arr, left, mid, right, helper);
	}

	private void merge(char[] arr, int left, int mid, int right, char[] helper) {
		for (int i = left; i <= right; i++) {
			helper[i] = arr[i];
		}
		int i = left, j = mid + 1, k = left;
		while (i <= mid && j <= right) {
			if (compare(helper[i], helper[j]) < 0) {
				arr[k++] = helper[i++];
			} else {
				arr[k++] = helper[j++];
			}
		}
		while (i <= mid) {
			arr[k++] = helper[i++];
		}
	}

	private int compare(char a, char b) {
		if (a >= 'A' && b >= 'A') {
			return a - b;
		} else if (a >= 'A') {
			return -1;
		} else if (b >= 'A') {
			return 1;
		} else {
			return a - b;
		}
	}

	// ABCD1234 -> A1B2C3D4
	public String mixOneByOne(String s) {
		if (s == null || s.length() == 0) {
			return "";
	}
	char[] array = s.toCharArray();
		mergeSortMix(array, 0, array.length - 1);
		return new String(array);
	}

	private void mergeSortMix(char[] array, int left, int right) {
		if (left + 1 >= right) {
			return;
		}
		int mid = left + (right - left) / 2;
		int quarterLen = (right - left + 1) / 4;
		transfer(array, mid - quarterLen + 1, mid, mid + quarterLen); // special attention  !!!
		mergeSortMix(array, left, mid);
		mergeSortMix(array, mid + 1, right);
	}

	// AB12 -> 12AB, by reversing 3 times
	private void transfer(char[] array, int left, int mid, int right) {
//		System.out.println("transfer start...");
		reverse(array, left, right);
		reverse(array, left, mid);
		reverse(array, mid + 1, right);
//		System.out.println("transfer end...");
	}

	private void reverse(char[] array, int left, int right) {
		while (left < right) {
			char tmp = array[right];
			array[right--] = array[left];
			array[left++] = tmp;
		}
	}
}
