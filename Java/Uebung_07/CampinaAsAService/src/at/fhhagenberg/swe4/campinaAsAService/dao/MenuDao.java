package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.models.MenuViewModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Menu;
import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

/**
 * 
 * @author Wolfgang
 *
 */
public class MenuDao extends BaseDao<MenuViewModel, Menu> {

	private ServiceInterface<Menu> serviceInterface;

	public MenuDao() {
		try {
			this.serviceInterface = (ServiceInterface<Menu>) Naming
					.lookup("rmi://localhost:4707" + "/MenuService");
		} catch (MalformedURLException |RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MenuViewModel> findAll() {
		return new ArrayList<MenuViewModel>();
	}

	@Override
	protected Class getDataBaseModelClass() {
		return Menu.class;
	}

	@Override
	protected Class getViewModelClass() {
		return MenuViewModel.class;
	}

	@Override
	public ServiceInterface<Menu> getServiceInterface() {
		return this.serviceInterface;
	}

}
