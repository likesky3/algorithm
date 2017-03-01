package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeSortV2 {
	
	public static void main(String[] args) {
		int[] nums = {5, 2, 6, 1};
		MergeSortV2 obj = new MergeSortV2();
		List<Integer> ret = obj.countSmaller(nums);
		for (int n : ret) {
			System.out.println(n);
		}
	}

	public List<Integer> countSmaller(int[] nums) {
		List<Integer> result = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return result;
		}
		Node[] nodes = new Node[nums.length];
		for (int i = 0; i < nums.length; i++) {
			nodes[i] = new Node(nums[i], i);
		}
		Node[] helper = new Node[nums.length];
		countWhileMergeSort(nodes, 0, nodes.length - 1, helper);
		Arrays.sort(nodes, new Comparator<Node>() {
			public int compare(Node a, Node b) {
				return a.idx - b.idx;
			}
		});
		for (Node node : nodes) {
			result.add(node.cnt);
		}
		return result;
	}
	
	private void countWhileMergeSort(Node[] nodes, int left, int right, Node[] helper) {
		if (left >= right) {
			return;
		}
		int mid = left + (right - left) / 2;
		countWhileMergeSort(nodes, left, mid, helper);
		countWhileMergeSort(nodes, mid + 1, right, helper);
		merge(nodes, left, mid, right, helper);
	}

	private void merge(Node[] nodes, int left, int mid, int right, Node[] helper) {
		System.arraycopy(nodes, left, helper, left, right - left + 1);
		int i = left, j = mid + 1, k = left;
		while (i <= mid && j <= right) {
			if (helper[i].val <= helper[j].val) {
				helper[i].cnt += j - 1 - mid; // specail attention, not update nodes[i].cnt
				nodes[k++] = helper[i++];
			} else {
				nodes[k++] = helper[j++];
			}
		}
		while (i <= mid) {
			helper[i].cnt += right - mid;  // attention! Don't update helper[i].cnt
			nodes[k++] = helper[i++];
		}
	}

	static class Node {
		int val;
		int idx;
		int cnt;

		public Node(int val, int idx) {
			this.val = val;
			this.idx = idx;
		}
	}
	
	private void print(Node[] nodes) {
		for (Node node : nodes) {
			System.out.printf("val=%d, idx=%d, cnt=%d\n",node.val, node.idx, node.cnt);
		}
	}

}
