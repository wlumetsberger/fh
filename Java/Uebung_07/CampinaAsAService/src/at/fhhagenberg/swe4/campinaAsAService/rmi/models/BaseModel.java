package at.fhhagenberg.swe4.campinaAsAService.rmi.models;

import java.io.Serializable;

import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.BaseDao;

public abstract class BaseModel implements Serializable {

	public abstract BaseDao getDao();
	public abstract Object getId();
	public abstract Class getViewModell();

}
