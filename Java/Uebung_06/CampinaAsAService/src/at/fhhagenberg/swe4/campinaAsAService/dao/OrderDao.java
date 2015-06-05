package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.models.Meal;
import at.fhhagenberg.swe4.campinaAsAService.models.Order;
import at.fhhagenberg.swe4.campinaAsAService.models.User;

/**
 * 
 * @author Wolfgang
 *
 */
public class OrderDao implements
		Dao<Order> {

	/**
	 * Returns Orderd List
	 */
	@Override
	public List<Order> findAll() {
		List<Order> orderList = new ArrayList();
		List<Meal> mealList = new MealDao()
				.findAll();
		User u = new UserDao().findAll()
				.get(0);
		for (Meal m : mealList) {
			orderList.add(new Order(u, m,
					LocalDateTime.now(),
					LocalDateTime.now(), ""));
			orderList.add(new Order(u, m,
					LocalDateTime.now(),
					LocalDateTime.now(), ""));
		}
		return orderList;
	}

	@Override
	public Order save(Order element) {
		return element;
	}

	@Override
	public Order get(Order element) {
		return element;
	}

	@Override
	public Order remove(Order element) {
		// TODO Auto-generated method stub
		return null;
	}

}
