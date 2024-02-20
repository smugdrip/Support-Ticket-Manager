/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;

/**
 * Test group writer
 * 
 * @author John Butterfield
 *
 */
public class GroupWriterTest {

	/**
	 * Test group writer
	 */
	@Test
	public void testGroupWriter() {
		Group g = new Group("Group");
		g.addCategory(new Category("ACategory", 0));
		g.addCategory(new Category("Tickets1", 0));
		assertEquals("Tickets1", g.getCurrentCategory().getCategoryName());
		g.getCurrentCategory().addTicket(new Ticket("Ticket1", "Ticket1Description", false));
		g.getCurrentCategory().addTicket(new Ticket("Ticket2", "Ticket2Description", true));
		g.addCategory(new Category("Tickets2", 0));
		assertEquals("Tickets2", g.getCurrentCategory().getCategoryName());
		g.getCurrentCategory().addTicket(new Ticket("Ticket3", "Ticket3Description", false));
		g.getCurrentCategory().addTicket(new Ticket("Ticket4", "Ticket4Description", true));
		g.getCurrentCategory().addTicket(new Ticket("Ticket5", "Ticket5Description", false));
		g.saveGroup(new File("test-files/actual_out.txt"));

		checkFiles("test-files/expected_out.txt", "test-files/actual_out.txt");

		assertThrows(IllegalArgumentException.class, () -> g.saveGroup(new File("/test-file/actual_out.txt")));

	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual outputs
	 */
	private void checkFiles(String expFile, String actFile) {

		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
				Scanner actScanner = new Scanner(new FileInputStream(actFile));) {

			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act);
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
