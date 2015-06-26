package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import at.fhhagenberg.swe4.campinaAsAService.models.UserViewModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.User;
import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

/**
 * 
 * @author Wolfgang
 *
 */
public class UserDao extends BaseDao<UserViewModel, User> {

	private ServiceInterface<User> serviceInterface;

	public UserDao() {
		try {
			this.serviceInterface = (ServiceInterface<User>) Naming
					.lookup("rmi://localhost:4707" + "/UserService");
		} catch (MalformedURLException |RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected Class getDataBaseModelClass() {
		return User.class;
	}

	@Override
	protected Class getViewModelClass() {
		return UserViewModel.class;
	}

	@Override
	public ServiceInterface<User> getServiceInterface() {
		return this.serviceInterface;
	}

}
