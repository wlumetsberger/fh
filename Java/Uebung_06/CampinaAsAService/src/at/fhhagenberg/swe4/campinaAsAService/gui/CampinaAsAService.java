package at.fhhagenberg.swe4.campinaAsAService.gui;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;

public class CampinaAsAService extends Application{

	@Override
	public void start(Stage stage) {
		
		BaseView base = BaseView.getInstance();
		MenuView menu = MenuView.getInstance();
		
		base.getPane().setTop(menu.getMenuBar());
		Scene scene = new Scene(base.getPane(),1024,768);
		
		stage.setTitle("Campina as a service");
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setScene(scene);
        
        stage.show();
	}
	
	 public static void main(String[] args) {
		    launch(args);
	}

}
