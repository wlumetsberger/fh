package at.fhhagenberg.swe4.campinaAsAService.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.fhhagenberg.swe4.campinaAsAService.dao.CatagorieDao;
import at.fhhagenberg.swe4.campinaAsAService.models.CatagorieViewModel;

/**
 * 
 * @author Wolfgang
 *
 */
public class CatagorieController extends Controller<CatagorieViewModel> {

	@Override
	public ObservableList<CatagorieViewModel> loadDataList() {
		ObservableList<CatagorieViewModel> list = FXCollections
				.observableArrayList();
		list.addAll(new CatagorieDao().findAll());
		return list;
	}

	@Override
	public Class getDataClass() {
		return CatagorieViewModel.class;
	}

	@Override
	public CatagorieViewModel newDataInstance() {
		CatagorieViewModel a = new CatagorieViewModel();
		return a;
	}

}
