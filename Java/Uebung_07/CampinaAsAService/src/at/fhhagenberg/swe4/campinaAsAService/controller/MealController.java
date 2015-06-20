package at.fhhagenberg.swe4.campinaAsAService.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;
import at.fhhagenberg.swe4.campinaAsAService.dao.MealDao;
import at.fhhagenberg.swe4.campinaAsAService.models.MealViewModel;

/**
 * 
 * @author Wolfgang
 *
 */
public class MealController extends
		Controller<MealViewModel> {

	@Override
	public ObservableList<MealViewModel> loadDataList() {
		Dao dao = new MealDao();
		ObservableList<MealViewModel> list = FXCollections
				.observableArrayList(dao
						.findAll());
		return list;
	}

	@Override
	public Class getDataClass() {
		return MealViewModel.class;
	}

	@Override
	public MealViewModel newDataInstance() {
		return new MealViewModel();
	}

}
