package org.professional.dp;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditDistanceTest {
//	@Test
//	public void testEditDistance() {
//		EditDistance obj = new EditDistance();
//		assertEquals(0,obj.editDistance("abc", "edf", 0, 0, 0));
//	    assertEquals(0,obj.editDistance("", "", 1, 1, 1));
//	    assertEquals(0,obj.editDistance("abc", "abc", 1, 1, 1));
//	    assertEquals(0,obj.editDistance("abcd", "abc", 1, 0, 1));
//	    assertEquals(1,obj.editDistance("abc", "adc", 2, 2, 1));
//	    assertEquals(1,obj.editDistance("abc", "dabc", 1, 0, 0));
//	    assertEquals(5,obj.editDistance("kitten", "sitting", 1, 1, 10));
//		assertEquals(0, obj.editDistance("", null, 1, 2, 3));
//		assertEquals(1, obj.editDistance("", "a", 1, 2, 3));
//		assertEquals(2, obj.editDistance("a", "", 1, 2, 3));
//		assertEquals(3, obj.editDistance("b", "a", 1, 2, 3));
//		assertEquals(0, obj.editDistance("a", "a", 1, 1, 1));
//		assertEquals(1, obj.editDistance("ac", "ad", 1, 1, 1));
//		assertEquals(1, obj.editDistance("abcdefgh", "abceefgh", 1, 1, 1));
//		assertEquals(1, obj.editDistance("leetcode", "letcode", 1, 1, 1));
//		
//		assertEquals(Integer.MAX_VALUE, obj.editDistance("ab", "ed", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
//		assertEquals(Integer.MAX_VALUE, obj.editDistance("ab", "", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
//		assertEquals(Integer.MAX_VALUE, obj.editDistance("", "ab", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
//		
//		assertEquals(2, obj.editDistance("ab", null, 1, 1, 1));
//	    assertEquals(2, obj.editDistance(null, "ab", 1, 1, 1));
//	}
	
//	@Test
//	public void testGetEditDistance() { 
//		EditDistance ed = new EditDistance();
//		assertEquals(0,ed.getEditDistance("abc", "edf", 0, 0, 0));
//		assertEquals(3,ed.getEditDistance("abc", "edf", 1, 1, 1));
//		assertEquals(4,ed.getEditDistance("abc", "edf", 1, 5, 1));
//	    assertEquals(0,ed.getEditDistance("", "", 1, 1, 1));
//	    assertEquals(0,ed.getEditDistance("abc", "abc", 1, 1, 1));
//		
////		assertEquals(Integer.MAX_VALUE, ed.getEditDistance("ab", "ed", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
////		assertEquals(Integer.MAX_VALUE, ed.getEditDistance("ab", "", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
////		assertEquals(Integer.MAX_VALUE, ed.getEditDistance("", "ab", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
//		
//		assertEquals(2, ed.getEditDistance("ab", "", 1, 1, 1));
//	    assertEquals(1, ed.getEditDistance(null, "ab", 1, 1, 1));
//	}
	
	@Test
	public void testGetLeastEditDistance() {
		EditDistance ed = new EditDistance();
		assertEquals(0, ed.getLeastEditDistance2("abc", "edf", 0, 0, 0));
		assertEquals(1, ed.getLeastEditDistance2("abc", "abd", 1, 1, 1));
		assertEquals(1, ed.getLeastEditDistance2("abc", "abd", 1, 1, 5));
		
		assertEquals(0, ed.getLeastEditDistance2("", "", 1, 1, 1));
	    assertEquals(3, ed.getLeastEditDistance2("abc", "", 1, 1, 1));
	    assertEquals(0, ed.getLeastEditDistance2("abc", "eabcd", 1, 1, 1));
	}
}
