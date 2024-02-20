/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.tickets;

/**
 * A Category of tickets in the WolfTicket system.
 * 
 * @author John Butterfield
 *
 */
public class Category extends AbstractCategory implements Comparable<Category> {

	/**
	 * Construct the Category.
	 * 
	 * @param categoryName   : the name of the category.
	 * @param completedCount : the count of completed tickets for the category.
	 */
	public Category(String categoryName, int completedCount) {
		super(categoryName, completedCount);

	}

	/**
	 * Compares the names of two Category objects, ignoring capitalization.
	 * 
	 * @param otherCategory category to be compared
	 * @return the value of the comparison.
	 */
	public int compareTo(Category otherCategory) {
		return this.getCategoryName().compareToIgnoreCase(otherCategory.getCategoryName());
	}

	@Override
	public String[][] getTicketsAsArray() {
		String[][] ticketArray = new String[this.getTickets().size()][2];
		for (int i = 0; i < this.getTickets().size(); i++) {
			ticketArray[i][0] = Integer.toString(i);
			ticketArray[i][1] = this.getTickets().get(i).getTicketName();
		}
		return ticketArray;
	}

}
