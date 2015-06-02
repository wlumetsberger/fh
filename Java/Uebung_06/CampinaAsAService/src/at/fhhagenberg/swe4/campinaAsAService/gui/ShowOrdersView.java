package at.fhhagenberg.swe4.campinaAsAService.gui;

import at.fhhagenberg.swe4.campinaAsAService.controller.OrderController;
import at.fhhagenberg.swe4.campinaAsAService.controller.UserController;
import at.fhhagenberg.swe4.campinaAsAService.helper.Util;
import at.fhhagenberg.swe4.campinaAsAService.models.Order;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class ShowOrdersView  extends BasicMasterDetailView<ShowOrderModel>{

	private static ShowOrdersView instance;
	
	private ShowOrdersView(){
		controller = new OrderController();
		fillTableData();	
		generateDetailSection(false);
		registerEvents();
	}
	
	static ShowOrdersView getInstance(){
		if(instance == null){
			instance = new ShowOrdersView();
		}
		return instance;
	}
}
