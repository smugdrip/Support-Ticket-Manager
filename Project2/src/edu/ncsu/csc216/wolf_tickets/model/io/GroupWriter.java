/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;

/**
 * Write a Group object and all of its corresponding Categories and Tickets to a
 * formatted text file. The output text file can then be read by the GroupReader
 * class.
 * 
 * @author John Butterfield
 *
 */
public class GroupWriter {

	/**
	 * Write a group to a file.
	 * 
	 * @param groupFile  : file for the group to be written to.
	 * @param groupName  : name of the group.
	 * @param categories : list of categories that make up the group.
	 * @throws IllegalArgumentException if the specified destination path is
	 *                                  invalid.
	 */
	public static void writeGroupFile(File groupFile, String groupName, ISortedList<Category> categories) {
		try {

			PrintStream fileWriter = new PrintStream(groupFile);

			fileWriter.println("! " + groupName);

			for (int i = 0; i < categories.size(); i++) {

				fileWriter.println(
						"# " + categories.get(i).getCategoryName() + "," + categories.get(i).getCompletedCount());

				for (int j = 0; j < categories.get(i).getTickets().size(); j++) {

					fileWriter.println(categories.get(i).getTicket(j).toString());

				}

			}

			fileWriter.close();
		} catch (FileNotFoundException e) {

			throw new IllegalArgumentException("Unable to save file.");

		}
	}
}
