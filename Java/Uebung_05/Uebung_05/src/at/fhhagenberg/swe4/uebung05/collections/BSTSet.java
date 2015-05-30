package at.fhhagenberg.swe4.uebung05.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import at.fhhagenberg.swe4.uebung05.interfaces.SortedTreeSet;

public class BSTSet<T> extends AbstractSet<T> {

	/**
	 * Node Inner Class
	 */
	private static class Node<T> {
		private T value;
		private Node<T> left, right;

		Node(T val, Node<T> left, Node<T> right) {
			this.value = val;
			this.left = left;
			this.right = right;
		}
	}

	/**
	 * Private Class for Iterator
	 */
	private static class BSTIterator<T> implements Iterator<T> {

		private Stack<Node<T>> unvisitedParents = new Stack();

		public BSTIterator(Node<T> root) {
			Node<T> next = root;
			while (next != null) {
				unvisitedParents.push(next);
				next = next.left;
			}
		}

		@Override
		public boolean hasNext() {
			return !unvisitedParents.empty();
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException("Stack is empty");
			}
			Node<T> cur = unvisitedParents.pop();
			Node<T> next = cur.right;
			while (next != null) {
				unvisitedParents.push(next);
				next = next.left;
			}
			return cur.value;
		}
	}
	
	private Node<T> root;
	public BSTSet(Comparator<T> comp){
		super(comp);
	}
	public BSTSet(){
		super();
	}
	private Node<T> addRecursive(Node<T> parent, T element){
		if(parent == null){
			incSize();
			return new Node<T>(element, null, null);
		}
		if(this.compareElements(element,parent.value) < 0){
			parent.left = addRecursive(parent.left, element);
		}else{
			parent.right = addRecursive(parent.right, element);
		}
		return parent;
	}
	@Override
	public boolean add(T element) {
		if(this.contains(element)){
			System.out.println("Element allready in Set");
			return false;
		}
		root = addRecursive(this.root, element);
		return true;
	}

	@Override
	public T get(T element) {
		Node<T> t = root;
		while(t != null){
			int cmpRes = this.compareElements(t.value, element);
			if(cmpRes == 0){
				return t.value;
			}
			else if(cmpRes > 0 ){
				t = t.left;
			}else{
				t = t.right;
			}
		}
		return null;
	}

	@Override
	public T first() {
		if(root == null){
			throw new NoSuchElementException("Empty Set");
		}
		Node<T> tmp = this.root;
		while(tmp.left != null){
			tmp = tmp.left;
		}
		return tmp.value;
	}

	@Override
	public T last() {
		if(root == null){
			throw new NoSuchElementException("Empty Set");
		}
		Node<T> tmp = this.root;
		while(tmp.right != null){
			tmp = tmp.right;
		}
		return tmp.value;
	}

	@Override
	public Iterator<T> iterator() {
		return new BSTIterator(this.root);
	}

	


}
