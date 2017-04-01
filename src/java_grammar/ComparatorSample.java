package java_grammar;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ComparatorSample {
	static class Cell {
        int row;
        int col;
        int val;
        public Cell(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
//        Comparator<Cell> cellComp = new CellComparator<>();  // wrong
        Comparator cellComp = new CellComparator(); 
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(k + 1, cellComp);
        minHeap.offer(new Cell(0, 0, matrix[0][0]));
        visited[0][0] = true;
        for (int i = 0; i < k - 1; i++) {
            Cell curr = minHeap.poll();
            if (curr.row + 1 < rows && !visited[curr.row + 1][curr.col]) {
                minHeap.offer(new Cell(curr.row + 1, curr.col, matrix[curr.row + 1][curr.col]));
                visited[curr.row + 1][curr.col] = true;
            }
            if (curr.col + 1 < cols && !visited[curr.row][curr.col + 1]) {
                minHeap.offer(new Cell(curr.row, curr.col + 1, matrix[curr.row][curr.col + 1]));
                visited[curr.row][curr.col + 1] = true;
            }
        }
        return minHeap.poll().val;
    }
    
    class CellComparator implements Comparator<Cell> {
        @Override
        public int compare(Cell a, Cell b) {
            if (a.val == b.val) {
                return 0;
            }
            return a.val < b.val ? -1 : 1;
        }
    }
}
