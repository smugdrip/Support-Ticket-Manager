/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.File;

import org.junit.Test;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;

/**
 * Test the GroupReader class.
 * @author John Butterfield
 *
 */
public class GroupReaderTest {

	/**
	 * Test group reader with valid files
	 */
	@Test
	public void testGroupReaderValid() {
		
		Group g = GroupReader.readGroupFile(new File("test-files/group1.txt"));
		assertEquals("CSC IT", g.getGroupName());
		assertEquals(4, g.getCategoriesNames().length);
		
		g.setCurrentCategory("Web");
		assertEquals(1, g.getCurrentCategory().getTickets().size());
		
		g.setCurrentCategory("Classroom Tech");
		assertEquals(4, g.getCurrentCategory().getTickets().size());
		
		g.setCurrentCategory("Desktop");
		assertEquals(2, g.getCurrentCategory().getTickets().size());
		
		g.setCurrentCategory("Active Tickets");
		assertEquals(5, g.getCurrentCategory().getTickets().size());
		
		
		g = GroupReader.readGroupFile(new File("test-files/group7.txt"));
		g.setCurrentCategory("License Renewals");
		assertEquals(1, g.getCurrentCategory().getTickets().size());
		
		g = GroupReader.readGroupFile(new File("test-files/group6.txt"));
		assertEquals(1, g.getCategoriesNames().length);
		assertEquals(0, g.getCurrentCategory().getTickets().size());
		
	}
	
	/**
	 * Test group reader with more scenarios
	 */
	@Test
	public void testGroupReaderValid2() {
		Group g = GroupReader.readGroupFile(new File("test-files/group1.txt"));
		g.setCurrentCategory("Active Ticket List");
		assertEquals(5, g.getCurrentCategory().getTickets().size());
	}
	
	/**
	 * Test group reader with invalid files
	 */
	@Test
	public void testGroupReaderInvalid() {
		
		assertThrows(IllegalArgumentException.class, () -> GroupReader.readGroupFile(new File("/test-files/group.txt")));
		assertThrows(IllegalArgumentException.class, () -> GroupReader.readGroupFile(new File("test-files/group3.txt")));

	}
	
}
