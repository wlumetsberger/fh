/**
 * 
 */
package at.fh_ooe.swe4.stack3;


import java.util.stream.Stream;

import at.fh_ooe.swe4.stack1.Stack1;
import at.fh_ooe.swe4.stack1.StackException;

/**
 * @author wolfgang
 *
 */

class MyClass{
	public String toString(){
		return "Instance of MyClass";
	}
}


public class Stack3<T> {

	private int max;
	private int top;
	private Object[] data;
	
	public Stack3() {
		this(10);
	}
	
	public Stack3(int max){
		this.max = max;
		data = new Object[max];
		top = -1;
	}
	
	public boolean isEmpty(){
		return top <= 0;
	}
	
	public boolean isFull(){
		return top == max - 1;
	}
	
	public void push(T o ) throws StackException{
		if(isFull()){
			throw new StackException("Stack is full");
		}
		top ++;
		data[top] = o;
	}
	public T pop() throws StackException{
		if(isEmpty()){
			throw new StackException("Stack is empty");
		}
		top--;
		return (T)data[top +1];
	}
	
	public static void main(String[] args){
		Stack3 s = new Stack3(10);
		Stack3<String> s2 = new Stack3<>(); // --> >= 1.7
		try{
			s.push(1);
			s.push("Hugo");
			s2.push("New String");
			s2.push("Another String");
		}catch(StackException ex){
			ex.printStackTrace();
			
		}
		
	}
}
