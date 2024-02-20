/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.ncsu.csc216.wolf_tickets.model.tickets.ActiveTicketList;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;

/**
 * Test the Group class
 * @author John Butterfield
 *
 */
public class GroupTest {

	/**
	 * Test the Group constructor
	 */
	@Test
	public void testGroup() {
		Group g = new Group("name");
		assertEquals("name", g.getGroupName());
		assertTrue(g.getCurrentCategory() instanceof ActiveTicketList);
		assertTrue(g.isChanged());
		assertThrows(IllegalArgumentException.class, () -> new Group("Active Tickets"));
		assertThrows(IllegalArgumentException.class, () -> new Group(null));
		assertThrows(IllegalArgumentException.class, () -> new Group(""));

	}
	
	/**
	 * Test adding Category objects to a Group
	 */
	@Test
	public void testInvalidAddCategory() {
		Group g = new Group("group name");
		assertThrows(IllegalArgumentException.class, () -> g.addCategory(new Category(ActiveTicketList.ACTIVE_TASKS_NAME, 0)));
		g.addCategory(new Category("category 1", 0));
		assertThrows(IllegalArgumentException.class, () -> g.addCategory(new Category("Category 1", 0)));

	}
	
	/**
	 * Test the setCurrentCategory method in Group
	 */
	@Test
	public void testSetCurrentCategory() {
		Group g = new Group("group name");
		Category c1 = new Category("category 1", 0);
		Category c2 = new Category("category 2", 0);
		g.addCategory(c1);
		g.addCategory(c2);
		assertEquals(c2, g.getCurrentCategory());
		g.setCurrentCategory("category 1");
		assertEquals(c1, g.getCurrentCategory());
		g.setCurrentCategory("i dont exist");
		assertTrue(g.getCurrentCategory() instanceof ActiveTicketList);
		assertThrows(IllegalArgumentException.class, () -> new Group(""));
		
	}
	
	/**
	 * Test the getCategoriesNames method
	 */
	@Test
	public void testGetCategoriesNames() {
		Group g = new Group("group name");
		
		assertEquals(1, g.getCategoriesNames().length);
		assertEquals("Active Tickets", g.getCategoriesNames()[0]);
		Category c1 = new Category("category 1", 0);
		Category c2 = new Category("category 2", 0);
		g.addCategory(c1);
		g.addCategory(c2);
		assertEquals(3, g.getCategoriesNames().length);
		assertEquals("Active Tickets", g.getCategoriesNames()[0]);
		assertEquals("category 1", g.getCategoriesNames()[1]);
		assertEquals("category 2", g.getCategoriesNames()[2]);
		
	}
	
	/**
	 * Test the editCategory method in Group
	 */
	@Test
	public void testEditCategory() {
		Group g = new Group("group name");
		assertThrows(IllegalArgumentException.class, () -> g.editCategory("name"));
		Category c1 = new Category("category 1", 0);
		Category c2 = new Category("category 2", 0);
		Category c3 = new Category("a category", 0);
		
		g.addCategory(c1);
		g.addCategory(c2);
		assertThrows(IllegalArgumentException.class, () -> g.editCategory("Active tickets"));
		assertEquals(3, g.getCategoriesNames().length);
		assertEquals("Active Tickets", g.getCategoriesNames()[0]);
		assertEquals("category 1", g.getCategoriesNames()[1]);
		assertEquals("category 2", g.getCategoriesNames()[2]);
		
		g.editCategory("new name");
		assertEquals("new name", g.getCurrentCategory().getCategoryName());
		assertEquals(3, g.getCategoriesNames().length);
		assertEquals("Active Tickets", g.getCategoriesNames()[0]);
		assertEquals("category 1", g.getCategoriesNames()[1]);
		assertEquals("new name", g.getCategoriesNames()[2]);
		
		g.addCategory(c3);
		assertEquals(4, g.getCategoriesNames().length);
		assertEquals("Active Tickets", g.getCategoriesNames()[0]);
		assertEquals("a category", g.getCategoriesNames()[1]);
		assertEquals("category 1", g.getCategoriesNames()[2]);
		assertEquals("new name", g.getCategoriesNames()[3]);
		
		Group g2 = new Group("group 2 name");
		g2.addCategory(new Category("Category1", 0));
		g2.addCategory(new Category("ACategory", 0));
		g2.addCategory(new Category("MiddleCategory", 0));
		g2.addCategory(new Category("ZZZCategory", 0));
		assertEquals("ZZZCategory", g2.getCurrentCategory().getCategoryName());
		g2.editCategory("BCategory");
		assertEquals("BCategory", g2.getCategoriesNames()[2]);
		
		
	}
	
