package at.fhhagenberg.swe4.campinaAsAService.rmi.service.impl;

import java.rmi.RemoteException;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.MealDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Meal;
import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

public class MealService implements ServiceInterface<Meal> {

	@Override
	public List<Meal> findAll() throws RemoteException {
		return MealDao.getInstance().findAll();
	}

	@Override
	public Meal find(Meal element) throws RemoteException {
		return MealDao.getInstance().findById(element.getId(), Meal.class);
	}
	@Override
	public boolean update(Meal element) throws RemoteException {
		return MealDao.getInstance().update(element);
	}

	@Override
	public boolean insert(Meal element) throws RemoteException {
		return MealDao.getInstance().insert(element);
	}

	@Override
	public boolean delete(Meal element) throws RemoteException {
		return MealDao.getInstance().delete(element);
	}

}
