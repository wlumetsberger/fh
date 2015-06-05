package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.util.List;
/**
 * 
 * @author Wolfgang
 *
 * @param <T>
 */
public interface Dao<T> {
	public List<T> findAll();
	public T save(T element);
	public T get(T element);
	public T remove(T element);
}
