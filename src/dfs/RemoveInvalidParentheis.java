package dfs;

import java.util.ArrayList;
import java.util.List;

/**
ref: https://leetcode.com/discuss/81478/easy-short-concise-and-fast-java-dfs-3-ms-solution
我们都知道如何验证括号串是否平衡，一个简单办法就是使用一个计数器，遇到‘（’ 加一， 遇到‘）’减一，
遍历过程中一旦计数器变为负数，说明已遍历的子串中有多余的‘）’。
为使前缀合法，我们需要删除一个多余的右括号，那删除哪个呢？答案是任意一个都行。
但是，如果真是随意删，就会得到许多重复结果。
考虑s=())，删s[1] 和s[2]结果是一样的，因此，我们规定每次删除连续相同括号的第一个。

删除后，前缀合法了，接着便可以递归地检查剩余的字符串了。
需要注意的是，我们还需要记录另一个信息：最后一次删除的位置。
没有这个信息，我们可能会得到重复结果，考虑s=()k))

至此，有人可能会问，如果是左括号更多呢，答案是可以从右往左类似验证一次，
一个更聪明的办法是，反转字符串，重用相同的代码。
*/

public class RemoveInvalidParentheis {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<String>();
        if (s == null) return res;
        recur(s, new char[]{'(', ')'}, 0, 0, res);
        return res;
    }
    private void recur(String s, char[] pair, int last_i, int last_j, List<String> res) {
        int n = s.length();
        int count = 0;
        int i = last_i;
        for (; i < n; i++) {
            char curr = s.charAt(i);
            if (curr == pair[0]) count++;
            if (curr == pair[1] && --count < 0) {
                for (int j = last_j; j <= i; j++) {
                    if (s.charAt(j) == pair[1] && (j == last_j || s.charAt(j - 1) != pair[1]))
                        recur(s.substring(0, j) + s.substring(j + 1), pair, i, j, res);
                }
                return;
            }
            
        }
        
        String reversed = new StringBuilder(s).reverse().toString();
        if (pair[0] == '(') {
            recur(reversed, new char[]{')', '('}, 0, 0, res);
        } else {
            res.add(reversed);
        }
    }
}
