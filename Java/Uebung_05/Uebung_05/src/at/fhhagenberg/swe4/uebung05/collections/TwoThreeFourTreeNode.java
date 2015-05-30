package at.fhhagenberg.swe4.uebung05.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class TwoThreeFourTreeNode<T> {

	private TwoThreeFourTreeNode<T> parent;
	private ArrayList<TwoThreeFourTreeNode<T>> children;
	private ArrayList<T> items;
	private Comparator<T> comparator;

	public TwoThreeFourTreeNode() {
		this(null);
	}

	public TwoThreeFourTreeNode(Comparator<T> comp) {
		this(comp, null);
	}

	public TwoThreeFourTreeNode(Comparator<T> comp,
			TwoThreeFourTreeNode<T> parent) {
		children = new ArrayList(4);
		items = new ArrayList(3);
		this.comparator = comp;
		this.parent = parent;

	}

	/**
	 * connect child
	 * 
	 * @param left
	 * @param right
	 */
	public void connectChilds(TwoThreeFourTreeNode<T> left,
			TwoThreeFourTreeNode<T> right) {
		if (left != null) {
			left.parent = this;
			children.add(0, left);
		}
		if (right != null) {
			right.parent = this;
			children.add(1, right);
		}
	}

	/**
	 * return child for given index
	 * 
	 * @param index
	 * @return
	 */
	public TwoThreeFourTreeNode<T> getChild(int index) {
		if (index < children.size()) {
			return children.get(index);
		}
		return null;
	}

	/**
	 * return index of given child
	 * 
	 * @param element
	 * @return
	 */
	public int getChildIndex(T element) {
		if (this.compare(items.get(0), element) > 0) {
			return 0;
		} else if (items.size() == 1
				|| (this.compare(items.get(1), element) > 0)) {
			return 1;
		} else if (items.size() == 2
				|| (this.compare(items.get(2), element) > 0)) {
			return 2;
		}
		return 3;

	}

	/**
	 * return child for item
	 * 
	 * @param item
	 * @return
	 */
	public TwoThreeFourTreeNode<T> getChild(T item) {
		if (children.size() <= 0) {
			return null;
		}
		return children.get(getChildIndex(item));
	}

	/**
	 * return Parent
	 * 
	 * @return
	 */
	public TwoThreeFourTreeNode<T> getParent() {
		return this.parent;
	}

	/**
	 * check if node is leaf
	 * 
	 * @return
	 */
	public boolean isLeaf() {
		return children.size() == 0;
	}

	/**
	 * check if list is full
	 * 
	 * @return
	 */
	public boolean isFull() {
		return items.size() == 3;
	}

	/**
	 * split node
	 * 
	 * @return
	 */
	public TwoThreeFourTreeNode<T> split() {
		TwoThreeFourTreeNode<T> tmp = this.parent;
		if (tmp == null) {
			tmp = new TwoThreeFourTreeNode<T>(this.comparator);
		}
		tmp.addItem(items.get(1));

		TwoThreeFourTreeNode<T> left = new TwoThreeFourTreeNode<T>(
				this.comparator, tmp);
		left.addItem(items.get(0));
		left.connectChilds(getChild(0), getChild(1));
		TwoThreeFourTreeNode<T> right = new TwoThreeFourTreeNode<T>(
				this.comparator, tmp);
		right.addItem(items.get(2));
		right.connectChilds(getChild(2), getChild(3));

		int childIndex = tmp.getChildIndex(items.get(0));
		if (childIndex < tmp.children.size()) {
			tmp.children.remove(childIndex);
		}
		tmp.children.add(childIndex, right);
		tmp.children.add(childIndex, left);

		return tmp;
	}

	/**
	 * add new Item
	 * 
	 * @param item
	 */
	public void addItem(T item) {
		items.add(item);
		items.sort(this.comparator);
	}

	/**
	 * return item
	 * 
	 * @param item
	 * @return
	 */
	public T getItem(T item) {
		int index = items.indexOf(item);
		if (index == -1) {
			throw new NoSuchElementException();
		}
		return items.get(index);
	}

	/**
	 * get Item by Index
	 * 
	 * @param index
	 * @return
	 */
	public T getItem(int index) {
		if (index == -1) {
			throw new NoSuchElementException();
		}
		return items.get(index);
	}

	/**
	 * return first child of
	 * 
	 * @return
	 */
	public TwoThreeFourTreeNode<T> getFirstChild() {
		return getChild(0);
	}

	/**
	 * get last child
	 * 
	 * @return
	 */
	public TwoThreeFourTreeNode<T> getLastChild() {
		if (children.size() <= 0) {
			return null;
		}
		return children.get(children.size() - 1);
	}

	/**
	 * return size of current stored node items
	 * 
	 * @return
	 */
	public int getItemSize() {
		return items.size();
	}

	/**
	 * check if item is in list
	 * 
	 * @param item
	 * @return
	 */
	public boolean contains(T item) {
		return items.contains(item);
	}

	/**
	 * compare function to compare our node
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	private int compare(final T obj1, final T obj2) {
		if (obj1 == null && obj2 == null) {
			return 0;
		} else if (obj1 == null) {
			return -1;

		} else if (obj2 == null) {
			return -2;
		}
		if (comparator != null) {
			return comparator.compare(obj1, obj2);
		}
		return ((Comparable<T>) obj1).compareTo(obj2);
	}

	/**
	 * Add values recursive to a Stack
	 * 
	 * @param stack
	 */
	public void addValueToStack(Stack<T> stack) {
		if (this.children.size() != 0) {
			for (int i = 0; i < items.size(); i++) {
				children.get(i).addValueToStack(stack);
				stack.push(items.get(i));
			}
			children.get(children.size() - 1).addValueToStack(stack);
		} else {
			stack.addAll(items);
		}
	}

	/**
	 * print node structur
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("/");
		for (T val : items) {
			builder.append(val);
			builder.append("/");
		}
		builder.append(System.lineSeparator());
		return builder.toString();
	}

}
