package string;

import utils.WhiteBoard;

public class EncodeDecode {

	public static void main(String[] args) {
		EncodeDecode obj = new EncodeDecode();
		System.out.println(obj.compress("abbcccdddeeee"));
	}
	
	// assumption: adjacent duplicate number <= 9
	public String compress(String input) {
		// step1: deal with dup >=2, shorter
		// step2: deal with dup == 1, longer
		char[] array = input.toCharArray();
		return encodeLong(array, encodeShorter(array));
	}
	
	private int encodeShorter(char[] array) {
		int slow = 0;
		int fast = 0;
		while (fast < array.length) {
			array[slow++] = array[fast++];
			int cnt = 1;
			while (fast < array.length && array[fast] == array[fast - 1]) {
				cnt++;
				fast++;
			}
			if (cnt > 1) {
				char[] cntArray = String.valueOf(cnt).toCharArray();
				for (int i = 0; i < cntArray.length; i++) {
					array[slow++] = cntArray[i];
				}
			}
		}
		return slow;
	}
	
	private String encodeLong(char[] array, int length) {
		int newLen = length;
		int i = 0;
		while (i < length) {
			if ( Character.isAlphabetic(array[i]) && (i == length - 1  || Character.isAlphabetic(array[i + 1]))) {
				newLen++;
			}
			i++;
		}
		if (newLen == length) {
			return new String(array, 0, length);
		}
		char[] result = new char[newLen];
		int slow = newLen - 1;
		int fast = length - 1;
		while (fast >= 0) {
			if (Character.isAlphabetic(array[fast]) && (fast == length - 1 || Character.isAlphabetic(array[fast + 1]))) { 
				result[slow--] = '1';
				result[slow--] = array[fast--];
			} else {
				result[slow--] = array[fast--];
			}
		}
		return new String(result);
	}
	
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
				array[end++] = array[i - 1]; // BUG: array[i],
				array[end++] = array[i]; // BUG: array[i + 1]
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
				result[slow--] = array[fast--]; //BUG: result[fast--]
			}
		}
		return new String(result);
	}

}
