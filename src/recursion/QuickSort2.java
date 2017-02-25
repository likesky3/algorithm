package recursion;

/**
 * This version is easier to write and also very elegant.*/
public class QuickSort2 {
	public int[] quickSort(int[] array) {
		if (array == null || array.length < 2) {
			return array;
		}
		quickSort(array, 0, array.length - 1);
		return array;
	}

	private void quickSort(int[] array, int left, int right) {
		if (left >= right) {
			return;
		}
		int pivotIdx = quickPartition(array, left, right);
		quickSort(array, left, pivotIdx - 1);
		quickSort(array, pivotIdx + 1, right);
	}

	private int quickPartition(int[] array, int left, int right) {
		int pivotIdx = selectPivot(left, right);
		int pivot = array[pivotIdx];
		swap(array, pivotIdx, right);
		int i = left, j = right - 1;
		while (i <= j) {
			if (array[i] < pivot) {
				i++;
			} else if (array[j] >= pivot) {
				j--;
			} else {
				swap(array, i++, j--);
			}
		}
		swap(array, i, right);
		return i;
	}

	private int selectPivot(int left, int right) {
		return left + (int) (Math.random() * (right - left + 1)); // Math.random() returns [0,1), so (right - left + 1) instead of (right - left)
	}

	private void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
}
