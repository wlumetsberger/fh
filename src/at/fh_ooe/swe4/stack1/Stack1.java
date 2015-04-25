package at.fh_ooe.swe4.stack1;

class MyClass{
	public String toString(){
		return "Instance of MyClass";
	}
}

public class Stack1 {

	private int max;
	private int top;
	private Object[] data;
	
	public Stack1() {
		this(10);
	}
	
	public Stack1(int max){
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
	
	public void push(Object o ){
		if(isFull()){
			
		}
		top ++;
		data[top] = o;
	}
	public static void main(String[] args){
		
	}
}
