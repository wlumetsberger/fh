package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import at.fhhagenberg.swe4.campinaAsAService.models.CatagorieViewModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Catagorie;
import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

/**
 * 
 * @author Wolfgang
 *
 */
public class CatagorieDao extends BaseDao<CatagorieViewModel, Catagorie> {

	private ServiceInterface<Catagorie> serviceInterface;

	public CatagorieDao() {
		try {
			serviceInterface = (ServiceInterface<Catagorie>) Naming
					.lookup("rmi://localhost:4707" + "/CatagorieService");
		} catch (MalformedURLException |RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ServiceInterface<Catagorie> getServiceInterface() {
		return this.serviceInterface;
	}

	@Override
	protected Class getDataBaseModelClass() {
		return Catagorie.class;
	}

	@Override
	protected Class getViewModelClass() {
		return CatagorieViewModel.class;
	}

}
