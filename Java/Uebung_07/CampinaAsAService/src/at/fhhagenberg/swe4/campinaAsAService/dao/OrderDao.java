package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import at.fhhagenberg.swe4.campinaAsAService.models.OrderViewModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Order;
import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

/**
 * 
 * @author Wolfgang
 *
 */
public class OrderDao extends BaseDao<OrderViewModel, Order> {

	private ServiceInterface<Order> serviceInterface;

	public OrderDao() {
		try {
			this.serviceInterface = (ServiceInterface<Order>) Naming
					.lookup("rmi://localhost:4707" + "/OrderService");
		} catch (MalformedURLException |RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected Class getDataBaseModelClass() {
		return Order.class;
	}

	@Override
	protected Class getViewModelClass() {
		return OrderViewModel.class;
	}

	@Override
	public ServiceInterface<Order> getServiceInterface() {
		return this.serviceInterface;
	}

}
