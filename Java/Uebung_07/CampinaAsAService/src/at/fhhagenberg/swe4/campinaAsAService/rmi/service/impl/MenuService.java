package at.fhhagenberg.swe4.campinaAsAService.rmi.service.impl;

import java.rmi.RemoteException;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.MenuDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Menu;
import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

public class MenuService implements ServiceInterface<Menu> {

	@Override
	public List<Menu> findAll() throws RemoteException {
		return MenuDao.getInstance().findAll();
	}

	@Override
	public boolean update(Menu element) throws RemoteException {
		return MenuDao.getInstance().update(element);
	}

	@Override
	public Menu find(Menu element) throws RemoteException {
		return MenuDao.getInstance().findById(element.getId(), Menu.class);
	}

	@Override
	public boolean insert(Menu element) throws RemoteException {
		return MenuDao.getInstance().insert(element);
	}

	@Override
	public boolean delete(Menu element) throws RemoteException {
		return MenuDao.getInstance().delete(element);
	}

}
