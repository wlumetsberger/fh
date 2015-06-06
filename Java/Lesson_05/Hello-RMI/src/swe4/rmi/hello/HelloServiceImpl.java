package swe4.rmi.hello;

import java.rmi.RemoteException;
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
	public int nextId()
			throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
