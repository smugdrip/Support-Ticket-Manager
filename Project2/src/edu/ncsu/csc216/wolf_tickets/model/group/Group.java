/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.group;

import java.io.File;

import edu.ncsu.csc216.wolf_tickets.model.io.GroupWriter;
import edu.ncsu.csc216.wolf_tickets.model.tickets.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.tickets.ActiveTicketList;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tickets.model.util.SortedList;

/**
 * The Group class contains an ISortedList of all the Category objects, the
 * ActiveTicketList associated with all the active tickets, and operations to
 * manipulate these lists for use by the GUI.
 * 
 * @author John Butterfield
 *
 */
public class Group {

	/** List of categories in the group. */
	private ISortedList<Category> categories;

	/** ActiveTicketList that will hold all the active tickets in the Group. */
	private ActiveTicketList activeTicketList;

	/** Category that is actively being modified in the GUI by a user. */
	private AbstractCategory currentCategory;

	/** Name of the group. */
	private String groupName;

	/** Boolean representing if the group has been changed since last save. */
	private boolean isChanged;

	/**
	 * Construct a Group object.
	 * 
	 * @param groupName : the name of the group.
	 */
	public Group(String groupName) {
		setGroupName(groupName);
		categories = new SortedList<Category>();
		activeTicketList = new ActiveTicketList();
		currentCategory = activeTicketList;
		setChanged(true);
	}

	/**
	 * Set the name of this group to the parameter.
	 * 
	 * @param groupName : the new name
	 * @throws IllegalArgumentException if the name is null, an empty string, or "Active Tickets".
	 */
	private void setGroupName(String groupName) {
		if (groupName == null || groupName.length() == 0 || groupName.equals(ActiveTicketList.ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.groupName = groupName;
	}

	/**
	 * Save the group to a file.
	 * 
	 * @param groupFile : file to save
	 */
	public void saveGroup(File groupFile) {
		GroupWriter.writeGroupFile(groupFile, groupName, categories);
		setChanged(false);
	}

	/**
	 * Get the current category.
	 * 
	 * @return the currentCategory
	 */
	public AbstractCategory getCurrentCategory() {
		return currentCategory;
	}

	/**
	 * Set the current category. If the category is not found, the current category
	 * is set to the active ticket list.
	 * 
	 * @param categoryName the currentCategory to set
	 */
	public void setCurrentCategory(String categoryName) {
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCategoryName().equals(categoryName)) {
				currentCategory = categories.get(i);
				return;
			}
		}
		getActiveTicketList();
		currentCategory = activeTicketList;
	}

	/**
	 * Get the group name
	 * 
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Get if this Group has been changed since last save or not.
	 * 
	 * @return the isChanged
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * Set the changed flag to some value.
	 * 
	 * @param isChanged the isChanged to set
	 */
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	/**
	 * Add a category to the group
	 * 
	 * @param category : category to be added
	 * @throws IllegalArgumentException if the category being added is a duplicate
	 *                                  or has an invalid name.
	 */
	public void addCategory(Category category) {
		if (category == null
				|| category.getCategoryName().toLowerCase().equals(ActiveTicketList.ACTIVE_TASKS_NAME.toLowerCase())) {
			throw new IllegalArgumentException("Invalid name.");
		}
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCategoryName().toLowerCase().equals(category.getCategoryName().toLowerCase())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		categories.add(category);
		currentCategory = category;
		getActiveTicketList();
		setChanged(true);
	}

	/**
	 * Get the names of all the categories in a 2d array.
	 * 
	 * @return a 2D array of categories
	 */
	public String[] getCategoriesNames() {

		getActiveTicketList();

		String[] s = new String[categories.size() + 1];

		s[0] = ActiveTicketList.ACTIVE_TASKS_NAME;

		for (int i = 1; i < categories.size() + 1; i++) {
			if (!categories.get(i - 1).getCategoryName().equals(ActiveTicketList.ACTIVE_TASKS_NAME)) {
				s[i] = categories.get(i - 1).getCategoryName();
			}
		}
		return s;
	}

	/**
	 * A private helper method that maintains the sorted order of activeTicketList.
	 */
	private void getActiveTicketList() {
		activeTicketList.clearTickets();
		for (int c = 0; c < categories.size(); c++) {
			for (int t = 0; t < categories.get(c).getTickets().size(); t++) {
				if (categories.get(c).getTickets().get(t).isActive()) {
					activeTicketList.addTicket(categories.get(c).getTickets().get(t));
				}
			}
		}
	}

	/**
	 * Set the name of the currentCategory to some value.
	 * 
	 * @param categoryName : the new name of the current category.
	 * @throws IllegalArgumentException if categoryName is null or invalid or if the
	 *                                  current category is the list of active
	 *                                  tickets.
	 */
	public void editCategory(String categoryName) {
		if (categoryName == null
				|| categoryName.toLowerCase().equals(ActiveTicketList.ACTIVE_TASKS_NAME.toLowerCase()) || categoryName.toLowerCase().equals("Current Tickets".toLowerCase())) {
			throw new IllegalArgumentException("Invalid name.");
		}
		if (currentCategory instanceof ActiveTicketList) {
			throw new IllegalArgumentException("The Active Tickets list may not be edited.");
		}
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCategoryName().toLowerCase().equals(categoryName.toLowerCase())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		if (currentCategory.getCategoryName().toLowerCase().equals(categoryName.toLowerCase())) {
			throw new IllegalArgumentException("Invalid name.");
		}

		
		for (int i = 0; i < categories.size(); i++) {

			if (categories.get(i).getCategoryName().equals(currentCategory.getCategoryName())) {

				Category temp = categories.get(i);
				categories.remove(i);
				temp.setCategoryName(categoryName);
				categories.add(temp);
				break;
			}

		}

		currentCategory.setCategoryName(categoryName);

		setChanged(true);

	}

	/**
	 * Remove a category
	 */
	public void removeCategory() {
		if (currentCategory instanceof ActiveTicketList) {
			throw new IllegalArgumentException("The Active Tickets list may not be deleted.");
		}
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCategoryName().equals(currentCategory.getCategoryName())) {
				categories.remove(i);
				currentCategory = activeTicketList;
				getActiveTicketList();
				setChanged(true);
				return;
			}
		}
	}

	/**
	 * Add a ticket to the current category
	 * 
	 * @param t : ticket to be added
	 */
	public void addTicket(Ticket t) {
		if (!(currentCategory instanceof Category) || t == null) {
			return;
		}
		currentCategory.addTicket(t);
		getActiveTicketList();
		setChanged(true);
	}

	/**
	 * Edit a ticket with the given parameters.
	 * 
	 * @param idx               : index of ticket to be edited
	 * @param ticketName        : name of ticket
	 * @param ticketDescription : description of the ticket
	 * @param active            : boolean flag for if the ticket is active or not.
	 */
	public void editTicket(int idx, String ticketName, String ticketDescription, boolean active) {
		if (!(currentCategory instanceof Category)) {
			return;
		}
		currentCategory.getTicket(idx).setTicketName(ticketName);
		currentCategory.getTicket(idx).setTicketDescription(ticketDescription);
		currentCategory.getTicket(idx).setActive(active);
		getActiveTicketList();
	}

}
