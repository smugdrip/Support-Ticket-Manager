/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test for the Ticket class.
 * @author John Butterfield
 *
 */
public class TicketTest {

	/**
	 * Test the ticket constructor and associated setters.
	 */
	@Test
	public void testTicket() {
		Ticket t = new Ticket("name", "description", true);
		assertEquals("name", t.getTicketName());
		assertEquals("description", t.getTicketDescription());
		assertTrue(t.isActive());
		
		assertThrows(IllegalArgumentException.class, () -> new Ticket("", "description", true));
		assertThrows(IllegalArgumentException.class, () -> new Ticket("name", null, true));
	}
	
	/**
	 * Test adding a category to a ticket.
	 */
	@Test
	public void testAddCategoryToTicket() {
		Ticket t = new Ticket("name", "description", true);
		Category c = new Category("name1", 0);
		Category c2 = new Category("name2", 0);
		
		assertThrows(IllegalArgumentException.class, () -> t.addCategory(null));
		assertEquals("", t.getCategoryName());
		t.addCategory(c);
		assertEquals("name1", t.getCategoryName());
		t.addCategory(c2);
		assertEquals("name1", t.getCategoryName());
		
	}
	
	/**
	 * Test the toString method.
	 */
	@Test
	public void testToStringTicket() {
		Ticket t = new Ticket("Ticket1", "Ticket1Description", false);
		
		assertEquals("* Ticket1\r\n" + "Ticket1Description", t.toString());
		
//		t = new Ticket("Dr. McLeod's computer won't charge.", "The laptop provided to me won't charge when I plug in the charger. \r\n"
//				+ "The charger works for other laptops though.", true);
//		
//		assertEquals("* Dr. McLeod's computer won't charge.,active\r\n"
//				+ "The laptop provided to me won't charge when I plug in the charger. \r\n"
//				+ "The charger works for other laptops though.\r\n"
//				+ "", t.toString());
		
		
	}
	
	/**
	 * Test the completeTicket method
	 */
	@Test
	public void testCompleteTicket() {
		Ticket t = new Ticket("ticket 1", "description 1", true);
		Category c = new Category("category 1", 0);
		c.addTicket(t);
		assertEquals("category 1", t.getCategoryName());
		assertEquals(1, c.getTickets().size());
		assertEquals("ticket 1", c.getTickets().get(0).getTicketName());
		
		t.completeTicket();
		assertEquals(0, c.getTickets().size());
		
		
	}
	
}
