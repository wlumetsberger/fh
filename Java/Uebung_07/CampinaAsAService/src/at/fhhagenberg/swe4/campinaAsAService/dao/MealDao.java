package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.models.MealViewModel;

/**
 * 
 * @author Wolfgang
 *
 */
public class MealDao implements
		Dao<MealViewModel> {

	@Override
	public List<MealViewModel> findAll() {
		List<MealViewModel> retVal = new ArrayList<MealViewModel>();
		CatagorieDao catDao = new CatagorieDao();
		retVal.add(new MealViewModel(1, "Schnitzel",
				"Schnitzel vom Schwein", catDao
						.findAll().get(0),
				LocalDateTime.now(),
				LocalDateTime.now(), 15.00));
		retVal.add(new MealViewModel(1,
				"Gschnetzlts",
				"Gschnetzlts vom Schwein",
				catDao.findAll().get(0),
				LocalDateTime.now(),
				LocalDateTime.now(), 10.90));
		return retVal;
	}

	@Override
	public MealViewModel save(MealViewModel element) {
		return element;
	}

	@Override
	public MealViewModel get(MealViewModel element) {
		return element;
	}

	@Override
	public MealViewModel remove(MealViewModel element) {
		// TODO Auto-generated method stub
		return null;
	}

}
