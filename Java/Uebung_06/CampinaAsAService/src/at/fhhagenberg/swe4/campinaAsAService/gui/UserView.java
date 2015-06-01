package at.fhhagenberg.swe4.campinaAsAService.gui;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import at.fhhagenberg.swe4.campinaAsAService.annotations.ViewProperty;
import at.fhhagenberg.swe4.campinaAsAService.controller.UserController;
import at.fhhagenberg.swe4.campinaAsAService.helper.Util;
import at.fhhagenberg.swe4.campinaAsAService.models.User;

public class UserView {

	private TableView<User> userTable;

	private UserController controller;
	private GridPane pane;
	private VBox vbox;

	private static UserView instance;
	
	private UserView() {
		userTable = new TableView<User>();
		controller = new UserController();
		pane = new GridPane();
		pane.setId("default-panel-user-view");
		fillTableData();
		registerEvents();
		
	}
	
	public static UserView getInstance(){
		if(instance == null){
			instance = new UserView();
		}
		return instance;
	}

	

	private void fillTableData() {
		userTable.setEditable(true);
		userTable.setItems(controller.getUsers());
		userTable.getColumns().addAll(Util.<User>getColumns(User.class));
		
		
    	pane.setRowIndex(userTable, 0);
    	pane.setColumnIndex(userTable,0);
    	pane.setHgrow(userTable, Priority.ALWAYS);
    	pane.setVgrow(userTable, Priority.ALWAYS);
		pane.getChildren().add(userTable);
	}
	
	private void registerEvents(){
		userTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
		    public void changed(ObservableValue<? extends User> observable,
		        User oldValue, User newValue) {
		    	System.out.println(newValue);
		        UserView.this.controller.setDetailUser(newValue);
		    }
		  });
	}

	public Pane getUserPane() {
		return this.pane;
	}

}
