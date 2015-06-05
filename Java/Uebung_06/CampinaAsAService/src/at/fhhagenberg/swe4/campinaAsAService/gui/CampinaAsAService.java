package at.fhhagenberg.swe4.campinaAsAService.gui;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;
/**
 * 
 * @author Wolfgang
 *
 */
public class CampinaAsAService extends Application{

	@Override
	public void start(Stage stage) {
		
		BaseView base = BaseView.getInstance();
		MenuView menu = MenuView.getInstance();
		
		base.getPane().setTop(menu.getMenuBar());
		Scene scene = new Scene(base.getPane(),1600,900);
		
		stage.setTitle("Campina as a service");
        stage.setWidth(1600);
        stage.setHeight(900);
        stage.setScene(scene);
        
        stage.show();
	}
	
	 public static void main(String[] args) {
		    launch(args);
	}

}
