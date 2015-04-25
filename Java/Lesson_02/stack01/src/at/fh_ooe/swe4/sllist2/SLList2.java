/**
 * 
 */
package at.fh_ooe.swe4.sllist2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author wolfgang
 *
 */
public class SLList2<T> {

	
	private static class Node<T>{
		public Node<T> next;
		public T val;
		
		public Node(T val, Node<T> next){
			this.val = val;
			this.next = next;
		}
	}
	
	private Node<T> head;
	private Node<T> tail;
	
	public void prepend(T obj){
		head = new Node<T>(obj,head);
		if(tail == null ){
			tail = head;
		}
	}
	
	public static class SLListIterator<T> implements Iterator<T>{

		private Node<T> cur;
		
		public SLListIterator(Node<T> head) {
			this.cur = head;
		}
		
		@Override
		public boolean hasNext() {
			return cur != null;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		};

		@Override
		public T next() {
			if( cur == null){
				throw new NoSuchElementException();
			}
			T n = cur.val;
			cur = cur.next;
			return n;
		}
		
		
		
	}
	
	public SLListIterator<T> iterator(){
		return new SLListIterator<>(head);
	}
	
	public static void main(String[] args) {
		SLList2<String> sl2 = new SLList2<>();
		
		sl2.prepend("adsfasdfasdf");
		sl2.prepend("jjhadflakjsdfkl");
		
		Iterator<String> it = sl2.iterator();
		while(it.hasNext()){
			System.out.println("Item: "+ it.next());
		}

	}

}
