package at.fhhagenberg.swe4.campinaAsAService.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;
import at.fhhagenberg.swe4.campinaAsAService.dao.UserDao;
import at.fhhagenberg.swe4.campinaAsAService.models.User;

/**
 * 
 * @author Wolfgang
 *
 */
public class UserController extends
		Controller<User> {

	@Override
	public ObservableList<User> loadDataList() {
		ObservableList<User> list = FXCollections
				.observableArrayList();
		Dao d = new UserDao();
		list.addAll(d.findAll());
		return list;
	}

	@Override
	public void saveDetail() {
		if (!this.dataList
				.contains(detailData)) {
			this.dataList.add(detailData);
		}
		detailData = new User();

	}

	@Override
	public Class getDataClass() {
		return User.class;
	}

	@Override
	public User newDataInstance() {
		return new User();
	}

}
