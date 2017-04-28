package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import utils.WhiteBoard;

public class FindPlaceToPutChair {
	static class Cell implements Comparable<Cell> {
		int x, y;
		List<Integer> dists;

		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
			dists = new ArrayList<>();
		}

		@Override
		public int compareTo(Cell b) {
			int last = dists.size() - 1;
			return dists.get(last) - b.dists.get(last);
		}
	}

	public List<Integer> putChair(char[][] gym) {
		int rows = gym.length;
		int cols = gym[0].length;
		Cell[][] g = new Cell[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				g[i][j] = new Cell(i, j);
			}
		}

		int equipCounts = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (gym[i][j] == 'E') {
					equipCounts++;
					if (!dijkstra(gym, g, i, j, equipCounts)) {
						return null;
					}
				}
			}
		}

		int minCost = Integer.MAX_VALUE;
		Cell minCell = null;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (gym[i][j] == 'O' || gym[i][j] == 'E') {
					continue;
				}
				int currCost = 0;
				Cell currPos = g[i][j];
				// check whether currPos is blocked by obstacles
				if (currPos.dists.size() < equipCounts) {
					continue;
				}
				for (int k = 0; k < equipCounts; k++) {
					currCost += currPos.dists.get(k);
				}
				if (currCost < minCost) {
					minCost = currCost;
					minCell = currPos;
				}
			}
		}
		if (minCell == null) {
			return null;
		}
		return Arrays.asList(minCell.x, minCell.y);
	}

	static final int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	private boolean dijkstra(char[][] gym, Cell[][] g, int i, int j, int equipCounts) {
		g[i][j].dists.add(0);
		boolean[][] visited = new boolean[g.length][g[0].length];
		PriorityQueue<Cell> minHeap = new PriorityQueue<>();
		minHeap.offer(g[i][j]);
		while (!minHeap.isEmpty()) {
			Cell curr = minHeap.poll();
			if (visited[curr.x][curr.y]) {
				continue;
			}
			visited[curr.x][curr.y] = true;
			int toCurrDist = curr.dists.get(curr.dists.size() - 1);
			for (int[] d : dirs) {
				int neighx = curr.x + d[0];
				int neighy = curr.y + d[1];
				if (neighx < 0 || neighx >= g.length || neighy < 0 || neighy >= g[0].length) {
					continue;
				}
				if (gym[neighx][neighy] == 'O') {
					continue;
				}
				Cell neigh = g[neighx][neighy];
				if (!visited[neighx][neighy]) {
					if (neigh.dists.size() < equipCounts) {
						neigh.dists.add(toCurrDist + 1);
						minHeap.offer(neigh);
					} else if (neigh.dists.get(equipCounts - 1) > toCurrDist + 1) {
						neigh.dists.set(neigh.dists.size() - 1, toCurrDist + 1);
						minHeap.offer(neigh);
					}
				}
			}
		}

		// check if there is some 'E' unreachable from curr 'E', if exists, no
		// solution
		return !existsUnreachableEuip(visited, gym);
	}

	private boolean existsUnreachableEuip(boolean[][] visited, char[][] gym) {
		for (int i = 0; i < gym.length; i++) {
			for (int j = 0; j < gym[0].length; j++) {
				if (gym[i][j] == 'E' && !visited[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		FindPlaceToPutChair obj = new FindPlaceToPutChair();
		// test case: some equip is wrapped by obstacles, some position is wrapped by obstacles
		char[][] gym = { { 'E', 'C', 'C' },
				{ 'O', 'E', 'C' },

				{ 'C', 'O', 'C' } };
		List<Integer> res = obj.putChair(gym);
		if (res == null) {
			System.out.println("null");
		} else {
			WhiteBoard.print(res);
		}
	}

}
