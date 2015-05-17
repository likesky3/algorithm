package org.professional.dp;

import junit.framework.Assert;

import org.junit.Test;

public class FindMinTotalTimeTest {
	FindMinTotalTime  obj = new FindMinTotalTime();
	int[] a1 = {1,3};
	int[] t1 = {2,0};
	int[] a2 = {2,2};
	int[] t2 = {2,0};
	AssembleLine line1 = new AssembleLine(0, a1, t1);
	AssembleLine line2 = new AssembleLine(0, a2, t2);
	AssembleLine[] lines = {line1, line2};
	int[] bestPath = new int[a1.length];
	@Test
	public void testFindMinTotalTime() throws Exception {
		Assert.assertEquals(4, obj.findMinTotalTime(lines, bestPath));
	}
	
	@Test
	public void testCheckSolution() throws Exception{
		Assert.assertEquals(true, obj.checkSolution(lines));
	}
}


/* 
某个汽车工厂共有两条装配线,每条有 n 个装配站。装配线 i 的第 j个装配站表示为 Si,j ,在该站的装配时间为 ai,j 。 
一个汽车底盘进入工厂,然后进入装配线 i(i 为 1 或 2),花费时间为 ei 。在通过一条线的第 j 个装配站后, 
这个底盘来到任一条装配线的第(j+1)个装配站。 
如果它留在相同的装配线,则没有移动开销。但是,如果它移动到另一条线上,则花费时间为 ti,j 。 
在离开一条装配线的第 n 个装配站后,完成的汽车底盘花费时间为 xi 离开工厂。 
待求解的问题是,确定应该在装配线 1 内选择哪些站,在装配线 2 内选择哪些站……，才能使汽车通过工厂的总时间最短。 
*/  

class AssembleLine {  
    int enterTime;  
    int[] assembleTime;  
    int[] transferTime;    
    public AssembleLine(int e, int[] a, int[] t) {
    	this.enterTime = e;
    	this.assembleTime = a;
    	this.transferTime = t;
    }
}  

class FindMinTotalTime {
	int findMinTotalTime(AssembleLine[] lines, int[] bestPath) throws Exception{  
	    if (lines == null || lines.length == 0)  
	        return 0;  
	    if (bestPath == null) {  
	        throw new Exception("Error input: bestPath null");  
	    }
	    int lineNum = lines.length;
	    int stationNum = lines[0].assembleTime.length;
	    // dp[i][j]: 第j条装配线到第i个装配站时的最小耗时
	    int[][] dp = new int[stationNum][lineNum];
	    for (int line = 0; line < lineNum; line++) {
	    	dp[0][line] = lines[line].enterTime + lines[line].assembleTime[0];
	    }
	    for (int currStation = 1; currStation < stationNum; currStation++) {
	    	for (int currLine = 0; currLine < lineNum; currLine++) {
	    		int lastStation = currStation - 1;
	    		dp[currStation][currLine] = dp[lastStation][currLine];
	    		for (int line = 0; line < lineNum; line++) {
	    			int newChoiceTime = dp[lastStation][line] + lines[line].transferTime[lastStation];
					if (line != currLine && newChoiceTime < dp[currStation][currLine]) {
						dp[currStation][currLine] = newChoiceTime;
					}
	    		}
	    		dp[currStation][currLine] += lines[currLine].assembleTime[currStation];
	    	}
	    }
	    int min = Integer.MAX_VALUE;
	    int finishAtLine = 0; 
	    for (int line = 0; line < lineNum; line++) {
	    	if(dp[stationNum - 1][line] < min) {
	    		min = dp[stationNum - 1][line];
	    		finishAtLine = line;
	    	}
	    }
	    
	    int chooseLine = finishAtLine;
	    int currTime = min - lines[chooseLine].assembleTime[stationNum - 1];
	    bestPath[stationNum - 1] = chooseLine;
	    for(int station = stationNum - 2; station >= 0; station--) {
	    	if (currTime == dp[station][chooseLine]) {
	    		bestPath[station] = chooseLine;
	    		currTime = dp[station][chooseLine];
	    		continue;
	    	}
	    	for (int line = 0; line < lineNum; line++) {
	    		if (line != chooseLine && currTime == dp[station][line] - lines[line].transferTime[station]) {
	    			bestPath[station] = line;
	    			currTime = dp[station][line];
	    			chooseLine = line;
	    			break;
	    		}
	    	}
	    }
	    return min;
	}
	
	public boolean checkSolution(AssembleLine[] lines) throws Exception {
		if (lines == null)
			return false;
		int stationNum = lines[0].assembleTime.length;
		int[] bestPath = new int[stationNum];
		int target = findMinTotalTime(lines, bestPath);
		int sum = lines[bestPath[0]].enterTime + lines[bestPath[0]].assembleTime[0];
		int lastLine = bestPath[0];
		for (int i = 1; i < stationNum; i++) {
			int currLine = bestPath[i];
			sum += lines[currLine].assembleTime[i];
			if (currLine != lastLine) {
				sum += lines[lastLine].transferTime[i - 1];
			}
		}
		return target == sum;
	}
}