package at.fhhagenberg.swe4.campinaAsAService.rmi.service.impl;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Catagorie;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Meal;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Menu;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Order;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.User;
import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

public class StartRmiInterface {

	/**
	 * Helper to split host and port
	 * @param host_port
	 * @return
	 */
	private static int getPort(String host_port) {
		int idx = host_port.lastIndexOf(":");
		if (idx == -1)
			return 1099;
		else
			return Integer.parseInt(host_port.substring(idx + 1));
	}
	/**
	 * Main Method starts registry and instances of every available Services
	 * @param args
	 */
	public static void main(String[] args) {

		String host_port = "localhost:4707";
		try {
			LocateRegistry.createRegistry(getPort(host_port));
			// catagorie
			ServiceInterface<Catagorie> catService = new CatagorieService();
			Remote catServiceStrub = UnicastRemoteObject.exportObject(
					catService, 0);
			Naming.rebind("rmi://" + host_port + "/CatagorieService",
					catServiceStrub);
			// meal
			ServiceInterface<Meal> mealService = new MealService();
			Remote mealServiceStrub = UnicastRemoteObject.exportObject(
					mealService, 0);
			Naming.rebind("rmi://" + host_port + "/MealService",
					mealServiceStrub);
			// menu
			ServiceInterface<Menu> menuService = new MenuService();
			Remote menuServiceStrub = UnicastRemoteObject.exportObject(
					menuService, 0);
			Naming.rebind("rmi://" + host_port + "/MenuService",
					menuServiceStrub);
			// order
			ServiceInterface<Order> orderService = new OrderService();
			Remote orderServiceStrub = UnicastRemoteObject.exportObject(
					orderService, 0);
			Naming.rebind("rmi://" + host_port + "/OrderService",
					orderServiceStrub);
			// user
			ServiceInterface<User> userService = new UserService();
			Remote userServiceStrub = UnicastRemoteObject.exportObject(
					userService, 0);
			Naming.rebind("rmi://" + host_port + "/UserService",
					userServiceStrub);

			System.out.println("Services available");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
