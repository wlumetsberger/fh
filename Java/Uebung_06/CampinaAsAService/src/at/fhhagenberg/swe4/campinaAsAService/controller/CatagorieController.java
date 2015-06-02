package at.fhhagenberg.swe4.campinaAsAService.controller;

import at.fhhagenberg.swe4.campinaAsAService.models.Catagorie;
import at.fhhagenberg.swe4.campinaAsAService.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CatagorieController extends Controller<Catagorie>{

	@Override
	public ObservableList<Catagorie> loadDataList() {
		ObservableList<Catagorie> list =  FXCollections.observableArrayList();
		list.add(new Catagorie("Frisch vom Grill","Gegrilltes zeugs"));
		list.add(new Catagorie("Vegetarisches","Vegetarische gerichte"));
		return list;
	}

	@Override
	public Class getDataClass() {
		return Catagorie.class;
	}

	@Override
	public Catagorie newDataInstance() {
		return new Catagorie();
	}

}
