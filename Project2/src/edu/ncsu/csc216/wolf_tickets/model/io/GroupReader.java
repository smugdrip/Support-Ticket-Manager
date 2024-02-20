/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.tickets.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.tickets.ActiveTicketList;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;

/**
 * GroupReader reads a formatted text file and generates a Group object
 * containing the corresponding Categories and Tickets from the file.
 * 
 * @author John Butterfield
 *
 */
public class GroupReader {

	/**
	 * Generate a Group object by reading from a file.
	 * 
	 * @param groupFile : file to be read
	 * @return a Group with information from the file.
	 * @throws IllegalArgumentException if the input file could not be found or if
	 *                                  the input file does not contain a group.
	 */
	public static Group readGroupFile(File groupFile) {
		String groupString = "";

		try {
			Scanner fileScan = new Scanner(groupFile);
			while (fileScan.hasNextLine()) {
				groupString = groupString + fileScan.nextLine() + "\n";
			}
			fileScan.close();

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");

		}

		if (groupString.charAt(0) != '!') {
			throw new IllegalArgumentException("Unable to load file.");

		}

		Scanner groupScan = new Scanner(groupString);
		Group newGroup = new Group(groupScan.nextLine().substring(2));

		groupScan.useDelimiter("\\r?\\n?[#]");
		while (groupScan.hasNext()) {
			try {
				newGroup.addCategory((Category) processCategory(groupScan.next()));
			} catch (Exception e) {
				// ignore the invalid category
			}

		}
		groupScan.close();
		newGroup.setCurrentCategory(ActiveTicketList.ACTIVE_TASKS_NAME);
		newGroup.setChanged(false);

		return newGroup;

	}

	/**
	 * Process a Category from a greater Group.
	 * 
	 * @param categoryText : text that makes up a category
	 * @return a Category object created from the text.
	 */
	private static AbstractCategory processCategory(String categoryText) {
		Scanner categoryScan = new Scanner(categoryText);
		String c = categoryScan.nextLine();

		Category newCategory = new Category(c.substring(1, c.indexOf(',')),
				Integer.parseInt(c.substring(c.indexOf(',') + 1)));

		categoryScan.useDelimiter("\\r?\\n?[*]");
		while (categoryScan.hasNext()) {
			try {
				newCategory.addTicket(processTicket(categoryScan.next()));
			} catch (Exception e) {
				// ignore the invalid ticket
			}

		}
		categoryScan.close();
		return newCategory;

	}

	/**
	 * Process a Ticket from a greater Category
	 * 
	 * @param ticketText : text that makes up the ticket
	 * @return a Ticket object created from the text.
	 */
	private static Ticket processTicket(String ticketText) {
		Scanner ticketScan = new Scanner(ticketText);
		String title = "";
		String description = "";
		boolean active = false;

		if (ticketScan.hasNextLine()) {
			title = ticketScan.nextLine();
		}

		while (ticketScan.hasNextLine()) {
			description = description + ticketScan.nextLine();
		}

		if (title.contains(",")) {
			active = true;
			title = title.substring(1, title.indexOf(','));
		} else {
			title = title.substring(1);
		}

		Ticket newTicket = new Ticket(title, description, active);

		ticketScan.close();
		return newTicket;

	}
}
