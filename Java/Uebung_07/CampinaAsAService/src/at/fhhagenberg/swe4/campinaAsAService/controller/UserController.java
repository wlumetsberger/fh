package at.fhhagenberg.swe4.campinaAsAService.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;
import at.fhhagenberg.swe4.campinaAsAService.dao.UserDao;
import at.fhhagenberg.swe4.campinaAsAService.models.UserViewModel;

/**
 * 
 * @author Wolfgang
 *
 */
public class UserController extends Controller<UserViewModel> {

	@Override
	public ObservableList<UserViewModel> loadDataList() {
		ObservableList<UserViewModel> list = FXCollections
				.observableArrayList();
		Dao d = new UserDao();
		list.addAll(d.findAll());
		return list;
	}

	@Override
	public Class getDataClass() {
		return UserViewModel.class;
	}

	@Override
	public UserViewModel newDataInstance() {
		return new UserViewModel();
	}

}
