package at.fhhagenberg.swe4.campinaAsAService.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;
import at.fhhagenberg.swe4.campinaAsAService.dao.OrderDao;
import at.fhhagenberg.swe4.campinaAsAService.models.OrderViewModel;

/**
 * 
 * @author Wolfgang
 *
 */
public class OrderController extends
		Controller<OrderViewModel> {

	public ObservableList<OrderViewModel> loadDataList() {
		ObservableList<OrderViewModel> list = FXCollections
				.observableArrayList();
		Dao d = (Dao) new OrderDao();
		list.addAll(d.findAll());
		return list;
	}

	@Override
	public Class getDataClass() {
		return OrderViewModel.class;
	}

	@Override
	public OrderViewModel newDataInstance() {
		return new OrderViewModel();
	}
}
