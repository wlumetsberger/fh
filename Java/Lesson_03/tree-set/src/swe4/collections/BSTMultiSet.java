package swe4.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BSTMultiSet <T extends Comparable<T>> implements SortedMultiSet<T>{

	public static class Node<T>{
		private T value;
		private Node<T> left, right;
		
		Node(T val, Node<T> left, Node<T> right){
			this.value = val;
			this.left = left;
			this.right = right;
		}
	}
	
	private Node<T> root;
	private int size;
	
	public BSTMultiSet() {
		this.root = null;
	}
	
	private static class BSTIterator<T> implements Iterator<T>{

		private Stack<Node<T>> unvisitedParents = new Stack();
		
		public BSTIterator(Node<T> root) {
			Node<T> next = root;
			while(next != null){
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
			if(!hasNext()){
				throw new NoSuchElementException("Stack is empty");
			}
			Node<T> cur = unvisitedParents.pop();
			Node<T> next = cur.right;
			while(next != null){
				unvisitedParents.push(next);
				next = next.left;
			}
			return cur.value;
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		return new BSTIterator<>(root);
	}

	private Node<T> addRecursive(Node<T> parent, T element){
		if(parent == null){
			size++;
			return new Node<T>(element, null, null);
		}
		if(element.compareTo(parent.value) < 0){
			parent.left = addRecursive(parent.left, element);
		}else{
			parent.right = addRecursive(parent.right, element);
		}
		return parent;
	}
	@Override
	public void add(T element) {
		root = addRecursive(root, element);
	}

	@Override
	public boolean contains(T element) {
		return get(element) != null;
	}

	@Override
	public T get(T element) {
		Node<T> t = root;
		while(t != null){
			int cmpRes = t.value.compareTo(element);
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
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		traverseInOrder(root, sb);
		sb.append("]");
		return sb.toString();
	}
	
	private void traverseInOrder(Node<T> t, StringBuffer sb){
		if(t != null){
			traverseInOrder(t.left, sb);
			if(sb.length() > 1) sb.append(",");
			sb.append(t.value);
			traverseInOrder(t.right, sb);
		}
	}
}