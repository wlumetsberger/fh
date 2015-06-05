package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.models.Catagorie;
/**
 * 
 * @author Wolfgang
 *
 */
public class CatagorieDao implements Dao<Catagorie>{

	@Override
	public List<Catagorie> findAll() {
		List<Catagorie> elements = new ArrayList<Catagorie>();
		elements.add(new Catagorie("Frisch vom Grill","Gegrilltes zeugs"));
		elements.add(new Catagorie("Vegetarisches","Vegetarische gerichte"));
		return elements;
	}

	@Override
	public Catagorie save(Catagorie element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Catagorie get(Catagorie element) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Catagorie remove(Catagorie element) {
		// TODO Auto-generated method stub
		return null;
	}

}
