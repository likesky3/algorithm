package weiss.chap1;

import java.io.*;
import java.util.*;

import tree.TrieTree;
import utils.WhiteBoard;

public class P02WordPuzzle {
	public static void main(String[] args) {
		P02WordPuzzle obj = new P02WordPuzzle();
		TrieTree dict = obj.createDictFromFile("/Users/linlinzhou/Downloads/words.txt");
		long cost1 = 0;
		long cost2 = 0;
		for (int i = 0; i < 100; i++) {
			char[][] matrix = obj.createRandomPuzzleBoard(16, 16);
			// char[][] matrix = {{'t', 'h', 'a', 't'}, {'m', 'i', 'n', 'e'},
			// {'h', 'e', 'l', 'l'}, {'w', 'o', 'r', 'd'}};
			long start = System.currentTimeMillis();
			Set<String> result1 = obj.solveWordPuzzle(matrix, dict);
			cost1 += System.currentTimeMillis() - start;
			start = System.currentTimeMillis();
			Set<String> result2 = obj.solveWordPuzzleSlower(matrix, dict);
			cost2 += System.currentTimeMillis() - start;
			if (result1.size() != result2.size()) {
				System.out.println("not equals");
			}
			for (String w : result1) {
				if (result2.contains(w)) {
					result2.remove(w);
				} else {
					System.out.println("diff: " + w);
				}
			}
			if (result2.isEmpty()) {
				System.out.println("equals");
			}
		}
		System.out.printf("cost1=%d, cost2=%d\n", cost1, cost2);
	}
	
	
	private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
	public Set<String> solveWordPuzzle(char[][] matrix, TrieTree dict) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		Set<String> result = new HashSet<>();
		int extendLimit = Math.max(rows, cols);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				boolean visitedFirstChar = false;
				for (int[] d : dirs) {
					sb.setLength(0);
					sb.append(matrix[i][j]);
					TrieTree.TrieTreeNode last = dict.getNextNode(null, matrix[i][j]);
					if (!visitedFirstChar && last.isWord()) {
						result.add(sb.toString());
					}
					for (int k = 1; k < extendLimit; k++) {
						int r = i + k * d[0];
						int c = j + k * d[1];
						if (r < 0 || r >= rows || c < 0 || c >= cols) {
							break;
						}
						sb.append(matrix[r][c]);
						last = dict.getNextNode(last, matrix[r][c]);
						if (last == null) {
							break;
						} else if (last.isWord()) {
							result.add(sb.toString());
						}
					}
				}
			}
		}
		return result;
	}
	
	public Set<String> solveWordPuzzleSlower(char[][] matrix, TrieTree dict) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		Set<String> result = new HashSet<>();
		int extendLimit = Math.max(rows, cols);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				boolean visitedFirstChar = false;
				for (int[] d : dirs) {
					sb.setLength(0);
					sb.append(matrix[i][j]);
					if (!visitedFirstChar && dict.containsWord(sb.toString())) {
						result.add(sb.toString());
					}
					for (int k = 1; k < extendLimit; k++) {
						int r = i + k * d[0];
						int c = j + k * d[1];
						if (r < 0 || r >= rows || c < 0 || c >= cols) {
							break;
						}
						sb.append(matrix[r][c]);
						String cand = sb.toString();
						if (dict.containsWord(cand)) {
							result.add(cand);
						} else if (!dict.containsPrefix(cand)) {
							break;
						}
					}
				}
			}
		}
		return result;
	}
	
	private char[][] createRandomPuzzleBoard(int rows, int cols) {
		char[][] m = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				m[i][j] = (char)((int)(Math.random() * 26) + 'a');
			}
		}
		return m;
	}
	
	private TrieTree createDictFromFile(String path) {
		TrieTree dict = new TrieTree();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		} catch (FileNotFoundException  fnfe) {
			fnfe.printStackTrace();
		}

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				dict.insertAWord(line.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dict;
	}
	
	private void test(TrieTree dict) {
		String[] words = {"hello", "world", "think", "way", "that"};
		for (String w : words) {
			System.out.println(dict.containsWord(w));
		}
	}
}
