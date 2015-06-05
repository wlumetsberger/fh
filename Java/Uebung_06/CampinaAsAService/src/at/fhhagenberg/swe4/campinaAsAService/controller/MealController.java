package at.fhhagenberg.swe4.campinaAsAService.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;
import at.fhhagenberg.swe4.campinaAsAService.dao.MealDao;
import at.fhhagenberg.swe4.campinaAsAService.models.Meal;

/**
 * 
 * @author Wolfgang
 *
 */
public class MealController extends
		Controller<Meal> {

	@Override
	public ObservableList<Meal> loadDataList() {
		Dao dao = new MealDao();
		ObservableList<Meal> list = FXCollections
				.observableArrayList(dao
						.findAll());
		return list;
	}

	@Override
	public Class getDataClass() {
		return Meal.class;
	}

	@Override
	public Meal newDataInstance() {
		return new Meal();
	}

}
