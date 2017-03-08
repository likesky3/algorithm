package string;

import utils.WhiteBoard;

public class ReorderString {

	public static void main(String[] args) {
		ReorderString obj = new ReorderString();
		int[] array = {1, 2, 3, 4, 5};
		obj.reorder(array);
		WhiteBoard.print(array);
	}
/////////////////////////Method 3: O(n),  link: leetcode find all numbers disappeared in an array
	// This method depends on assumption that all elements have same sign, all positive or negative
	public int[] reorder3(int[] array) {
		if (array == null || array.length < 4) {
			return array;
		}
		// array[0] do not need to change position
		// if the left half has been moved to the correct position, then job is done
		int n = array.length;
		for (int i = 1; i < n / 2; i++) {
			if (array[i] > 0) {
				// move i to its expected position: targetPos, then put element in targetPos to its expected positoin,
				// continue this process until we found an element whose expected position is i
				int currVal = array[i];
				int targetPos = getTargetPos(i, n);
				while (targetPos != i) {
					int tmp = array[targetPos];
					array[targetPos] =  -currVal;
					targetPos = getTargetPos(targetPos, n);
					currVal = tmp;
				}
				array[i] = -currVal;
			}
		}
		for (int i = 1; i < n; i++) {
			if (array[i] < 0) {
				array[i] = -array[i];
			}
		}
		return array;
	}
	
	private int getTargetPos(int i, int n) {
		if (i < n / 2) { // left half
			return 2 * i;
		} else { // right half
			return (i - n / 2) * 2 + 1;
		}
	}
	
	public int[] reorder2(int[] array) {
		if (array == null || array.length < 4) {
			return array;
		}
		int n = array.length;
		for (int i = 1; i < n / 2; i++) {
			if (array[i] > 0) {
				// find expected value for pos i, move it back to i, then move to its old index, do same thing
				// until we need to move old array[i] to its expected positon
				int original = array[i];
				int curr = i;
				int target = getTarget(curr, n);
				while (target != i) {
					array[curr] = -array[target];
					curr = target;
					target = getTarget(curr, n);
				}
				array[curr] = -original; 
			}
		}
		for (int i = 0; i < n; i++) {
			if (array[i] < 0) {
				array[i] = -array[i];
			}
		}
		return array;
	}
	
	private int getTarget(int curr, int n) {
		if (curr % 2 == 0) {
			return curr / 2;
		} else {
			return (curr - 1 + n) / 2;
		}
	}
//////////////////////////////////Method 2	
	public int[] reorder(int[] array) {
		if (array == null || array.length < 4) {
			return array;
		}
		int n = array.length;
		if (n % 2 == 1) {
		  n--;
		}
		recur(array, 0, n - 1);
		return array;
	}
	
	private void recur(int[] array, int left, int right) {
	  int n = right - left + 1;
	  if (n <= 2) {
	    return;
	  }
	  int m = left + n / 2; // remember add left here
	  int lm = left + n / 4;
	  int rm = left + n * 3 / 4;
	  reverse(array, lm, m - 1);
	  reverse(array, m, rm - 1);
	  reverse(array, lm, rm - 1); // this one should do in the last
	  WhiteBoard.print(array);
	  int leftHalfLen = lm - left;
	  recur(array, left, left + leftHalfLen * 2 - 1);
	  recur(array, left + leftHalfLen * 2, right);
	}
	
	private void reverse(int[] array, int left, int right) {
	  while (left < right) {
	    int tmp = array[left];
	    array[left] = array[right];
	    array[right] = tmp;
	    left++;
	    right--;
	  }
	}
	
////////////////////////Method 1: O(nlogn)
	public int[] reorder1(int[] array) {
		recur1(array, 0, array.length - 1);
		return array;
	}

	private void recur1(int[] array, int left, int right) {
		int n = right - left + 1;
		if (n < 4) {
			return;
		}
		if (n % 2 == 1) {
			recur(array, left, right - 1);
			return;
		}

		int unit = 0;
		if (n % 4 == 0) {
			unit = n / 4;
		} else {
			unit = (n - 2) / 4;
		}

		reverse(array, left + unit, right - unit);
		reverse(array, left + unit, right - n / 2);
		reverse(array, left + n / 2, right - unit);
		if (n % 4 > 0) {
			reverse(array, right - n / 2, left + n / 2);
		}
		recur1(array, left, left + unit * 2 - 1);
		recur1(array, right - unit * 2 + 1, right);
	}

//	private void reverse(int[] array, int i, int j) {
//		while (i < j) {
//			int tmp = array[i];
//			array[i] = array[j];
//			array[j] = tmp;
//			i++;
//			j--;
//		}
//	}

}
