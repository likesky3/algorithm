### DFS
* Remove Invalid Parentheses  
DFS, 递归  
技巧：  
去重技巧（非HashSet）：连续相同括号时限定删除第一个；记录上一次删除的位置，后续递归从这个位置后面进行删除。  
利用括号的对称性提高代码重用性。  

* Recover Binary Search Tree：没有很快想清楚思路  

* Reconstruct Itinerary  
DFS, 递归，回溯，贪婪。主体思路是正确的，调试很久的两个bug:  
1.回溯时要恢复现场，在位置 i 处删除某个元素，回溯时要将这个元素加回到位置 i，而不是简单地加在最后。  
2.回溯时要恢复现场，result添加了一个元素，回溯时删除，要精准删除最后位置的这个元素，而不能简单地调用result.remove(to), 一旦result中含有重复元素，则无法得到正确答案。  
另外，借助判断返回值可尽早结束。  
此题有另一种思路，尚未理解其正确性。  

* Number of Islands  
DFS, BFS, Union-Find. 其中Union-Find的union函数写错debug了很长时间，直接对比正确code的时候都没识别出来写错的地方。
union是将两个root进行union，而不是当前具体元素，若仅union了非根元素，会导致重复union，此题中进而导致count计算不正确。

* Longest Increasing Path in a Matrix  
DFS, Memoization. 没有使用cache数组，此题要超时。*搜索类题目，总要想想能否借助缓存提高性能，所谓记忆式搜索*

* Clone Graph
递归时要检查是否会死循环而导致栈溢出。

* Course Schedule  
Toplogic Sort。 图的表示，可以用二维数组，也可以用Adjacent List.

* Intersection of Two Linked Lists  
体会不需要计算length的思路，我即使理解了思路coding也没对。

* Insertion Sort List  
Memory Limit Exceed。某些case代码会死循环，链表类题目，如果存在圈就可能死循环。

###DP
* Coin Change  
出错原因：之所以错是因为限制了子递归调用可用的coin类型，对于case [3,7,405,436] 8839，首次计算amount=24时，p = 0，因此得到dp[24] = 8，而dp[i]一旦计算出来后不再更新。要保证任何时候计算amount的时候，所有coins都可用。
* Create Maximum Number  
综合题，可拆解为两个子问题：1. 如何从一个数字数组中获取包含k个元素的最大子序列，2. 如何合并两个数组使得结果最大。  
* Paint Fences  
Easy题，我却没有一次ac，且因为思路不够清晰没有写出简洁的code。
* Paint Houses  
1. 做dp时如果输入参数可以修改，可利用以减小空间开销。 2. 初始化没彻底导致bug。  
