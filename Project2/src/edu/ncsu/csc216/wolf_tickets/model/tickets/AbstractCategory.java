/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.tickets;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * The AbstractCategory class holds the common functionality between Category
 * and ActiveTicketList. Each category or active ticket list will have a list of
 * Ticket objects and methods to manipulate this list.
 * 
 * @author John Butterfield
 *
 */
public abstract class AbstractCategory {

	/** The name of this category */
	private String categoryName;

	/** The number of completed tickets for this category */
	private int completedCount;

	/** The list of tickets for this category */
	private ISwapList<Ticket> tickets;

	/**
	 * Set the fields of this AbstractCategory
	 * 
	 * @param categoryName   : Name of the category
	 * @param completedCount : Count of completed tickets
	 */
	public AbstractCategory(String categoryName, int completedCount) {
		setCategoryName(categoryName);
		setCompletedCount(completedCount);
		this.tickets = new SwapList<Ticket>();
	}

	/**
	 * Set the completed count.
	 * 
	 * @param completedCount the value to set completedCount to.
	 * @throws IllegalArgumentException if count is less than 0.
	 */
	private void setCompletedCount(int completedCount) {
		if (completedCount < 0) {
			throw new IllegalArgumentException("Invalid completed count.");
		} else {
			this.completedCount = completedCount;
		}
	}

	/**
	 * Get the name of the category.
	 * 
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Set the category name.
	 * 
	 * @param categoryName the categoryName to set
	 * @throws IllegalArgumentException if the name is null or an empty string
	 */
	public void setCategoryName(String categoryName) {
		if (categoryName == null || categoryName.length() == 0) {
			throw new IllegalArgumentException("Invalid name.");
		} else {
			this.categoryName = categoryName;
		}
	}

	/**
	 * Get the count of completed tickets for this category.
	 * 
	 * @return the completedCount
	 */
	public int getCompletedCount() {
		return completedCount;
	}

	/**
	 * Get the list of tickets for this category.
	 * 
	 * @return the tickets
	 */
	public ISwapList<Ticket> getTickets() {
		return tickets;
	}

	/**
	 * Add a ticket to the category.
	 * 
	 * @param t : the ticket to add
	 * @throws IllegalArgumentException if t is null
	 */
	public void addTicket(Ticket t) {

		if (t == null) {
			throw new IllegalArgumentException();
		}
		tickets.add(t);
		t.addCategory(this);
	}

	/**
	 * Remove a ticket from the specified element.
	 * 
	 * @param idx : index of element to be removed.
	 * @return the removed Ticket
	 */
	public Ticket removeTicket(int idx) {

		return tickets.remove(idx);

	}

	/**
	 * Get the ticket at the specified index.
	 * 
	 * @param idx the index of the ticket to get
	 * @return The ticket specified by index.
	 */
	public Ticket getTicket(int idx) {

		return tickets.get(idx);

	}

	/**
	 * Finds the given Ticket in the list and removes it then increments
	 * completedCount by one.
	 * 
	 * @param t : the ticket to complete.
	 * @throws IllegalArgumentException if t is null.
	 */
	public void completeTicket(Ticket t) {
		if (t == null) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < tickets.size(); i++) {
			if (t == tickets.get(i)) {
				removeTicket(i);
				completedCount++;
				return;
			}
		}

	}

	/**
	 * An abstract method that returns a 2D String array that represents this
	 * category. Functionality is left for child classes to specify.
	 * 
	 * @return A 2D array representing the tickets in a category
	 */
	public abstract String[][] getTicketsAsArray();

}
