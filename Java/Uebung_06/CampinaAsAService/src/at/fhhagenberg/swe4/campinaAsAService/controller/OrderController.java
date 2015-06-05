package at.fhhagenberg.swe4.campinaAsAService.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;
import at.fhhagenberg.swe4.campinaAsAService.dao.OrderDao;
import at.fhhagenberg.swe4.campinaAsAService.models.Order;

/**
 * 
 * @author Wolfgang
 *
 */
public class OrderController extends
		Controller<Order> {

	public ObservableList<Order> loadDataList() {
		ObservableList<Order> list = FXCollections
				.observableArrayList();
		Dao d = (Dao) new OrderDao();
		list.addAll(d.findAll());
		return list;
	}

	@Override
	public Class getDataClass() {
		return Order.class;
	}

	@Override
	public Order newDataInstance() {
		return new Order();
	}
}
