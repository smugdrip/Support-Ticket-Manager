/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Test the SwapList class.
 * 
 * @author John Butterfield
 *
 */
public class SwapListTest {

	/**
	 * Test the SwapList constructor.
	 */
	@Test
	public void testSwapList() {

		SwapList<String> s = new SwapList<String>();
		assertEquals(0, s.size());
	}

	/**
	 * Test adding elements to the a SwapList.
	 */
	@Test
	public void testAddingSwapList() {

		SwapList<String> s = new SwapList<String>();

		assertThrows(IndexOutOfBoundsException.class, () -> s.get(0));

		s.add("first");
		assertEquals(1, s.size());
		assertEquals("first", s.get(0));

		s.add("second");
		assertEquals(2, s.size());
		assertEquals("second", s.get(1));
		assertEquals("first", s.get(0));

		s.add("third");
		assertEquals(3, s.size());
		assertEquals("third", s.get(2));
		assertEquals("second", s.get(1));
		assertEquals("first", s.get(0));

		assertThrows(IndexOutOfBoundsException.class, () -> s.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> s.get(3));
		assertThrows(NullPointerException.class, () -> s.add(null));

		s.add("fourth");
		s.add("fifth");
		s.add("sixth");
		s.add("seventh");
		s.add("eighth");
		s.add("ninth");
		s.add("tenth");
		s.add("eleventh");
		assertEquals(11, s.size());

	}

	/**
	 * Test Removing an element from a SwapList.
	 */
	@Test
	public void testRemoveSwapList() {
		SwapList<String> s = new SwapList<String>();

		s.add("first");
		assertEquals(1, s.size());
		assertEquals("first", s.get(0));

		assertEquals("first", s.remove(0));
		assertEquals(0, s.size());

		s.add("first");
		s.add("second");
		s.add("third");

		assertEquals(3, s.size());
		assertEquals("second", s.remove(1));
		assertEquals(2, s.size());
		assertEquals("first", s.get(0));
		assertEquals("third", s.get(1));

		SwapList<String> s2 = new SwapList<String>();

		s2.add("first");
		s2.add("second");
		s2.add("third");

		assertEquals("third", s2.remove(2));
		assertEquals(2, s2.size());

		SwapList<String> s3 = new SwapList<String>();

		s3.add("first");
		s3.add("second");
		s3.add("third");

		assertEquals("first", s3.remove(0));
		assertEquals(2, s3.size());

	}

	/**
	 * Test the moveUp method in SwapList.
	 */
	@Test
	public void testMoveUpSwapList() {

		SwapList<String> s = new SwapList<String>();
		s.add("first");

		s.moveUp(0);
		assertEquals(1, s.size());
		assertEquals("first", s.get(0));

		assertThrows(IndexOutOfBoundsException.class, () -> s.moveUp(-1));
		s.add("second");
		assertThrows(IndexOutOfBoundsException.class, () -> s.moveUp(2));

		s.moveUp(1);
		assertEquals(2, s.size());
		assertEquals("second", s.get(0));
		assertEquals("first", s.get(1));

		SwapList<String> s2 = new SwapList<String>();
		s2.add("first");
		s2.add("second");
		s2.add("third");
		s2.moveUp(1);
		assertEquals(3, s2.size());
		assertEquals("second", s2.get(0));
		assertEquals("first", s2.get(1));
		assertEquals("third", s2.get(2));

	}

	/**
	 * Test the moveUp method in SwapList.
	 */
	@Test
	public void testMoveDownSwapList() {

		SwapList<String> s = new SwapList<String>();
		s.add("first");

		s.moveDown(0);
		assertEquals(1, s.size());
		assertEquals("first", s.get(0));

		assertThrows(IndexOutOfBoundsException.class, () -> s.moveDown(-1));
		s.add("second");
		assertThrows(IndexOutOfBoundsException.class, () -> s.moveDown(2));

		s.moveDown(0);
		assertEquals(2, s.size());
		assertEquals("second", s.get(0));
		assertEquals("first", s.get(1));

		SwapList<String> s2 = new SwapList<String>();
		s2.add("first");
		s2.add("second");
		s2.add("third");
		s2.moveDown(2);
		assertEquals(3, s2.size());
		assertEquals("first", s2.get(0));
		assertEquals("second", s2.get(1));
		assertEquals("third", s2.get(2));

		SwapList<String> s3 = new SwapList<String>();
		s3.add("first");
		s3.add("second");
		s3.add("third");
		s3.moveDown(0);
		assertEquals(3, s3.size());
		assertEquals("second", s3.get(0));
		assertEquals("first", s3.get(1));
		assertEquals("third", s3.get(2));

	}

	/**
	 * Test the moveToFront method in SwapList.
	 */
	@Test
	public void testMoveToFrontSwapList() {

		SwapList<String> s = new SwapList<String>();
		s.add("first");
		assertThrows(IndexOutOfBoundsException.class, () -> s.moveToFront(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> s.moveToFront(1));
		assertThrows(IndexOutOfBoundsException.class, () -> s.moveToFront(2));
		s.moveToFront(0);
		assertEquals(1, s.size());
		assertEquals("first", s.get(0));

		s.add("second");
		s.moveToFront(0);
		assertEquals(2, s.size());
		assertEquals("first", s.get(0));
		assertEquals("second", s.get(1));
		s.moveToFront(1);
		assertEquals(2, s.size());
		assertEquals("second", s.get(0));
		assertEquals("first", s.get(1));

		s.add("third");
		s.moveToFront(2);
		assertEquals("third", s.get(0));
		assertEquals("second", s.get(1));
		assertEquals("first", s.get(2));

	}

	/**
	 * Test the moveToBack method in SwapList.
	 */
	@Test
	public void testMoveToBackSwapList() {

		SwapList<String> s = new SwapList<String>();
		s.add("first");
		assertThrows(IndexOutOfBoundsException.class, () -> s.moveToBack(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> s.moveToBack(1));
		assertThrows(IndexOutOfBoundsException.class, () -> s.moveToBack(2));
		s.moveToBack(0);
		assertEquals(1, s.size());
		assertEquals("first", s.get(0));

		s.add("second");
		s.moveToBack(1);
		assertEquals(2, s.size());
		assertEquals("first", s.get(0));
		assertEquals("second", s.get(1));
		s.moveToBack(0);
		assertEquals(2, s.size());
		assertEquals("second", s.get(0));
		assertEquals("first", s.get(1));

		s.add("third");
		s.moveToBack(0);
		assertEquals("first", s.get(0));
		assertEquals("third", s.get(1));
		assertEquals("second", s.get(2));

	}

}
