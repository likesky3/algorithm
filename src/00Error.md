引用Google一位程序员的话
>程序员分为两种
>
> 1. 首先确认前条件/不变式/终止条件/边界条件，然后开始编码
> 2. 先开始编码，然后通过各种用例/测试/调试对编码进行调整，最后得到一个好的结果
>个人认为第一种程序员的效率是第二种的10倍，因为第一种程序员不需要把时间浪费在编码-测试-debug-编码这个及其耗时的循环上。

#####没有明确前条件（初始条件）
Minimum Depth of Binary Tree, 初值应该是整数最大值而不是1.
Binary Tree Maximum Path Sum, 初值应设为最小整数而非0.

#####常见边界条件
1. 最大值、最小值测试
Valid Binary Search Tree, 边界值需要使用Long类型的极值

2. 空指针
Flatten Binary Tree to A Linked List
