package edu.ncsu.csc216.wolf_tickets.model.util;

/**
 * SwapList is a custom implementation of an ArrayList to hold Tickets for the
 * WolfTicket System. SwapList holds functionality specific to the WolfTicket
 * system such as moveUp().
 * 
 * @author John Butterfield
 * @param <E> Default data type
 *
 */
public class SwapList<E> implements ISwapList<E> {

	/** Initial capacity of the list will be 10. */
	private static final int INITIAL_CAPACITY = 10;

	/** Array to hold data. **/
	private E[] list;

	/** Size of the list. */
	private int size;

	/**
	 * Construct a SwapList.
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		this.size = 0;
		this.list = (E[]) new Object[INITIAL_CAPACITY];
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

		E object = list[idx];

		for (int i = idx; i < size; i++) {
			list[i] = list[i + 1];
		}

		list[size - 1] = null;
		size--;
		return object;

	}

	/**
	 * Move the data at the specified idx to idx - 1. (moving up in the list).
	 * 
	 * @param idx the index of the data to be moved forward.
	 * @throws IndexOutOfBoundsException if idx is out of bounds for the list.
	 */
	@Override
	public void moveUp(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		} else if (idx == 0) {
			return;
		}
		E moveThisUp = list[idx];
		E moveThisDown = list[idx - 1];
		list[idx] = moveThisDown;
		list[idx - 1] = moveThisUp;

	}

	/**
	 * Move the data at the specified idx to idx + 1. (moving down in the list).
	 * 
	 * @param idx the index of the data to be moved back.
	 * @throws IndexOutOfBoundsException if idx is out of bounds for the list.
	 */
	@Override
	public void moveDown(int idx) {
		if (idx < 0 || idx >= size) {

			throw new IndexOutOfBoundsException("Invalid index.");
		} else if (idx == size - 1) {
			return;

		}
		E moveThisDown = list[idx];
		E moveThisUp = list[idx + 1];
		list[idx] = moveThisUp;
		list[idx + 1] = moveThisDown;

	}

	/**
	 * Move the data at the specified idx to idx = 0. (moving to the front of the
	 * list).
	 * 
	 * @param idx the index of the data to be moved to the front.
	 * @throws IndexOutOfBoundsException if idx is out of bounds for the list.
	 */
	@Override
	public void moveToFront(int idx) {
		if (idx < 0 || idx >= size) {

			throw new IndexOutOfBoundsException("Invalid index.");
		} else if (idx == 0) {
			return;
		}
		E moveToFront = list[idx];
		for (int i = idx; i > 0; i--) {
			list[i] = list[i - 1];

		}
		list[0] = moveToFront;
	}

	/**
	 * Move the data at the specified idx to idx = size - 1. (moving to the end of
	 * the list).
	 * 
	 * @param idx the index of the data to be moved to the back.
	 * @throws IndexOutOfBoundsException if idx is out of bounds for the list.
	 */
	@Override
	public void moveToBack(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		} else if (idx == size - 1) {
			return;
		}
		E moveToBack = list[idx];
		for (int i = idx; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = moveToBack;
	}

	/**
	 * Get the data at the specified index
	 * 
	 * @param idx the index to be got.
	 * @return the data at idx.
	 * @throws IndexOutOfBoundsException if idx is out of bounds for the list.
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		return list[idx];

	}

	/**
	 * Get the number of elements in this list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Add an element to the end of the list.
	 * 
	 * @param element the data to be added to the list
	 * @throws NullPointerException if element is null.
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}

		if (size == list.length) {
			growArray();
		}

		list[size] = element;

		size++;
	}

	/**
	 * Grows the size of list to be twice its current capacity.
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] temp = (E[]) new Object[list.length * 2];

		for (int i = 0; i < list.length; i++) {
			temp[i] = list[i];
		}

		list = temp;
	}
}
