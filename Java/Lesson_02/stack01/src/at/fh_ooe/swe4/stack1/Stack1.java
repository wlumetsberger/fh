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
	
	public void push(Object o ) throws StackException{
		if(isFull()){
			throw new StackException("Stack is full");
		}
		top ++;
		data[top] = o;
	}
	public Object pop() throws StackException{
		if(isEmpty()){
			throw new StackException("Stack is empty");
		}
		top--;
		return data[top +1];
	}
	
	public static void main(String[] args){
		Stack1 s = new Stack1(10);
		try{
			s.push(1);
			s.push(new MyClass());
			s.push("Hugo");
		}catch(StackException ex){
			ex.printStackTrace();
			
		}
		
	}
}
