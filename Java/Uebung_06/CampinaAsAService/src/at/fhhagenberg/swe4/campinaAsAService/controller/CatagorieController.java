package at.fhhagenberg.swe4.campinaAsAService.controller;

import at.fhhagenberg.swe4.campinaAsAService.dao.CatagorieDao;
import at.fhhagenberg.swe4.campinaAsAService.models.Catagorie;
import at.fhhagenberg.swe4.campinaAsAService.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * 
 * @author Wolfgang
 *
 */
public class CatagorieController extends Controller<Catagorie>{

	@Override
	public ObservableList<Catagorie> loadDataList() {
		ObservableList<Catagorie> list =  FXCollections.observableArrayList();
		list.addAll(new CatagorieDao().findAll());
		return list;
	}

	@Override
	public Class getDataClass() {
		return Catagorie.class;
	}

	@Override
	public Catagorie newDataInstance() {
		Catagorie a = new  Catagorie();
		return a;
	}

}
