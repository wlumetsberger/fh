package at.fhhagenberg.swe4.campinaAsAService.models;

import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;

/**
 * Interface for our Models, Pojos to provide a DAO
 * 
 * @author Wolfgang
 *
 */
public interface BaseViewModel {
	/**
	 * returns a new Dao for the current Model ---> dao factory
	 * 
	 * @return
	 */
	public Dao getDao();
	public Class getDbModell();

}
