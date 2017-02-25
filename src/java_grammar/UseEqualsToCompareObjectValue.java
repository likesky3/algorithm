package java_grammar;

import utils.WhiteBoard;

/** Never use == to compare Object's value
 *  Always use equals()
 * */
public class UseEqualsToCompareObjectValue {
	public static void main(String[] args) {
		Integer a = Integer.MIN_VALUE;
		System.out.println(a == Integer.MIN_VALUE);
		System.out.println(a == -2147483648);
		System.out.println(a.equals(-2147483648));
//		a = 1;
//		System.out.println(a == 1);
//		System.out.println(a.equals(1));
		
		UseEqualsToCompareObjectValue obj = new UseEqualsToCompareObjectValue();
		Integer[] nums = {-2147483648,-2147483648,-2147483648,-2147483648,1,1,1};
		System.out.println(a == nums[0]); // false
		int[] nums2 = {-2147483648,-2147483648,-2147483648,-2147483648,1,1,1};
		System.out.println(nums2[0] == a); // true
//		System.out.println(obj.thirdMax(nums));
	}
	
	public int thirdMax(int[] nums) {
        Integer max1 = null, max2 = null, max3 = null;
        for (Integer n : nums) {
//            if (n == max1 || n == max2 || n == max3) {
             if (n.equals(max1) || n.equals(max2) || n.equals(max3)) {
                continue;
            }
//            System.out.printf("n=%d, max1=%d, max2=%d, max3=%d\n", n, max1 == null ? 0 : max1, max2 == null ? 0 : max2,max3 == null ? 0 : max3);
            if (max1 == null || n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (max2 == null || n > max2) {
                max3 = max2;
                max2 = n;
            } else if (max3 == null || n > max3) {
                max3 = n;
            }
            // System.out.printf("n=%d, max1=%d, max2=%d, max3=%d\n", n, max1 == null ? 0 : max1, max2 == null ? 0 : max2,
            // max3 == null ? 0 : max3);
        }
        return max3 == null ? max1 : max3;
    }
}
