package at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServiceInterface<T> extends Remote {

	public List<T> findAll() throws RemoteException;
	public boolean update(T element) throws RemoteException;
	public boolean insert(T element) throws RemoteException;
	public boolean delete(T element) throws RemoteException;
	public T find(T element) throws RemoteException;

}
