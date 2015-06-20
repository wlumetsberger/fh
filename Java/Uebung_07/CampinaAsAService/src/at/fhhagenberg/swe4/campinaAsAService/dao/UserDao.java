package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.models.UserViewModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.User;

/**
 * 
 * @author Wolfgang
 *
 */
public class UserDao implements
		Dao<UserViewModel> {

	@Override
	public List<UserViewModel> findAll() {
		ArrayList<UserViewModel> list = new ArrayList();
		list.add(new UserViewModel(
				"Wolfgang",
				"Lumetsberger",
				"wolfgang.lumetsberger@gmail.com",
				"passphrase", false));
		list.add(new UserViewModel("Max",
				"Mustermann",
				"max.mustermann@gmail.com",
				"maxi123", true));
		return list;
	}

	@Override
	public UserViewModel save(UserViewModel element) {
		return element;
	}

	@Override
	public UserViewModel get(UserViewModel element) {
		return element;
	}

	@Override
	public UserViewModel remove(UserViewModel element) {
		// TODO Auto-generated method stub
		return null;
	}

}
