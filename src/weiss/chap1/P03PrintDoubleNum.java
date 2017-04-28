package weiss.chap1;

/**
 * in computer, 64 bit double, 1 bit is sign bit, 11 bit is exponential bits, 52 is effective bits
 * number of effective bits of double is 52, in decimal world the max numbef of effective digit is 15
 * so we can print 123456789.123456 "precisely", but we can not print "123456789.12345678" precisely.
 * 
 * */
public class P03PrintDoubleNum {
	private static final double epsilon = 1e-12;
	public void printDoubleNum(double num) {
		if (num < 0) {
			System.out.print("-");
			num = -num;
		}
		double base = 1;
		while (num / base >= 10) { //>< num >= base * 10 has overflow risk
			base *= 10;
		}
		
		while (num > 0) {
			int digit = (int)(num / base);
			
			// epsilon is not the smaller the "better", e.g. we want 
			if (Math.abs(num - (digit + 1) * base) < epsilon) { // >< without this check, for 1.23 we will get 1.22999... 
				digit++;
			}
			printDigit(digit);
			num -= digit * base;
//			System.out.println("\n" + num);
			if (Math.abs(base - 1) < 1e-6) {
				System.out.print(".");
			}
			base /= 10;
		}
		System.out.println();
	}
	
	public void printDoubleNum1(double num) {
		if (num < 0) {
			System.out.print("-");
			num = -num;
		}
		printIntegerPart((long)num);
		System.out.print(".");
		if (num - (long)num > 0) {
			printDecimalPart(num, (long)num);
		} else {
			printDigit(0);
		}
	}
	
	private void printIntegerPart(long num) {
		if (num > 10) {
			printIntegerPart(num / 10);
		}
		printDigit((int)(num % 10)); // num % 10 == num - num / 10 * 10;
	}
	
	
	private void printDecimalPart(double num, long integerPart) {
		int power = 10;
		while ((long)(num * power) != num * power) {
			power *= 10;
		}
		printIntegerPart((long)(num * power) - integerPart * power);
//		System.out.println("\n" + num + " power=" + power);
	}
	
	private void printDigit(int num) {
		if (num >= 0 && num < 10) {
			System.out.print(num);
		} else {
			System.out.println("printDigit() will only print digit from 0 to 9");
		}
	}
	
	public static void main(String[] args) {
		P03PrintDoubleNum obj = new P03PrintDoubleNum();
//		obj.printDoubleNum(123456789.123456); 
//		obj.printDoubleNum(1.23);
		obj.printDoubleNum(0.000000123456789);
//		System.out.println();
//		obj.printDoubleNum(123456.12345); 
	}
}
