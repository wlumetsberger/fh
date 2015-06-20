package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.models.MenuViewModel;

/**
 * 
 * @author Wolfgang
 *
 */
public class MenuDao implements
		Dao<MenuViewModel> {

	@Override
	public List<MenuViewModel> findAll() {
		return new ArrayList<MenuViewModel>();
	}

	@Override
	public MenuViewModel save(MenuViewModel element) {
		return element;
	}

	@Override
	public MenuViewModel get(MenuViewModel element) {
		return element;
	}

	@Override
	public MenuViewModel remove(MenuViewModel element) {
		// TODO Auto-generated method stub
		return null;
	}

}
