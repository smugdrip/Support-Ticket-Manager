/**
 * 
 */
package edu.ncsu.csc216.wolf_tickets.model.util;

/**
 * The SortedList class maintains a sorted Linked List that holds Categories for
 * the WolfTicket system. The list will be sorted alphabetically.
 * 
 * @author John Butterfield
 * @param <E> : Default data type.
 *
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {

	/** Size of this sorted list */
	private int size;

	/** The memory address of the first node in the list */
	private ListNode front;

	/**
	 * Construct a sorted list.
	 */
	public SortedList() {
		this.front = null;
		this.size = 0;
	}

	/**
	 * Add an element to the list in sorted oder.
	 * 
	 * @param element the data to add to the list.
	 * @throws NullPointerException     if element is null.
	 * @throws IllegalArgumentException if element is a duplicate.
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}

		if (front == null) {
			front = new ListNode(element, null);
			size++;
			return;
		}

		if (this.contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}

		ListNode current = front;
		if (element.compareTo(front.data) < 0) {
			front = new ListNode(element, front);
		} else {
			current = front;
			while (current.next != null && current.next.data.compareTo(element) < 0) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		}
		size++;

	}

	/**
	 * Remove the data at the specifed index.
	 * 
	 * @param idx the index that will be removed.
	 * @return the data that was removed.
	 * @throws IndexOutOfBoundsException if the id is out of bounds.
	 */
	@Override
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		E value = null;
		if (idx == 0) {
			value = front.data;
			front = front.next;
		} else {

			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			value = current.next.data;
			current.next = current.next.next;
		}
		size--;
		return value;
	}

	/**
	 * Check if the list contains a specific element.
	 * 
	 * @param element the data to check for.
	 * @return true if the list does contain the data, otherwise false.
	 */
	@Override
	public boolean contains(E element) {
		if (element == null) {
			return false;
		}

		ListNode temp = front;
		for (int i = 0; i < size; i++) {
			if (temp != null) {
				if (temp.data.equals(element)) {
					return true;
				}
				temp = temp.next;
			}
		}
		return false;
	}

	/**
	 * Get the data at the specified index.
	 * 
	 * @param idx the index to get
	 * @return the data at idx
	 * @throws IndexOutOfBoundsException if idx is out for range for the list
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		ListNode current = front;

		for (int i = 0; i < idx; i++) {
			if (current != null) {
				current = current.next;
			}
		}

		if (current != null) {
			return current.data;
		} else {
			return null;
		}
	}

	/**
	 * Get the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * A node that can link with other nodes to create a linked list.
	 * 
	 * @author John Butterfield
	 */
	private class ListNode {

		/** The data contained in the node */
		public E data;
		/** The next node in the list */
		public ListNode next;

		/**
		 * Constructor for a node which initializes the private fields
		 * 
		 * @param data : the data
		 * @param next : the next node
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}
