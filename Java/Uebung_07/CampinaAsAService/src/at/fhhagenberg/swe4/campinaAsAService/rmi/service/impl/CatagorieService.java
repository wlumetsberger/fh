package at.fhhagenberg.swe4.campinaAsAService.rmi.service.impl;

import java.rmi.RemoteException;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.CatagorieDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Catagorie;
import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

public class CatagorieService implements ServiceInterface<Catagorie> {

	@Override
	public List<Catagorie> findAll() throws RemoteException {
		CatagorieDao dao = CatagorieDao.getInstance();
		return dao.findAll();

	}

	@Override
	public Catagorie find(Catagorie element) throws RemoteException {
		return CatagorieDao.getInstance().findById(element.getId(),
				Catagorie.class);
	}

	@Override
	public boolean update(Catagorie element) throws RemoteException {
		return CatagorieDao.getInstance().update(element);
	}

	@Override
	public boolean insert(Catagorie element) throws RemoteException {
		return CatagorieDao.getInstance().insert(element);
	}

	@Override
	public boolean delete(Catagorie element) throws RemoteException {
		return CatagorieDao.getInstance().delete(element);
	}

}
