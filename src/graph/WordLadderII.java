package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordLadderII {
    // step 1: BFS to get prevMap
    // step 2: use prevMap to get result
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (beginWord == null || endWord == null || wordList == null) return result;
        wordList.remove(beginWord);
        wordList.add(endWord);
        Map<String, List<String>> prevMap = new HashMap<String, List<String>>();
        Deque<String> queue = new ArrayDeque<String>();
        queue.offer(beginWord);
        int currLevelSize = 0, nextLevelSize = 1; // number of words of each level
        Set<String> visited = new HashSet<String>();
        int wl = beginWord.length();
        boolean finish = false;
        while (!queue.isEmpty()) {
            if (currLevelSize == 0) { // preprocess work before a new level
                if (finish) break;
                currLevelSize = nextLevelSize;
                nextLevelSize = 0;
                wordList.removeAll(visited);
                visited.clear();
            }
            String word = queue.poll();
            currLevelSize--;
            char[] array = word.toCharArray();
            boolean finishCurrWord = false;
            for (int i = 0; i < wl; i++) {
                char oldChar = word.charAt(i);
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == oldChar) continue;
                    array[i] = c;
                    String newWord = new String(array);
                    if (wordList.contains(newWord)) {
                        // update newWord in prevMap
                        if (!prevMap.containsKey(newWord)) {
                            prevMap.put(newWord, new ArrayList<String>());
                        }
                        prevMap.get(newWord).add(word);
                        // check if we come to the endWord
                        if (endWord.equals(newWord)) {
                            finishCurrWord = true;
                            finish = true;
                            break;
                        }
                        // avoid duplicate
                        if (visited.add(newWord)) {
                            queue.offer(newWord);
                            nextLevelSize++;
                        }
                    }
                } // end change curr pos from 'a' to 'z'
                if (finishCurrWord) break;
                array[i] = oldChar;
            } // end iteration from the first char to last char of the current word 
        } // end while
        if (!finish) return result;
        // build ladder using prevMap
        backtrace(beginWord, endWord, prevMap, new LinkedList<String>(), result);
        return result;
    }
    private void backtrace(String beginWord, String curr, Map<String, List<String>> prevMap, 
        List<String> item, List<List<String>> result) {
        item.add(0, curr);
        if (curr.equals(beginWord)) {
            result.add(new ArrayList<String>(item));
        } else {
            for (String prev : prevMap.get(curr)) {
                backtrace(beginWord, prev, prevMap, item, result);
            }            
        }
        item.remove(0);
    }
}

/**
visited是当前这层bfs遍历到的单词，一层遍历完，从dict中删除visited, 在图中就是标记为已访问，下次再被访问时跳过；
currLevelSize记得更新；
利用Set.add()操作返回值避免添加重复元素进queue
 * */