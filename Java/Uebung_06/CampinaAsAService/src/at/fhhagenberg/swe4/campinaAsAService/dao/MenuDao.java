package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.models.Menu;

/**
 * 
 * @author Wolfgang
 *
 */
public class MenuDao implements
		Dao<Menu> {

	@Override
	public List<Menu> findAll() {
		return new ArrayList<Menu>();
	}

	@Override
	public Menu save(Menu element) {
		return element;
	}

	@Override
	public Menu get(Menu element) {
		return element;
	}

	@Override
	public Menu remove(Menu element) {
		// TODO Auto-generated method stub
		return null;
	}

}
