package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.models.Meal;

/**
 * 
 * @author Wolfgang
 *
 */
public class MealDao implements
		Dao<Meal> {

	@Override
	public List<Meal> findAll() {
		List<Meal> retVal = new ArrayList<Meal>();
		CatagorieDao catDao = new CatagorieDao();
		retVal.add(new Meal(1, "Schnitzel",
				"Schnitzel vom Schwein", catDao
						.findAll().get(0),
				LocalDateTime.now(),
				LocalDateTime.now(), 15.00));
		retVal.add(new Meal(1,
				"Gschnetzlts",
				"Gschnetzlts vom Schwein",
				catDao.findAll().get(0),
				LocalDateTime.now(),
				LocalDateTime.now(), 10.90));
		return retVal;
	}

	@Override
	public Meal save(Meal element) {
		return element;
	}

	@Override
	public Meal get(Meal element) {
		return element;
	}

	@Override
	public Meal remove(Meal element) {
		// TODO Auto-generated method stub
		return null;
	}

}
