package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.models.MealViewModel;
import at.fhhagenberg.swe4.campinaAsAService.models.OrderViewModel;
import at.fhhagenberg.swe4.campinaAsAService.models.UserViewModel;

/**
 * 
 * @author Wolfgang
 *
 */
public class OrderDao implements
		Dao<OrderViewModel> {

	/**
	 * Returns Orderd List
	 */
	@Override
	public List<OrderViewModel> findAll() {
		List<OrderViewModel> orderList = new ArrayList();
		List<MealViewModel> mealList = new MealDao()
				.findAll();
		UserViewModel u = new UserDao().findAll()
				.get(0);
		int orderId = 1;
		for (MealViewModel m : mealList) {
			orderList.add(new OrderViewModel(orderId,u, m,
					LocalDateTime.now(),
					LocalDateTime.now(), ""));
			orderId++;
			orderList.add(new OrderViewModel(orderId,u, m,
					LocalDateTime.now(),
					LocalDateTime.now(), ""));
			orderId++;
		}
		return orderList;
	}

	@Override
	public OrderViewModel save(OrderViewModel element) {
		return element;
	}

	@Override
	public OrderViewModel get(OrderViewModel element) {
		return element;
	}

	@Override
	public OrderViewModel remove(OrderViewModel element) {
		// TODO Auto-generated method stub
		return null;
	}

}
