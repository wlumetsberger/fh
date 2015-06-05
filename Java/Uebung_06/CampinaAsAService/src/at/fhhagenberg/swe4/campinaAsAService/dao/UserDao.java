package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.models.User;

/**
 * 
 * @author Wolfgang
 *
 */
public class UserDao implements
		Dao<User> {

	@Override
	public List<User> findAll() {
		ArrayList<User> list = new ArrayList();
		list.add(new User(
				"Wolfgang",
				"Lumetsberger",
				"wolfgang.lumetsberger@gmail.com",
				"passphrase", false));
		list.add(new User("Max",
				"Mustermann",
				"max.mustermann@gmail.com",
				"maxi123", true));
		return list;
	}

	@Override
	public User save(User element) {
		return element;
	}

	@Override
	public User get(User element) {
		return element;
	}

	@Override
	public User remove(User element) {
		// TODO Auto-generated method stub
		return null;
	}

}
