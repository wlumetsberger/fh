package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import at.fhhagenberg.swe4.campinaAsAService.models.MealViewModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Meal;
import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

/**
 * 
 * @author Wolfgang
 *
 */
public class MealDao extends BaseDao<MealViewModel, Meal> {

	private ServiceInterface<Meal> serviceInterface;

	public MealDao() {
		try {
			serviceInterface = (ServiceInterface<Meal>) Naming
					.lookup("rmi://localhost:4707" + "/MealService");
		} catch (MalformedURLException |RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Class getDataBaseModelClass() {
		return Meal.class;
	}

	@Override
	protected Class getViewModelClass() {
		return MealViewModel.class;
	}

	@Override
	public ServiceInterface<Meal> getServiceInterface() {
		return this.serviceInterface;
	}

}
