package org.professional.dp;

public class _WhiteBoard {
	private enum InputEnum {
        invalid, spcae, sign, digit, dot, exponent
    }
	public static void main(String[] args) {
		System.out.println(InputEnum.invalid.ordinal());
	}
}