	/**
	 * Test the removeCategory method in Group
	 */
	@Test
	public void testRemoveCategory() {
		Group g = new Group("group name");
		assertThrows(IllegalArgumentException.class, () -> g.removeCategory());
		
		Category c1 = new Category("category 1", 0);
		Category c2 = new Category("category 2", 0);

		
		g.addCategory(c1);
		g.addCategory(c2);
		
		assertEquals("category 2", g.getCurrentCategory().getCategoryName());
		assertEquals(3, g.getCategoriesNames().length);
		g.removeCategory();
		assertTrue(g.getCurrentCategory() instanceof ActiveTicketList);
		assertEquals(2, g.getCategoriesNames().length);
	}
	
	/**
	 * Test the addTicket method in Group
	 */
	@Test
	public void testAddTicket() {
		Group g = new Group("group name");
		Ticket t = new Ticket("name", "description", true);
		assertEquals(1, g.getCategoriesNames().length);
		try {
			g.addTicket(t);
			assertEquals(1, g.getCategoriesNames().length);
		} catch(Exception e) {
			fail("Unexpected exception thrown");
		}
		Category c1 = new Category("category 1", 0);
		g.addCategory(c1);
		assertEquals(0, g.getCurrentCategory().getTickets().size());
		g.addTicket(t);
		assertEquals(1, g.getCurrentCategory().getTickets().size());
		
	}
	
	/**
	 * Test the editTicket method in Group
	 */
	@Test
	public void testEditTicket() {
		Group g = new Group("group name");
		try {
			g.editTicket(0, "name", "desc", true);
			
		} catch(Exception e) {
			fail("Unexpected exception thrown");
		}
		Category c1 = new Category("category 1", 0);
		g.addCategory(c1);
		Ticket t = new Ticket("name", "description", true);
		
		g.addTicket(t);
		assertEquals(1, g.getCurrentCategory().getTickets().size());
		g.editTicket(0, "new name", "new description", false);
		assertEquals("new name", g.getCurrentCategory().getTicket(0).getTicketName());
		assertEquals("new description", g.getCurrentCategory().getTicket(0).getTicketDescription());
		assertFalse(g.getCurrentCategory().getTicket(0).isActive());
		assertEquals(1, g.getCurrentCategory().getTickets().size());

		
		
	}
	
	/**
	 * Test a scenario
	 */
	@Test
	public void testScenario() {
		Group g = new Group("Name");
		
		Category c1 = new Category("Category1", 0);
		g.addCategory(c1);
		
		Ticket t1 = new Ticket("Ticket1", "Ticket1Description", true);
		Ticket t2 = new Ticket("Ticket2", "Ticket2Description", false);
		Ticket t3 = new Ticket("Ticket3", "Ticket3Description", true);
		g.addTicket(t1);
		g.addTicket(t2);
		g.addTicket(t3);
		
		Category c2 = new Category("ACategory", 0);
		g.addCategory(c2);
		
		Ticket t4 = new Ticket("Ticket4", "Ticket4Description", true);
		Ticket t5 = new Ticket("Ticket5", "Ticket5Description", false);

		g.addTicket(t4);
		g.addTicket(t5);
		
		g.setCurrentCategory("Active Tickets");
		
		//g.getCurrentCategory().completeTicket(t1);
		g.getCurrentCategory().getTicket(1).completeTicket();
		assertTrue(g.getCurrentCategory() instanceof ActiveTicketList);
		assertEquals(2, g.getCurrentCategory().getTickets().size());

	}
}
