package at.fhhagenberg.swe4.campinaAsAService.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuView {

	private MenuBar menuBar;
	private static MenuView instance;
	
	public static MenuView getInstance(){
		if(instance == null){
			instance = new MenuView();
		}
		return instance;
	}
	private MenuView(){
		menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
	    MenuItem users = new MenuItem("Manage Users");
	    MenuItem orders = new MenuItem("ShowOrders");
	    MenuItem catagories = new MenuItem("Manage Catagories");
	    
	    users.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				UserView view = UserView.getInstance();
				BaseView.getInstance().getPane().setCenter(view.getPane());
			}
		});
	    orders.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ShowOrdersView view = ShowOrdersView.getInstance();
				BaseView.getInstance().getPane().setCenter(view.getPane());
			}
		});
	    catagories.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				CatagorieView view = CatagorieView.getInstance();
				BaseView.getInstance().getPane().setCenter(view.getPane());
			}
		});
		menuFile.getItems().addAll(users,orders,catagories);
	    menuBar.getMenus().add(menuFile);
	}
	
	public MenuBar getMenuBar() {
		return menuBar;
	}
	
	
	
}
