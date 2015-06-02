package at.fhhagenberg.swe4.campinaAsAService.gui;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import at.fhhagenberg.swe4.campinaAsAService.annotations.ViewProperty;
import at.fhhagenberg.swe4.campinaAsAService.controller.UserController;
import at.fhhagenberg.swe4.campinaAsAService.helper.Util;
import at.fhhagenberg.swe4.campinaAsAService.models.Order;
import at.fhhagenberg.swe4.campinaAsAService.models.User;

public class UserView extends BasicMasterDetailView<User> {

	private static UserView instance;

	private UserView() {
		controller = new UserController();
		fillTableData();
		generateDetailSection(true);
		registerEvents();

	}

	public static UserView getInstance() {
		if (instance == null) {
			instance = new UserView();
		}
		return instance;
	}

	

	
	
	

	


}
