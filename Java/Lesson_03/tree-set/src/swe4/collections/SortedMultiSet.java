package swe4.collections;

public interface SortedMultiSet<T  extends Comparable<T>> extends Iterable<T>{
	void add(T element);
	boolean contains(T element);
	T get(T element);
	int size();
}