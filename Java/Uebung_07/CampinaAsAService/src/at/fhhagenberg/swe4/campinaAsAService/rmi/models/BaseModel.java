package at.fhhagenberg.swe4.campinaAsAService.rmi.models;

import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.BaseDao;

public abstract class BaseModel {
	
	public abstract BaseDao getDao();

}
