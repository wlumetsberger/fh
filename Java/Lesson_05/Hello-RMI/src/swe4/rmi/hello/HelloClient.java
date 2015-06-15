package swe4.rmi.hello;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class HelloClient {
	private static final int NO_ITERATIONS = 1000;
	
	private static void doTimings(HelloService service) throws RemoteException{
		long nanoStart = System.nanoTime();
		for(int i=0; i< NO_ITERATIONS; i++){
			service.getServerDate();
		}
		long nanoEnd = System.nanoTime();
		double timePerCall = (double) (nanoEnd - nanoStart) / NO_ITERATIONS / 1e9;
		System.out.println("Time per Call: "+ timePerCall);
	}
	public static void main(String[] args) {
		String host_port = args[0];
		try{
			System.out.println("Lookup for HelloService");
			HelloService helloServiceProxy = (HelloService) Naming.lookup("rmi://"+ host_port +"/HelloService");
			System.out.println("helloServiceProxy: "+ helloServiceProxy.getClass().getName());
			
			System.out.println("Client-Date: "+ LocalDateTime.now());
			System.out.println("Server-Date: "+ helloServiceProxy.getServerDate());
			
			for(int i=0; i<NO_ITERATIONS; i++){
				System.out.println("NextId=" + helloServiceProxy.nextId());
			}
			
			System.out.println("------ TIMINGS Start------");
			HelloClient.doTimings(helloServiceProxy);
			System.out.println("------ TIMINGS End------");
			
		}catch(Exception ex){ 
			ex.printStackTrace();
		}
	}
}
