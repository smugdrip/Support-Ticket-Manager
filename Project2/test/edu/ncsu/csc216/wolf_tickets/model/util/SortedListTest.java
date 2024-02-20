/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Test the SortedList class
 * @author John Butterfield
 *
 */
public class SortedListTest {

	
	/**
	 * Test the sorted list constructor.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> s = new SortedList<String>();
		assertEquals(0, s.size());
	}
	
	/**
	 * Test adding elements to SortedList.
	 */
	@Test
	public void testAddingSortedList() {
		SortedList<String> s = new SortedList<String>();
		assertThrows(NullPointerException.class, () -> s.add(null));
		s.add("a");
		assertEquals(1, s.size());
		assertEquals("a", s.get(0));
		assertThrows(IllegalArgumentException.class, () -> s.add("a"));
		
		s.add("c");
		assertEquals(2, s.size());
		assertEquals("a", s.get(0));
		assertEquals("c", s.get(1));
		
		s.add("b");
		assertEquals(3, s.size());
		assertEquals("a", s.get(0));
		assertEquals("b", s.get(1));
		assertEquals("c", s.get(2));
		
		SortedList<String> s2 = new SortedList<String>();
		s2.add("b");
		assertEquals(1, s2.size());
		assertEquals("b", s2.get(0));
		
		s2.add("a");
		assertEquals(2, s2.size());
		assertEquals("a", s2.get(0));
		assertEquals("b", s2.get(1));
	}
	
	/**
	 * Test removing elements from SortedList.
	 */
	@Test
	public void testRemovingSortedList() {
		SortedList<String> s = new SortedList<String>();
		assertThrows(IndexOutOfBoundsException.class, () -> s.remove(0));
		s.add("a");
		assertThrows(IndexOutOfBoundsException.class, () -> s.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> s.remove(1));
		
		assertEquals("a", s.remove(0));
		assertEquals(0, s.size());
		
		SortedList<String> s2 = new SortedList<String>();
		s2.add("a");
		s2.add("b");
		s2.add("c");
		
		assertEquals("a", s2.remove(0));
		assertEquals(2, s2.size());
		assertEquals("b", s2.get(0));
		assertEquals("c", s2.get(1));
		
		SortedList<String> s3 = new SortedList<String>();
		s3.add("a");
		s3.add("b");
		s3.add("c");
		assertEquals("b", s3.remove(1));
		assertEquals(2, s3.size());
		assertEquals("a", s3.get(0));
		assertEquals("c", s3.get(1));
		
		SortedList<String> s4 = new SortedList<String>();
		s4.add("a");
		s4.add("b");
		s4.add("c");
		assertEquals("c", s4.remove(2));
		assertEquals(2, s4.size());
		assertEquals("a", s4.get(0));
		assertEquals("b", s4.get(1));
		
		
		
		
		
		
	}
}
