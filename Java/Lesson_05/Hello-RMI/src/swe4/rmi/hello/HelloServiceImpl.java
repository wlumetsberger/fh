package swe4.rmi.hello;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;

public class HelloServiceImpl implements HelloService{

	private int counter = 0;
	
	private static int getPort(String host_port){
		int idx = host_port.lastIndexOf(":");
		if(idx == -1)
			return 1099;
		else
			return Integer.parseInt(host_port.substring(idx+1));
	}
	@Override
	public LocalDateTime getServerDate()
			throws RemoteException {
		return LocalDateTime.now();
	}

	@Override
	public synchronized int nextId()
			throws RemoteException {
		int value = counter;
		try{
			Thread.sleep(100);
		}catch(InterruptedException e){
			
		}
		counter = value +1;
		System.out.printf("NextId=%d , (thread: %d) %n",counter, Thread.currentThread().getId());
		return counter;
	}
	
	public static void main(String[] args) {
		
		 // version 1: start rmiRegistry via commandLine -> files have to be in classpath of rmiRegistry process
		String host_port = args[0];
		try{
			LocateRegistry.createRegistry(getPort(host_port));
	
		}catch(Exception ex){
			
		}
	}
}
