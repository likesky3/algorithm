package binary_search;

public class FindLargestSmaller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindLargestSmaller obj = new FindLargestSmaller();
		int[] data = {41,6,0,3,22,15,31,8469,6336,5734,6509,9177,8770};
		obj.findLargestSmaller(data, 0, data.length - 1, 41);
	}
	
	private int findLargestSmaller(int[] data, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (target >= data[mid]) {
                left = mid;
            } else {
                right = mid - 1; 
            }
            System.out.printf("[left=%d]=%d, [right=%d]=%d, [mid=%d]=%d, target=%d\n", left, data[left], right, data[right], mid, data[mid], target);
        }
        
        if (data[right] < target) {
            return right;
        } else if (data[left] < target) {
            return left;
        } else {
            return -1;
        }
    }

}
