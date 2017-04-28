package sort;

public class QuickSort {
	public static int[] quickSort(int[] array) {
	    if (array == null || array.length < 2) {
	      return array;
	    }
	    quickSort(array, 0, array.length - 1);
	    return array;
	  }
	  
	  private static void quickSort(int[] array, int left, int right) {
	    if (left >= right) {
	      return;
	    }
	    int pivotIdx = partition(array, left, right);
	    quickSort(array, left, pivotIdx - 1);
	    quickSort(array, pivotIdx + 1, right);
	  }
	  
	  private static int partition(int[] array, int left, int right) {
	    int pivotIdx = getPivotIdx(left, right);
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
	  
	  private static int getPivotIdx(int left, int right) {
	    return left + (int)(Math.random() * (right - left + 1));
	  }
	  
	  private static void swap(int[] array, int i, int j) {
	    int tmp = array[i];
	    array[i] = array[j];
	    array[j] = tmp;
	  }
}
