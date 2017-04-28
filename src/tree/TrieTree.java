package tree;

public class TrieTree {
	public static class TrieTreeNode {
		private char key;
		private TrieTreeNode[] children;
		private boolean isWord;
		public TrieTreeNode(char key) {
			this.key = key;
			children = new TrieTreeNode[26];
			isWord = false;
		}
		
		public boolean isWord() {
			return isWord;
		}
	}
	
	TrieTreeNode root;

	public TrieTree() {
		root = new TrieTreeNode('0');
	}

	public boolean insertAWord(String word) {
		TrieTreeNode curr = root;
		for (char c : word.toCharArray()) {
			if (curr.children[c - 'a'] == null) {
				curr.children[c - 'a'] = new TrieTreeNode(c);
			}
			curr = curr.children[c - 'a'];
		}
		if (curr.isWord) {
			return false;
		} else {
			curr.isWord = true;
			return true;
		}
	}

	private TrieTreeNode containsPrefixInner(String prefix) {
		TrieTreeNode curr = root;
		for (char c : prefix.toCharArray()) {
			if (curr.children[c - 'a'] == null) {
				return null;
			}
			curr = curr.children[c - 'a'];
		}
		return curr;
	}

	public boolean containsPrefix(String prefix) {
		return containsPrefixInner(prefix) != null;
	}

	public boolean containsWord(String word) {
		TrieTreeNode last = containsPrefixInner(word);
		return last != null && last.isWord;
	}

	public TrieTreeNode getNextNode(TrieTreeNode last, char c) {
		TrieTreeNode curr = last == null ? root : last;
		
		if (curr.children[c - 'a'] == null) {
			return null;
		}
		curr = curr.children[c - 'a'];
		return curr;
	}
}