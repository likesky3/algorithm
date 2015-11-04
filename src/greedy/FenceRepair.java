package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

// Huffman
public class FenceRepair {
	
	// Method 1: Wrong, idea: always cut to get the longest fence which has not been obtained.
	// Fail case: 6 3 4 8 6 9 
	public static void main1(String[] args) {
		Scanner s = new Scanner(System.in);
		List<Long> list = new ArrayList<Long>();
		int n = s.nextInt();
		long currLen = 0;
		for (int i = 0; i < n; i++) {
			list.add(s.nextLong());
			currLen += list.get(i);
		}
		long cost = 0;
		Collections.sort(list);
		for (int i = n - 1; i > 0; i--) {
			cost += currLen;
			currLen -= list.get(i);
		}
		System.out.println(cost);
	}
	
	// Method 2: Get runtime error in POJ, but pass 1000 random junit test.
	public static void main2(String[] args) {
		Scanner s = new Scanner(System.in);
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		int n = s.nextInt();
		for (int i = 0; i < n; i++) {
			pq.offer(s.nextLong());
		}
		long cost = 0;
		while (pq.size() > 1) {
			long sum = pq.poll() + pq.poll();
			cost += sum;
			pq.offer(sum);
		}
		System.out.println(cost);
	}
	
	public static void main3(String[] args) {
		Scanner s = new Scanner(System.in);
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		int n = s.nextInt();
		for (int i = 0; i < n; i++) {
			pq.offer(s.nextLong());
		}
		long cost = 0;
		while (n > 1) {
			long sum = pq.poll() + pq.poll();
			cost += sum;
			pq.offer(sum);
			n--;
		}
		System.out.println(cost);
	}
	
	public long getCost1(int[] fences) {
		Arrays.sort(fences);
		long cost = 0;
		long currLen = 0;
		for (int f : fences) {
			currLen += f;
		}
		for (int i = fences.length - 1; i > 0; i--) {
			cost += currLen;
			currLen -= fences[i];
		}
		return cost;
	}
	
	public long getCost2(int[] fences) {
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		int n = fences.length;
		for (int i = 0; i < n; i++) {
			pq.offer((long) fences[i]);
		}
		long cost = 0;
		while (pq.size() > 1) {
			long sum = pq.poll() + pq.poll();
			cost += sum;
			pq.offer(sum);
		}
		return cost;
	}
	
	public long getCost3(int[] fences) {
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		int n = fences.length;
		for (int i = 0; i < n; i++) {
			pq.offer((long) fences[i]);
		}
		long cost = 0;
		while (n > 1) {
			long sum = pq.poll() + pq.poll();
			cost += sum;
			pq.offer(sum);
			n--;
		}
		return cost;
	}
	
	@Test
	public void testFenceRepair() {
		FenceRepair obj = new FenceRepair();
		Random rand = new Random();
		for (int i = 0; i < 10000; i++) {
			int n = rand.nextInt(20000);
			int[] fences = new int[n];
			for (int j = 0; j < n; j++) {
				while (fences[j] == 0) {
					fences[j] = rand.nextInt(50000);
				}
			}
			Assert.assertEquals(obj.getCost2(fences), obj.getCost3(fences));
		}
		
		// Check the correctness of getCost1.
//		for (int i = 0; i < 10; i++) {
//			int n = rand.nextInt(10);
//			int[] fences = new int[n];
//			System.out.println("i=" + i);
//			for (int j = 0; j < n; j++) {
//				while (fences[j] == 0) {
//					fences[j] = rand.nextInt(10);
//				}
//				System.out.print(fences[j] + " ");
//			}
//			Assert.assertEquals(obj.getCost1(fences), obj.getCost2(fences));
//			System.out.println();
//		}
	}
}

