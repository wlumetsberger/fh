package at.fhhagenberg.swe4.campinaAsAService.rmi.service.impl;

import java.rmi.RemoteException;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.OrderDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Order;
import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

public class OrderService implements ServiceInterface<Order> {

	@Override
	public List<Order> findAll() throws RemoteException {
		return OrderDao.getInstance().findAll();
	}

	@Override
	public boolean update(Order element) throws RemoteException {
		return OrderDao.getInstance().update(element);
	}
	@Override
	public Order find(Order element) throws RemoteException {
		return OrderDao.getInstance().findById(element.getId(), Order.class);
	}

	@Override
	public boolean insert(Order element) throws RemoteException {
		return OrderDao.getInstance().insert(element);
	}

	@Override
	public boolean delete(Order element) throws RemoteException {
		return OrderDao.getInstance().delete(element);
	}

}
