package bfs;

import java.util.ArrayDeque;
import java.util.Deque;

/**Find the least steps to get out the maze.
 * You can go left \ right \ up \ down at each step.
 * #S####
 * ...###
 * #..G#.
 * */
public class Maze {
	// 单源最短路径
	public int getLeastSteps(char[][] maze, int sx, int sy, int gx, int gy) {
		if (maze == null || maze.length == 0 || maze[0].length == 0) return 0;
		int m = maze.length, n = maze[0].length;
		int[][] d = new int[m][n]; // d[x][y], distance between (sx, sy) to (x, y).
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				d[i][j] = Integer.MAX_VALUE;
			}
		}
		
		Deque<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(sx * n + sy);
		d[sx][sy] = 0;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			int x = curr / n, y = curr % n;
			if (x == gx && y == gy) return d[gx][gy];
			for (int k = 0; k < dx.length; k++) {
				int x2 = x + dx[k], y2 = y + dy[k];
				if (x2 >= 0 && x2 < m && y2 >= 0 && y2 < n && maze[x2][y2] != '#' && d[x2][y2] == Integer.MAX_VALUE) {
					d[x2][y2] = d[x][y] + 1;
					queue.offer(x2 * n + y2);
				}
			}
		}
		return d[gx][gy];
	}
	int[] dx = {0, 0, 1, -1};
	int[] dy = {1, -1, 0, 0};
}
