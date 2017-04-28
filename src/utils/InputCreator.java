package utils;

public class InputCreator {

	public static int[] crateIntArray(int length, int max) {
		int[] nums = new int[length];
		for (int i = 0; i < length; i++) {
			nums[i] = (int)(Math.random() * max);
			if (Math.random() < 0.5) {
				nums[i] = -nums[i];
			}
		}
		return nums;
	}
	
	public static int[] crateIntArrayPos(int length, int max) {
		int[] nums = new int[length];
		for (int i = 0; i < length; i++) {
			nums[i] = (int)(Math.random() * max);
		}
		return nums;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
