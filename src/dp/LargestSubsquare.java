package dp;

public class LargestSubsquare {

	public static void main(String[] args) {
		LargestSubsquare obj = new LargestSubsquare();
		int[][] matrix = {{1, 1, 1, 1, 1}, {1, 0, 0, 1, 1}, {1, 0, 0, 1, 1}, {1, 1, 1, 1, 1}, {0, 1, 1, 1, 1}};
		System.out.println(obj.getLengthOfLargestSubsquare(matrix));
	}
	
	// idea: right[i][j] records number of continual 1 from [i,j] to [i, cols - 1]
	// down[i][j] records number of continual 1 from [i, j] to [rows - 1, j]
	// base case: most right column, right[i][cols - 1] = matrix[i][cols - 1]
	// induction rule: if matrix[i][j] == 0, right[i][j] = 0, else right[i][j] = right[i][j + 1] + 1;
	// down[i][j] is similar
	// check 3 points
	// Time: O(n^3), Space: O(n^2)
	public int getLengthOfLargestSubsquare(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] right = new int[rows][cols];
		int[][] down = new int[rows][cols];
		calculate(matrix, right, down);
		int maxLen = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				for (int k = Math.min(right[i][j], down[i][j]); k > 0; k--) {
					if (down[i][j + k - 1] >= k && right[i + k - 1][j] >= k) {
						maxLen = Math.max(maxLen, k);
						break;
					}
				}
			}
		}
		return maxLen;
	}
	
	private void calculate(int[][] matrix, int[][] right, int[][] down) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		for (int j = cols - 1; j >= 0; j--) { // calculate the last row
			down[rows - 1][j] = matrix[rows - 1][j];
		}
		right[rows - 1][cols - 1] = matrix[rows - 1][cols - 1];
		for (int i = rows - 2; i >= 0; i--) {
			right[i][cols - 1] = matrix[i][cols - 1]; // calculate the last col
			down[i][cols - 1] = matrix[i][cols - 1] == 1 ? down[i + 1][cols - 1] + 1 : 0;
			for (int j = cols - 2; j >= 0; j--) {
				right[i][j] = matrix[i][j] == 1 ? right[i][j + 1] + 1 : 0;
				down[i][j] = matrix[i][j] == 1 ? down[i + 1][j] + 1 : 0;
			}
		}
	}

}
