package at.fhhagenberg.swe4.campinaAsAService.controller;

import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.fhhagenberg.swe4.campinaAsAService.gui.ShowOrderModel;
import at.fhhagenberg.swe4.campinaAsAService.gui.ShowOrdersView;
import at.fhhagenberg.swe4.campinaAsAService.models.Catagorie;
import at.fhhagenberg.swe4.campinaAsAService.models.Meal;
import at.fhhagenberg.swe4.campinaAsAService.models.Order;
import at.fhhagenberg.swe4.campinaAsAService.models.User;

public class OrderController extends Controller<ShowOrderModel>{

	
	public ObservableList<ShowOrderModel> loadDataList(){
		ObservableList<ShowOrderModel> list =  FXCollections.observableArrayList();
		Catagorie testCat = new Catagorie("Deftiges","Hausmannskost");
		Meal testMeal1 = new Meal(1,"Schnitzel","Wiener Schnitzel vom Schwein mit Kartoffeln",testCat, new Date(), new Date());
		Meal testMeal2 = new Meal(2,"Gschnetzelts","Gschnetzelts vom Schwein mit Kartoffeln",testCat, new Date(), new Date());
		list.add(new ShowOrderModel(new Order(new User("Wolfgang","Lumetsberger","w.lumetsberger@gmail.com","asdf",false),testMeal1 , new Date(), new Date(), "")));
		list.add(new ShowOrderModel(new Order(new User("Hugo","Test","hugo.test@gmail.com","asdf",false),testMeal2 , new Date(), new Date(), "Gruß aus der Küche erwünscht")));
		return list;
	}
	
	@Override
	public Class getDataClass() {
		return ShowOrderModel.class;
	}
	@Override
	public ShowOrderModel newDataInstance() {
		return new ShowOrderModel();
	}
}
