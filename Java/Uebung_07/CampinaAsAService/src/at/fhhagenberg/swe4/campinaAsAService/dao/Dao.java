package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

/**
 * 
 * @author Wolfgang Todo create abstract dao which holds the service for rmi
 * @param <T>
 */
public interface Dao<T, E> {

	public ServiceInterface<E> getServiceInterface();

	public List<T> findAll();

	public boolean save(T element);

	public T get(T element);

	public boolean remove(T element);
}
