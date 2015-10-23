package org.basic.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordLadderII {

	public static void main(String[] args) {
		// "ta", "if", ["ts","sc","ph","ca","jr","hf","to","if","ha","is","io","cf","ta"]
		WordLadderII obj = new WordLadderII();
//		String[] array = {"ts","sc","ph","ca","jr","hf","to","if","ha","is","io","cf","ta"};
//		List<String> list = Arrays.asList(array);
//		Set<String> dict = new HashSet<>(list);
//		obj.findLadders("ta", "if", dict);
		
		String[] array = {"dot", "hot", "dog"};
		List<String> list = Arrays.asList(array);
		Set<String> dict = new HashSet<>(list);
		System.out.println("ladder=" + obj.ladderLength("hot", "dog", dict));
	}
	public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (wordList == null || beginWord == null || endWord == null)
            return 0;
        LinkedList<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        wordList.remove(beginWord);
        int currLevel = 1, nextLevel = 0;
        int n = beginWord.length();
        int ladder = 1;
        while (!queue.isEmpty()) {
            String word = queue.poll();
            System.out.println("queue poll(): " + word);
            char[] array = word.toCharArray();
            for (int i = 0; i < n; i++) {
            		
                for (char c = 'a'; c <= 'z'; c++) {
                    if (array[i] != c) {
                        array[i] = c;
                        String newWord = new String(array);
                        if (word.equals("dot"))
                        System.out.println(newWord + " ");
                        if (newWord.equals(endWord)) {
                            return ladder + 1;
                        }
                        if (wordList.contains(newWord)) {
//                        	System.out.println(newWord + " ");
                            queue.offer(newWord);
                            wordList.remove(newWord);
                            nextLevel++;
                        }
                    }
                }
            }
            currLevel--;
            if (currLevel == 0) {
                ladder++;
                currLevel = nextLevel;
                nextLevel = 0;
            }
        }
        return 0;
    }
	
	class StringWithLevel {
        String str;
        int level;
        public StringWithLevel(String str, int level) {
            this.str = str;
            this.level = level;
        }
    }
    
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<List<String>>();
        LinkedList<StringWithLevel> queue = new LinkedList<StringWithLevel>();
        queue.add(new StringWithLevel(end, 0));
        HashMap<String, ArrayList<String>> nextMap = new HashMap<String, ArrayList<String>>();
        HashSet<String> visited = new HashSet<String>();
        // HashSet<String> unvisited = new HashSet<String>(dict.size());
        // unvisited.addAll(dict);
        // unvisited.add(start);
        // unvisited.remove(end);
        dict.add(start);
        dict.remove(end);
        int currLevel = 0, preLevel = 0;
        int finalLevel = Integer.MAX_VALUE;
        int L = start.length();
        boolean found = false;
        while (!queue.isEmpty()) {
            StringWithLevel curr = queue.poll();
            String currStr = curr.str;
            currLevel = curr.level;
            if (currLevel > preLevel) {
            	System.out.println(" ----queue.size=" + queue.size() + " " + currStr);
                if (found) break;
                preLevel = currLevel;
                dict.removeAll(visited);
                visited.clear();
            }
            
            char[] currCharArray = currStr.toCharArray();
            for (int i = 0; i < L; i++) {
                char oldChar = currCharArray[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == oldChar) continue;
                    currCharArray[i] = c;
                    String newStr = new String(currCharArray);
                    if (dict.contains(newStr)) {
                    	
                        ArrayList<String> nextList = null;
                        if (nextMap.containsKey(newStr)) {
                            nextList = nextMap.get(newStr);
                        } else {
                            nextList = new ArrayList<String>();
                        }
                        nextList.add(currStr);
                        nextMap.put(newStr, nextList);
                        if (newStr.equals(start)) {
                        	System.out.println(currStr + " " + queue.size());
                            found = true;
                            break;
                        }
                        if (visited.add(newStr)) {
                        	System.out.print(newStr + (currLevel + 1) + " ");
                            queue.offer(new StringWithLevel(newStr, currLevel + 1)); // enqueue a new item of next level
                        }
                    }
                }
                currCharArray[i] = oldChar;
                if (found) break;
            }
        }
        if (found) {
            List<String> item = new ArrayList<String>();
            item.add(start);
            getPath(start, end, item, nextMap, result);
        }
        return result;
    }
    
    public void getPath(String start, String end, List<String> item, 
        HashMap<String, ArrayList<String>> nextMap, List<List<String>> result) {
        if (start.equals(end)) {
            result.add(new ArrayList<String>(item));
        } else {
            ArrayList<String> nextList = nextMap.get(start);
            for (String next : nextList) {
                item.add(next);
                getPath(next, end, item, nextMap, result);
                item.remove(item.size() - 1);
            }
        }
    }

}
