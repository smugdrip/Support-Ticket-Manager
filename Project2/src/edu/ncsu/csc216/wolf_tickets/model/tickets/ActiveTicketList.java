/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.tickets;

/**
 * ActiveTicketList maintains the active list of tickets for the WolfTicket
 * system.
 * 
 * @author John Butterfield
 *
 */
public class ActiveTicketList extends AbstractCategory {

	/** The name of the active tickets category will be "Active Tickets" */
	public static final String ACTIVE_TASKS_NAME = "Active Tickets";

	/**
	 * Construct the ActiveTicketList
	 */
	public ActiveTicketList() {
		super(ACTIVE_TASKS_NAME, 0);

	}

	/**
	 * Get the tickets as an array.
	 * 
	 * @return a 2D array representing the tickets in the ActiveTicketList
	 */
	public String[][] getTicketsAsArray() {
		String[][] ticketArray = new String[this.getTickets().size()][2];
		for (int i = 0; i < this.getTickets().size(); i++) {
			ticketArray[i][0] = this.getTickets().get(i).getCategoryName();
			ticketArray[i][1] = this.getTickets().get(i).getTicketName();
		}
		return ticketArray;
	}

	/**
	 * Add a ticket to the lists of tickets. A ticket must be active to be added to
	 * this list.
	 * 
	 * @param t : the ticket to be added
	 * @throws IllegalArgumentException if t is null or not active.
	 */
	@Override
	public void addTicket(Ticket t) {
		if (t == null || !t.isActive()) {
			throw new IllegalArgumentException("Cannot add ticket to Active Tickets.");
		}

		getTickets().add(t);
		t.addCategory(this);
	}

	/**
	 * Set the name of this category to some value. The name of the active ticket
	 * list must be "Active Tickets"
	 * 
	 * @param categoryName : the new name of the category
	 * @throws IllegalArgumentException if categoryName is null or not equal to
	 *                                  "Active Tickets"
	 */
	@Override
	public void setCategoryName(String categoryName) {
		if (categoryName == null || !categoryName.equals(ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("The Active Tickets list may not be edited.");
		}
		super.setCategoryName(categoryName);
	}

	/**
	 * Clears the ActiveTicketList of all Tickets.
	 */
	public void clearTickets() {
		for (int i = getTickets().size() - 1; i >= 0; i--) {
			getTickets().remove(i);
		}
	}

}
