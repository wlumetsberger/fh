package at.fhhagenberg.swe4.campinaAsAService.rmi.daos;

import java.util.ArrayList;
import java.util.List;

public class BaseDao<T> {
	
	public List<T> findAll(){
		System.out.println("baseDao findAll");
		return new ArrayList<T>();
	}
	
	public T update(T element){
		System.out.println("update elment: "+ element);
		return element;
	}
	
	public T insert(T element){
		System.out.println("insert elment:"+ element);
		return element;
	}
	
	public boolean delete(T element){
		System.out.println("delete elment: "+ element);
		return true;
	}
}
