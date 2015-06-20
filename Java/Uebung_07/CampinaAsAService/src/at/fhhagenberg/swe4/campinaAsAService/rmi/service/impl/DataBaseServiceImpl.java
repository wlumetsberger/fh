package at.fhhagenberg.swe4.campinaAsAService.rmi.service.impl;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.BaseDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.BaseModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

public class DataBaseServiceImpl<T extends BaseModel> implements ServiceInterface<T> {

	private BaseDao<T> dao;
	
	public DataBaseServiceImpl() {
			dao = new BaseDao();
	}
	
	@Override
	public List<T> findAll() throws RemoteException {
		return dao.findAll();
	}

	@Override
	public T update(T element)throws RemoteException {
		return dao.update(element);
	}

	@Override
	public T insert(T element)throws RemoteException {
		return dao.insert(element);
	}

	@Override
	public boolean delete(T element) throws RemoteException{
		return dao.delete(element);
	}
	
	private static int getPort(String host_port){
		int idx = host_port.lastIndexOf(":");
		if(idx == -1)
			return 1099;
		else
			return Integer.parseInt(host_port.substring(idx+1));
	}
	
	public static void main(String[] args) {
		
		 // version 1: start rmiRegistry via commandLine -> files have to be in classpath of rmiRegistry process
		String host_port = "localhost:4707";
		try{
			LocateRegistry.createRegistry(getPort(host_port));
			ServiceInterface<BaseModel> service = new DataBaseServiceImpl<BaseModel>();
			Remote serviceStrub = UnicastRemoteObject.exportObject(service,0);
			
			Naming.rebind("rmi://"+ host_port +"/DataBaseService", serviceStrub);
			
			System.out.println("Service available on: rmi://"+ host_port +"/DataBaseService");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
