package weiss.chap1;

public class P05CountNumOfBitOne {

	public static int countBitOne(int n) {
		if (n == 0) {
			return 0;
		}
		
		int countInHalf = countBitOne(n >>> 1);
		return countInHalf + ((n & 1) == 1 ? 1 : 0);
	}
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			int num = (int)(Math.random() * 1000);
			if (countBitOne(num) != Integer.bitCount(num)) {
				System.out.printf("wrong answer @ %d: %d != %d(standard)\n ", num, countBitOne(num), Integer.bitCount(num));
			}
		}
		System.out.println(Integer.bitCount(-11));
		System.out.println(countBitOne(-11));
	}

}
