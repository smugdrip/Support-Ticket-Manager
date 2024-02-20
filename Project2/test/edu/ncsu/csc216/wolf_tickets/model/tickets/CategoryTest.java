/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Test the Category, AbstractCategory, and ActiveTicketList  classes.
 * @author John Butterfield
 *
 */
public class CategoryTest {
	
	/**
	 * Test the construction of Category objects.
	 */
	@Test
	public void testCategory() {
		
		assertThrows(IllegalArgumentException.class, () -> new Category("A name", -1));
		assertThrows(IllegalArgumentException.class, () -> new Category(null, 1));
		Category c = new Category("A name", 0);
		assertEquals("A name", c.getCategoryName());
		assertEquals(0, c.getCompletedCount());
		
	}
	
	/**
	 * Test the compareTo method in Category.
	 */
	@Test
	public void testCompareToCategory() {
		Category c = new Category("A name", 0);
		
		Category c2 = new Category("A name", 0);
		
		assertEquals(0, c.compareTo(c2));
		
		
	}
	
	/**
	 * Test the getTicketsAsArray method in Category.
	 */
	@Test
	public void testGetArrayCategory() {
		Category c = new Category("name", 1);
		assertEquals(0, c.getTicketsAsArray().length);
		c.addTicket(new Ticket("name", "", true));
		c.addTicket(new Ticket("name2", "description", false));
		String[][] actual = c.getTicketsAsArray();
		String[][] expected = {{"0", "name"}, {"1", "name2"}};
		for (int i = 0; i < c.getTicketsAsArray().length; i++) {
			for (int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], actual[i][j]);
			}
		}
		
	}
	
	/**
	 * Test the removeTicket, getTicket, and completeTicket methods in AbstractCategory
	 */
	@Test
	public void testAbstractCategory() {
		
		Category c = new Category("name", 1);
		Ticket t1 = new Ticket("name", "", true);
		Ticket t2 = new Ticket("Aname2", "description", false);
		c.addTicket(t1);
		c.addTicket(t2);
		
		assertEquals(t1, c.removeTicket(0));
		assertEquals(t2, c.getTicket(0));
		
		Category c2 = new Category("name", 0);
		Ticket t3 = new Ticket("name", "", true);
		Ticket t4 = new Ticket("Aname2", "description", false);
		c2.addTicket(t3);
		c2.addTicket(t4);
		assertEquals(c2.getCategoryName(), t3.getCategoryName());
		assertEquals(c2.getCategoryName(), t4.getCategoryName());
		assertEquals(2, c2.getTickets().size());
		assertEquals(0, c2.getCompletedCount());
		t3.completeTicket();
		assertEquals(1, c2.getCompletedCount());
		assertEquals(1, c2.getTickets().size());
		assertEquals(t4, c2.getTickets().get(0));
		t4.completeTicket();
		assertEquals(2, c2.getCompletedCount());
		assertEquals(0, c2.getTickets().size());
		
		assertEquals("", t3.getCategoryName());
		
			
	}
	
	/**
	 * Test the getTicketsAsArray method in ActiveTicketList
	 */
	@Test
	public void testGetArrayActiveList() {
		ActiveTicketList a = new ActiveTicketList();
		assertEquals(0, a.getTicketsAsArray().length);
		a.addTicket(new Ticket("name", "", true));
		a.addTicket(new Ticket("name2", "description", true));
		assertThrows(IllegalArgumentException.class, () -> a.addTicket(new Ticket("name2", "description", false)));
		String[][] actual = a.getTicketsAsArray();
		String[][] expected = {{"Active Tickets", "name"}, {"Active Tickets", "name2"}};
		for (int i = 0; i < a.getTicketsAsArray().length; i++) {
			for (int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], actual[i][j]);
			}
		}
	}
	
	/**
	 * Test the setCategoryName and clearTickets methods in ActiveTicketList
	 */
	@Test
	public void testActiveListMethods() {
		ActiveTicketList a = new ActiveTicketList();
		a.addTicket(new Ticket("name", "", true));
		a.addTicket(new Ticket("name2", "description", true));
		assertThrows(IllegalArgumentException.class, () -> a.setCategoryName("abc"));
		
		assertEquals(2, a.getTickets().size());
		a.clearTickets();
		assertEquals(0, a.getTickets().size());
	}
	
	/**
	 * Test the Ticket, Category observer system
	 */
	@Test
	public void testTicketObserver() {
		Ticket t = new Ticket("ticket name", "ticket description", false);
		Category c = new Category("category name", 0);
		c.addTicket(t);
		assertEquals(1, c.getTickets().size());
		t.completeTicket();
		assertEquals(1, c.getCompletedCount());
		assertEquals(0, c.getTickets().size());
	}
}
