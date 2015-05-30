package at.fhhagenberg.swe4.uebung05.interfaces;

import java.util.Comparator;
import java.util.Iterator;

public interface SortedSet<T> extends Iterable<T> {
	/**
	 * add new element to the tree
	 * @param element
	 * @return
	 */
	boolean add(T element);
	/**
	 * get element from Tree
	 * returns null if element does not exist
	 * @param element
	 * @return
	 */
	T get(T element);
	/***
	 * checks if tree contains element
	 * @param element
	 * @return
	 */
	boolean contains(T element);
	/**
	 * returns size of tree
	 * @return
	 */
	int size();
	/**
	 * get first element of tree
	 * @return
	 */
	T first();
	/**
	 * get last element of tree
	 * @return
	 */
	T last();
	/**
	 * returns comparator or null if no comparator available
	 * @return
	 */
	Comparator<T> comparator();
	/**
	 * retuns Iterator 
	 */
	Iterator<T> iterator();
}
