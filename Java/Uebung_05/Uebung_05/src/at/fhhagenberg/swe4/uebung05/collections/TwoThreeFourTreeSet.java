package at.fhhagenberg.swe4.uebung05.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class TwoThreeFourTreeSet<T> extends AbstractSet<T>{

	private TwoThreeFourTreeNode<T> root;
	
	public TwoThreeFourTreeSet() {
		super();
	}
	public TwoThreeFourTreeSet(Comparator<T> comp){
		super(comp);
	}
	/**
	 * add new Element to Set
	 */
	@Override
	public boolean add(T element){
		if(root == null){
			root = new TwoThreeFourTreeNode<T>(this.comparator());
			root.addItem(element);
			incSize();
			this.setHeight(0);
			return true;
		}
		TwoThreeFourTreeNode<T> curNode = root;
		int level = 0;
		while(curNode != null){
			if(curNode.isFull()){
				TwoThreeFourTreeNode<T> result = curNode.split();
				if(result.getParent() == null){
					root = result;
					level = 0;
				}else{
					level--;
				}
				curNode = result;
			}else{
				if(curNode.contains(element)){
					return false;
				}
				if(curNode.isLeaf()){
					curNode.addItem(element);
					incSize();
					if(level > height()){
						this.setHeight(level);
					}
					return true;
				}else{
					curNode = curNode.getChild(element);
					level++;
				}
			}
		}
		return false;
	}

	/**
	 * get Element from Set
	 */
	@Override
	public T get(T element) {
		TwoThreeFourTreeNode<T> curNode  = root;
		while(curNode != null){
			if(curNode.contains(element)){
				return curNode.getItem(element);
			}
			curNode = curNode.getChild(element);
		}
		return null;
	}
	
	/**
	 * return first Element of Set
	 */
	@Override
	public T first() {
		if(root == null){
			throw new NoSuchElementException();
		}
		TwoThreeFourTreeNode<T> tmp = root;
		while(tmp != null && tmp.getFirstChild() != null){
			tmp = tmp.getFirstChild();
		}
		return tmp.getItem(0);
	}

	/**
	 * return last Element of Set
	 */
	@Override
	public T last() {
		if(root == null){
			throw new NoSuchElementException();
		}
		TwoThreeFourTreeNode<T> tmp = root;
		while(tmp != null && tmp.getLastChild() != null){
			tmp = tmp.getLastChild();
		}
		return tmp.getItem(tmp.getItemSize()-1);
	}

	/**
	 * return Iterator for Collection
	 */
	@Override
	public Iterator<T> iterator() {
		Stack<T> help = new Stack<T>();
		if(root != null){
			root.addValueToStack(help);
		}
		
		return help.iterator();
	}
	/**
	 * to string method to print out tree
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Iterator<T> it = this.iterator();
		while(it.hasNext()){
			T value = (T) it.next();
			builder.append(value);
		}
		return builder.toString();	
	}
	
	

}
