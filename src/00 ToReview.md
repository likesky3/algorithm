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


