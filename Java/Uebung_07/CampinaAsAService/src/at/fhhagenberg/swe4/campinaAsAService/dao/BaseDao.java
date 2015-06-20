package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.rmi.models.BaseModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

public abstract class BaseDao<T,E> implements Dao<T> {

	private ServiceInterface<E> service;
	
	public BaseDao() {
		String host_port ="localhost:4707";
		try {
			service = (ServiceInterface<E>) Naming.lookup("rmi://"+ host_port +"/DataBaseService");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected ServiceInterface<E> getServiceInterface(){
		return this.service;
	}
}
