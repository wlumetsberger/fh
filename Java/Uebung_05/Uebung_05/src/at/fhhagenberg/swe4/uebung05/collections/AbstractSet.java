package at.fhhagenberg.swe4.uebung05.collections;

import java.util.Comparator;

import at.fhhagenberg.swe4.uebung05.interfaces.SortedTreeSet;

public abstract class AbstractSet<T> implements SortedTreeSet<T>{

	private int size;
	private Comparator<T> comparator;
	private int height;
	
	/**
	 * Constructor without Comparator
	 */
	public AbstractSet() {
		this(null);
	}
	/**
	 * Constructor with Comparator
	 * @param comp
	 */
	public AbstractSet(Comparator<T> comp) {
		this.comparator = comp;
		this.size = 0;
		this.height = -1;
	}
	/**
	 * Compare two Elements with given Comparator
	 * or natural Comparator if specific is null
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	protected int compareElements(final T obj1, final T obj2){
		if(obj1 == null && obj2 == null){
			return 0;
		}else if(obj1 == null){
			return -1;
		}else if(obj2 == null){
			return 1;
		}
		if(comparator != null){
			return comparator.compare(obj1, obj2);
		}
		return((Comparable<T>)obj1).compareTo(obj2);
	}
	
	protected void incSize(){
		this.size++;
	}
	@Override
	public int size() {
		return this.size;
	}
	@Override
	public Comparator<T> comparator() {
		return this.comparator;
	}
	@Override
	public int height() {
		return this.height;
	}
	protected void setHeight(int height){
		this.height = height;
	}
	@Override
	public boolean contains(T element) {
		return get(element) != null;
	}
}
