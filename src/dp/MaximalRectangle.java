package dp;

public class MaximalRectangle {
	public static void main(String[] args) {
		MaximalRectangle obj = new MaximalRectangle();
		char[][] matrix = new char[1][1];
		matrix[0][0] = '1';
		System.out.println(obj.maximalRectangle(matrix));
		
	}
	public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int max = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        System.out.println(rows + " " + cols);
        int[][][] dp = new int[rows][cols][2];
        for (int i = 0; i < rows; i++) {
        	System.out.println("for1: " + i);
            for (int j = 0; j < cols; j++) {
            	System.out.println("for2: " + j);
                if (matrix[i][j] == '1') {
                    if (j > 0) {
                        dp[i][j][0] = matrix[i][j - 1] == 1 ? (dp[i][j - 1][0] + 1) : 0;
                        System.out.println("if j > 0: " + j);
                    } else {
                        dp[i][j][0] = 1;
                        System.out.println("dp[i][j][0] =" + dp[i][j][0] );
                    }
                    if (i > 0) {
                        dp[i][j][1] = matrix[i - 1][j] == 1 ? (dp[i - 1][j][1] + 1) : 0;
                        System.out.println("if i > 0: " + i);
                    }
                    else {
                        dp[i][j][1] = 1;
                        System.out.println("dp[i][j][1] =" + dp[i][j][1] );
                    }
                    max = Math.max(max, dp[i][j][0] * dp[i][j][1]);
                } else {
                	System.out.println("yi?");
                }
            }
        }
        return max;
    }
}
