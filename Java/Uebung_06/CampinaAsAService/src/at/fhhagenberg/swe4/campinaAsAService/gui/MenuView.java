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
	    users.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Manage User Pressed");
				UserView view = UserView.getInstance();
				BaseView.getInstance().getPane().setCenter(view.getUserPane());
			}
		});
		menuFile.getItems().add(users);
	    menuBar.getMenus().add(menuFile);
	}
	
	public MenuBar getMenuBar() {
		return menuBar;
	}
	
	
	
}
