package recursion;

public class QuickSort {
	public int[] quickSort(int[] array) {
		if (array == null || array.length < 2) {
			return array;
		}
		quickSort(array, 0, array.length - 1);
		return array;
	}

	private static final int CUTOFF = 10;

	private void quickSort(int[] array, int left, int right) {
		if (right - left <= CUTOFF) {
			insertSort(array, left, right);
			return; // bug1: forgot return
		}
		int pivot = median3(array, left, right);
		int i = left - 1, j = right - 1;
		while (i < j) {
			while (i < j && array[++i] < pivot) {
			}
			while (i < j && array[--j] > pivot) { // bug 3: write <
			}
			if (i < j) {
				swap(array, i, j);
			}
		}
		swap(array, i, right - 1);
		quickSort(array, left, i - 1);
		quickSort(array, i + 1, right);
	}

	private void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	private int median3(int[] array, int left, int right) {
		int mid = left + (right - left) / 2;
		if (array[mid] < array[left]) {
			swap(array, left, mid);
		}
		if (array[right] < array[mid]) {
			swap(array, mid, right);
		}
		if (array[mid] < array[left]) {
			swap(array, left, mid);
		}
		swap(array, mid, right - 1);
		return array[right - 1];
	}

	private void insertSort(int[] array, int left, int right) {
		for (int i = left + 1; i <= right; i++) { // bug4: write i < right
		  int tmp = array[i];
		  int j = i - 1;
		  for (; j >= 0; j--) {
		    if (array[j] <= tmp) { // bug2: array[j] < array[i]
		      break;
		    }
		    array[j + 1] = array[j];
		  }
		  array[j + 1] = tmp;
		}
	}
}
