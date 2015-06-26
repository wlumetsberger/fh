package at.fhhagenberg.swe4.campinaAsAService.rmi.service.impl;

import java.rmi.RemoteException;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.UserDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.User;
import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

public class UserService implements ServiceInterface<User> {

	@Override
	public List<User> findAll() throws RemoteException {
		return UserDao.getInstance().findAll();
	}

	@Override
	public boolean update(User element) throws RemoteException {
		return UserDao.getInstance().update(element);
	}
	@Override
	public User find(User element) throws RemoteException {
		return UserDao.getInstance().findById(element.getId(), User.class);
	}

	@Override
	public boolean insert(User element) throws RemoteException {
		return UserDao.getInstance().insert(element);
	}

	@Override
	public boolean delete(User element) throws RemoteException {
		return UserDao.getInstance().delete(element);
	}

}
