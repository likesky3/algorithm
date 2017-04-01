package utils;

import java.util.*;

import design.ParkingLot;
import design.ParkingTicket;
import design.Vehicle;
import design.VehicleSize;

public class WhiteBoard {
	public static void main(String[] args) throws Exception {
		WhiteBoard obj = new WhiteBoard();
		Solution test = new Solution();
		ParkingLot lot = new ParkingLot(1, 10);
		Vehicle bossCar = new Vehicle(VehicleSize.COMPACT, false);
		Vehicle employeeCar = new Vehicle(VehicleSize.COMPACT, true);
		Vehicle truck = new Vehicle(VehicleSize.TRUCK, true);
		ParkingTicket t = null;
		if (lot.hasSpot(bossCar)) {
			t = lot.park(bossCar);
			if (t != null) {
				System.out.println("boss car, park succ");
			} else {
				System.out.println("boss car, park fail");
			}
		}
		if (lot.hasSpot(truck)) {
			lot.park(truck);
			if (t != null) {
				System.out.println("truck, park succ");
			} else {
				System.out.println("truck, park fail 1");
			}
		} else {
			System.out.println("truck, park fail 2");
		}
	}

	private static final int MAXIMUM_CAPACITY = 1 << 30;

	public static final int tableSizeFor(int cap) {
		int n = cap - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}

	public static final int[] test() {
		return new int[] { 1, 2 };
	}

	public static void test(String a, int b) {

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

interface InterfOne {
	int a = 0;

	default void f() {
		System.out.println("default implementation of interfOne.f()");
	}

	static int withReturn() {
		System.out.println("InterfOne.withReturn()");
		return 0;
	}
}

class ImpleOne implements InterfOne {

	public int withReturn() {
		System.out.println("ImpleOne.withReturn()");
		System.out.println("print field in interface" + a);
		System.out.println("print field in interface" + InterfOne.a);
		return a;
	}

}

class Solution {
}
