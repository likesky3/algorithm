package string;

public class RabinKarp {

	public static void main(String[] args) {
		RabinKarp obj = new RabinKarp();
		System.out.println(obj.strstr("abc", "bc"));
		System.out.println(obj.strstr("mississipip", "issip"));
	}
	
	//  assumption: large and small are not null
	public int strstr(String large, String small) {
		if (large.length() < small.length()) {
			return -1;
		}
		// we need a prime as module end
		int largePrime = 101;
		// we also need a small prime to calculate hash value of string,
		// since the charset would be very large, e.g. 1,112,064 in UTF, we can not use that number
		int smallPrime = 31; 
		int seed = 1;
		int targetHash = small.charAt(0) % largePrime;
		for (int i = 1; i < small.length(); i++) {
			seed = moduleHash(seed, 0, smallPrime, largePrime);
			targetHash = moduleHash(targetHash, small.charAt(i), smallPrime, largePrime);
		}
		int hash = 0;
		for (int i = 0; i < small.length(); i++) {
			hash = moduleHash(hash, large.charAt(i), smallPrime, largePrime);
		}
		if (hash == targetHash && equals(large, 0, small)) {
			return 0;
		}
		for (int i = 1; i + small.length() <= large.length(); i++) {
			hash = nonNegative(hash, large.charAt(i - 1), seed, smallPrime, largePrime);
			hash = moduleHash(hash, large.charAt(i + small.length() - 1), smallPrime, largePrime);
			if (hash == targetHash && equals(large, i, small)) {
				return i;
			}
		}
		return -1;
	}

	private int moduleHash(int hash, int addition, int smallPrime, int largePrime) {
		return (hash * smallPrime  % largePrime + addition) % largePrime;
	}
	
	private boolean equals(String large, int offset, String small) {
		for (int i = 0; i < small.length(); i++) {
			if (small.charAt(i) != large.charAt(offset + i)) {
				return false;
			}
		}
		return true;
	}
	
	private int nonNegative(int hash, int deletedItem, int seed, int smallPrime, int largePrime) {
		int res = hash - deletedItem * seed % largePrime;
		if (res < 0) {
			res += largePrime;
		}
		return res;
	}
}
