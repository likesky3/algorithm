package heap;

import java.util.*;
public class UlgyNumbers {
	public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int k = primes.length;
        int[] idx = new int[k];
        int[] groupHead = new int[k];
        for (int i = 0; i < k; i++) {
            groupHead[i] = primes[i];
        }
        for (int i = 1; i < n; i++) {
            int min = groupHead[0];
            for (int j = 1; j < k; j++) {
                min = Math.min(groupHead[j], min);
            }
            ugly[i] = min;
            for (int j = 0; j < k; j++) {
                if (groupHead[j] == min) {
                    groupHead[j] = ugly[++idx[j]] * primes[j];
                }
            }
        }
        return ugly[n - 1];
    }
	
	public int nthSuperUglyNumber2(int n, int[] primes) {
		int[] ugly = new int[n];
		int k = primes.length;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < k; i++) { // O(klogk)
			pq.offer(new Node(primes[i], 0, primes[i]));
		}
		ugly[0] = 1;
		for (int i = 1; i < n; i++) {
			Node next = pq.poll(); // O(nlogk)
			ugly[i] = next.val;
			pq.offer(new Node(next.prime * ugly[next.idx + 1], next.idx + 1, next.prime)); // O(nlogk)
			while (pq.peek().val == ugly[i]) { // ?
				next = pq.poll();
				pq.offer(new Node(next.prime * ugly[next.idx + 1], next.idx + 1, next.prime));
			}
		}
		return ugly[n - 1];
	}
	
	private static class Node implements Comparable<Node> {
		int val;
		int idx;
		int prime;
		public Node(int val, int idx, int prime) {
			this.val = val;
			this.idx = idx;
			this.prime = prime;
		}
		public Node(int prime, int idx) {
			this.prime = prime;
			this.idx = idx;
		}
		@Override
		public int compareTo(Node o) {
			return this.val - o.val;
		}
		
	}
	
    // time: O(nlogk)
    // space: O(n + k)
    public int nthSuperUglyNumber3(int n, int[] primes) {
        int[] uglys = new int[n];
        uglys[0] = 1;
        Set<Long> uglysSet = new HashSet<>();
        PriorityQueue<Node> minHeap = new PriorityQueue<>(n * 2, new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                long aVal = (long)a.prime * uglys[a.idx];
                long bVal = (long)b.prime * uglys[b.idx];
                return (int)(aVal - bVal);
            }
        });
        
        for (int i = 0; i < primes.length; i++) { // O(klogk)
            minHeap.offer(new Node(primes[i], 0));
        }
        for (int i = 1; i < n; i++) { 
            Node curr = minHeap.poll(); // O(nlogk)
            uglys[i] = curr.prime * uglys[curr.idx];
            int newIdx = curr.idx + 1;
            while (!uglysSet.add((long)curr.prime * uglys[newIdx])) { // ?
                newIdx++;
            }
            minHeap.offer(new Node(curr.prime, newIdx)); // O(nlogk)
        }
        return uglys[n - 1];
    }
	
	
	public static void main(String[] args) {
		UlgyNumbers obj = new UlgyNumbers();
		int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 57, 59, 61, 63, 65, 67, 71, 73, 79};
		int cost1 = 0, cost2 = 0, cost3 = 0;
		for (int i = 0; i < 100; i++) {
			long start = System.currentTimeMillis();
			int a = obj.nthSuperUglyNumber(1000, primes);
			cost1 += System.currentTimeMillis() - start;
			
			start = System.currentTimeMillis();
			int b= obj.nthSuperUglyNumber2(1000, primes);
			cost2 += System.currentTimeMillis() - start;
			
			start = System.currentTimeMillis();
			int c= obj.nthSuperUglyNumber2(1000, primes);
			cost3 += System.currentTimeMillis() - start;
			
			if (a != b) {
				System.out.println("wrong");
			}
		}
		System.out.printf("cost1=%d, cost2=%d, cost3=%d\n", cost1, cost2, cost3);
	}

}
