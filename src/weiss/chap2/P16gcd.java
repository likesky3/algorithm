package weiss.chap2;

public class P16gcd {

	public static int gcd(int a, int b) {
		if (a < b) {
			return gcd(b, a);
		}
		int rem = 0;
		while ((rem = a % b) > 0) {
			a = b;
			b = rem;
		}
		return b;
	}
	
	public static int gcd2(int a, int b) {
		if (a < b) {
			return gcd2(b, a);
		} else if (b == 0) {
			return a;
		}
		boolean aIsEven = (a & 1) == 0;
		boolean bIsEven = (b & 1) == 0;
		if (aIsEven && bIsEven) {
			return 2 * gcd2(a / 2, b / 2);
		} else if (aIsEven) {
			return gcd(a / 2, b);
		} else if (bIsEven) {
			return gcd(a, b / 2);
		} else {
			return gcd(b + (a - b) / 2, (a - b) / 2);
		}
	}
	 
	public static void main(String[] args) {
		long start = 0;
		int cost1 = 0;
		int cost2 = 0;
		for (int i = 0; i < 1000; i++) {
			int a = (int)(Math.random() * Integer.MAX_VALUE);
			int b = (int)(Math.random() * Integer.MAX_VALUE);
			start = System.currentTimeMillis();
			int gcd1 = gcd(a, b);
			cost1 += System.currentTimeMillis() - start;
			
			start = System.currentTimeMillis();
			int gcd2 = gcd2(a, b);
			cost2 += System.currentTimeMillis() - start;
			if (gcd1 != gcd2) {
				System.out.printf("a=%d, b=%d, gcd1=%d, gcd2=%d\n", a, b, gcd1, gcd2);
			}
		}
		System.out.printf("gcd1=%d, gcd2=%d\n", cost1, cost2);
		
	}

}
