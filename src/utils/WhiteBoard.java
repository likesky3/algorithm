package utils;

import java.util.*;

public class WhiteBoard {
	public static void main(String[] args) throws Exception {
		WhiteBoard obj = new WhiteBoard();
		Solution test = new Solution();
		int[][] people = { { 7, 0 }, { 4, 4 }, { 7, 1 }, { 5, 0 }, { 6, 1 }, { 5, 2 } };
		int[] nums = { 0, 0, 0, 1, 9 };
		System.out.println(test.decompress("a0b1c2d3e4"));

	}

	public static void print(int[] array) {
		for (int n : array) {
			System.out.print(n + ",");
		}
		System.out.println();
	}

	public static void print(char[] array) {
		for (char n : array) {
			System.out.print(n + ",");
		}
		System.out.println();
	}

	public static void print(Iterable<Integer> list) {
		for (Integer n : list) {
			System.out.print(n + " ");
		}
		System.out.println();
	}
}

class Solution {
	  public String decompress(String input) {
	    if (input.length() == 0) {
	      return input;
	    }
	    char[] array = input.toCharArray();
	    return decodeLong(array, decodeShort(array));
	  }
	  
	  private int decodeShort(char[] array) {
	    int end = 0;
	    for (int i = 1; i < array.length; i += 2) {
	      int digit = array[i] - '0';
	      if (digit <= 2) {
	        for (int cnt = 0; cnt < digit; cnt++) {
	          array[end++] = array[i - 1];
	        }
	      } else {
	        array[end++] = array[i - 1];
	        array[end++] = array[i];
	      }
	    }
	    return end;
	  }
	  
	  private String decodeLong(char[] array, int length) {
	    int newLen = length;
	    for (int i = 0; i < length; i++) {
	      int digit = array[i] - '0';
	      if (2 < digit && digit <= 9) {
	        newLen += digit - 2;
	      }
	    }
	    if (newLen == length) {
	      return new String(array, 0, length);
	    }
	    
	    char[] result = new char[newLen];
	    // from right to left
	    int slow = newLen - 1;
	    int fast = length - 1;
	    while (fast >= 0) {
	      int digit = array[fast] - '0';
	      if (digit > 2 && digit <= 9) {
	        char c = array[fast - 1];
	        for (int i = 0; i < digit; i++) {
	          result[slow--] = c;
	        }
	        fast -= 2;
	      } else {
	        result[slow--] = array[fast--];
	      }
	    }
	    return new String(result);
	  }
	  
	}
