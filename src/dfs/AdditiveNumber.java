package dfs;

import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

public class AdditiveNumber {

    @Test
    public void testAdditiveNumber() {
        AdditiveNumber obj = new AdditiveNumber();
//        Assert.assertEquals(false, obj.isAdditiveNumber("120122436"));
//        Assert.assertEquals(true, obj.isAdditiveNumber("101"));
//        Assert.assertEquals(true, obj.isAdditiveNumber("011"));
//        Assert.assertEquals(true, obj.isAdditiveNumber("12012122436"));
//        Assert.assertEquals(true, obj.isAdditiveNumber("121474836472147483648"));
//        
//        Assert.assertEquals(true, obj.isAdditiveNumber2("121474836472147483648"));
        
        Assert.assertEquals(false, obj.isAdditiveNumber3("120122436"));
        Assert.assertEquals(true, obj.isAdditiveNumber3("101"));
        Assert.assertEquals(true, obj.isAdditiveNumber3("011"));
        Assert.assertEquals(true, obj.isAdditiveNumber3("12012122436"));
        Assert.assertEquals(true, obj.isAdditiveNumber3("121474836472147483648"));
    }
    
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 3) return false;
        int numOfDigits = num.length();
        int maxDigitsInNum = (numOfDigits - 1) / 2;
        for (int i = 1; i <= maxDigitsInNum; i++) {
            if (i > 1 && num.charAt(0) == '0') { // Numbers in the additive sequence cannot have leading zeros.
                return false;
            }
            long num1 = Long.parseLong(num.substring(0, i));
            for (int j = 1; j <= maxDigitsInNum && (numOfDigits - (i + j)) >= Math.max(i, j); j++) {
                if (j > 1 && num.charAt(i) == '0') {
                    break; // Numbers in the additive sequence cannot have leading zeros.
                }
                long num2 = Long.parseLong(num.substring(i, i + j));
                long num3 = num1 + num2;
                String num3Str = String.valueOf(num3);
                if (i + j + num3Str.length() > numOfDigits) { //Avoid IndexOfBound exception.
                    break;
                }
                if (num3Str.equals(num.substring(i + j, i + j + num3Str.length()))) {
                    if (dfs(num, i + j + num3Str.length(), num2, num3)) return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(String num, int start, long num1, long num2) {
        if (start == num.length()) return true;
        else if (start > num.length()) return false;
        long num3 = num1 + num2;
        String num3Str = String.valueOf(num3);
        if (start + num3Str.length() > num.length()) return false;
        if (num3Str.equals(num.substring(start, start + num3Str.length()))) {
            if (dfs(num, start + num3Str.length(), num2, num3)) return true;
        }
        return false;
    }
    
    // This elegant version refers other.
    public boolean isAdditiveNumber3(String num) {
        if (num == null) return false;
        int n = num.length();
        int maxDigitsInOneNum = n / 2;
        for (int i = 1; i <= maxDigitsInOneNum; i++) {
            if (i > 1 && num.charAt(0) == '0') return false;
            long num1 = Long.parseLong(num.substring(0, i));
            for (int j = 1; (n - i - j) >= Math.max(i, j); j++) {
                if (j > 1 && num.charAt(i) == '0') break;
                long num2 = Long.parseLong(num.substring(i, i + j));
                if (isValid2(num, i + j, num1, num2)) {
                    return true;
                }
            }
        }
        return false;
    }
    // Recursive
    private boolean isValid(String num, int start, long num1, long num2) {
        if (start == num.length()) return true;
        long num3 = num1 + num2;
        String num3Str = String.valueOf(num3);
        return (num.startsWith(num3Str, start) && isValid(num, start + num3Str.length(), num2, num3));
    } 
    // Iterative
    private boolean isValid2(String num, int start, long num1, long num2) { 
    	long num3 = 0;
    	String num3Str = "";
    	int i = start;
    	for (; i < num.length(); i += num3Str.length()) {
    		num3 = num1 + num2;
    		num3Str = String.valueOf(num3);
    		if (!num.startsWith(num3Str, i)) return false;
    		num1 = num2;
    		num2 = num3;
    	}
    	return i == num.length();
    }
    
    // Follow up: How would you handle overflow for very large input integers?
    // -->Use BigInteger, cost is lower performance.
    public boolean isAdditiveNumber2(String num) {
        if (num == null || num.length() < 3) return false;
        int numOfDigits = num.length();
        int maxDigitsInNum = (numOfDigits - 1) / 2;
        for (int i = 1; i <= maxDigitsInNum; i++) {
            if (i > 1 && num.charAt(0) == '0') { // Numbers in the additive sequence cannot have leading zeros.
                return false;
            }
            BigInteger num1 = new BigInteger(num.substring(0, i));
            for (int j = 1; j <= maxDigitsInNum && (numOfDigits - (i + j)) >= Math.max(i, j); j++) {
                if (j > 1 && num.charAt(i) == '0') {
                    break; // Numbers in the additive sequence cannot have leading zeros.
                }
                BigInteger num2 = new BigInteger(num.substring(i, i + j));
                BigInteger num3 = num1.add(num2);
                String num3Str = num3.toString();
                if (i + j + num3Str.length() > numOfDigits) { //Avoid IndexOfBound exception.
                    break;
                }
                if (num3Str.equals(num.substring(i + j, i + j + num3Str.length()))) {
                    if (dfs2(num, i + j + num3Str.length(), num2, num3)) return true;
                }
            }
        }
        return false;
    }
    private boolean dfs2(String num, int start, BigInteger num1, BigInteger num2) {
        if (start == num.length()) return true;
        else if (start > num.length()) return false;
        BigInteger num3 = num1.add(num2);
        String num3Str = num3.toString();
        if (start + num3Str.length() > num.length()) return false;
        if (num3Str.equals(num.substring(start, start + num3Str.length()))) {
            if (dfs2(num, start + num3Str.length(), num2, num3)) return true;
        }
        return false;
    }
}
