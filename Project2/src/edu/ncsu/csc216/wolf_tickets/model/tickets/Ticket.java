/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.tickets;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * The Ticket class represents a ticket submitted to the WolfTicket system. Each
 * ticket will have its corresponding title and a list of the categories it is
 * in.
 * 
 * @author John Butterfield
 *
 */
public class Ticket {

	/** The ticket name */
	private String ticketName;

	/** The ticket description */
	private String ticketDescription;

	/** Boolean representing whether or not this ticket is active */
	private boolean active;

	/** List of Category objects this ticket is associated with */
	private ISwapList<AbstractCategory> categories;

	/**
	 * Construct the ticket.
	 * 
	 * @param ticketName        : name of the ticket
	 * @param ticketDescription : optional description of the ticket
	 * @param active            : boolean representing if the ticket is active or
	 *                          not
	 */
	public Ticket(String ticketName, String ticketDescription, boolean active) {
		setTicketName(ticketName);
		setTicketDescription(ticketDescription);
		setActive(active);
		categories = new SwapList<AbstractCategory>();

	}

	/**
	 * Get the ticket name.
	 * 
	 * @return the ticketName
	 */
	public String getTicketName() {
		return ticketName;
	}

	/**
	 * Set the ticket name.
	 * 
	 * @param ticketName the ticketName to set.
	 * @throws IllegalArgumentException if ticketName is null, empty, or contains
	 *                                  ",".
	 */
	public void setTicketName(String ticketName) {
		if (ticketName == null || ticketName.length() == 0 || ticketName.contains(",")) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		} else {
			this.ticketName = ticketName;
		}
	}

	/**
	 * Get the ticket description.
	 * 
	 * @return the ticketDescription
	 */
	public String getTicketDescription() {
		return ticketDescription;
	}

	/**
	 * Set the ticket description.
	 * 
	 * @param ticketDescription the ticketDescription to set
	 * @throws IllegalArgumentException if the description is null or contains
	 *                                  invalid character(s).
	 */
	public void setTicketDescription(String ticketDescription) {
		if (ticketDescription == null || ticketDescription.contains("#") || ticketDescription.contains("*")) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		} else {
			this.ticketDescription = ticketDescription;
		}
	}

	/**
	 * Get the active boolean of this ticket.
	 * 
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Set the active boolean to some value.
	 * 
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Returns the name of the AbstractCategory at index 0 or an empty string if
	 * there are no categories.
	 * 
	 * @return the category name at index 0 or an empty string if categories is
	 *         empty.
	 */
	public String getCategoryName() {
		if (categories == null || categories.size() == 0) {
			return "";
		}
		return categories.get(0).getCategoryName();
	}

	/**
	 * Add a category to the list of categories. If the category already exists,
	 * nothing is added.
	 * 
	 * @param category the category to add
	 * @throws IllegalArgumentException if the category being added is null.
	 */
	public void addCategory(AbstractCategory category) {
		if (category == null) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCategoryName().equals(category.getCategoryName())) {
				return;
			}
		}
		categories.add(category);

	}

	/**
	 * Complete the Ticket and notify the categories.
	 */
	public void completeTicket() {
		setActive(false);
		for (int i = 0; i < categories.size(); i++) {

			categories.get(i).completeTicket(this);

		}
		categories = new SwapList<AbstractCategory>();
	}

	/**
	 * Generate a string representation of this ticket for use in file i/o by
	 * GroupWriter.
	 */
	@Override
	public String toString() {
		String s = "";
		s = "* " + ticketName;
		if (active) {
			s = s + ",active\r\n";
		} else {
			s = s + "\r\n";
		}
		if (ticketDescription != null) {
			s = s + ticketDescription;
		}

		return s;

	}

}
