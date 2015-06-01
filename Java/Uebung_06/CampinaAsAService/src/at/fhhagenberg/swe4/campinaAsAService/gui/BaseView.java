package at.fhhagenberg.swe4.campinaAsAService.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class BaseView {
	
	private static BaseView instance;
	private BorderPane basePane;
	
	private BaseView(){
		 basePane = new BorderPane();
	}
	
	public static BaseView getInstance() {
		if(instance == null){
			instance = new BaseView();
		}
		return instance;
	}
	
	public BorderPane getPane(){
		return this.basePane;
	}
}
