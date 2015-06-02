package at.fhhagenberg.swe4.campinaAsAService.helper;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import at.fhhagenberg.swe4.campinaAsAService.annotations.ViewProperty;
import at.fhhagenberg.swe4.campinaAsAService.models.User;

public class Util {

	/**
	 * For a Model with @ViewProperty Annotation
	 * This Method can produce correct TableModel with all default Fields
	 * @param clazz
	 * @return 
	 * @return
	 */
	public static <T> List<TableColumn<T,?>> getColumns(Class clazz) {
		
		List<TableColumn<T,?>> retVal = new ArrayList();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			Annotation anno = f.getDeclaredAnnotation(ViewProperty.class);
			if (anno != null) {
				ViewProperty prop = (ViewProperty) anno;
				if (prop.showInDefaultModel()) {
					if(f.getType().equals(boolean.class) || f.getType().equals(Boolean.class)){
						TableColumn<T,Boolean> col = new TableColumn<T,Boolean>(prop.name());
						col.setCellValueFactory(new PropertyValueFactory<T, Boolean>(
								f.getName()));
						retVal.add(col);
					}else{
					TableColumn<T,String> col = new TableColumn<T,String>(prop.name());
					col.setCellValueFactory(new PropertyValueFactory<T, String>(
							f.getName()));
					retVal.add(col);
					}
				}
			}
		}
		return retVal;
	}
	
	public static <T> TableView<T> generateTableView(Class clazz){
		TableView<T> view = new TableView<T>();
		view.setEditable(true);
		view.getColumns().addAll(Util.<T>getColumns(clazz));
		return view;

	}
	
	public static <T> GridPane generateDetailDialog(Class clazz, boolean editable){
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		
		Field[] fields = clazz.getDeclaredFields();
		int i= 1;
		for (Field f : fields) {
			Annotation anno = f.getDeclaredAnnotation(ViewProperty.class);
			if (anno != null) {
				ViewProperty prop = (ViewProperty) anno;
				if(f.getType().equals(boolean.class)){
					Label l = new Label(prop.name());
					CheckBox b = new CheckBox();
					b.setId(f.getName());
					b.setDisable(!editable);
					grid.setRowIndex(l, i);
					grid.setRowIndex(b, i);
					grid.setColumnIndex(l, 1);
					grid.setColumnIndex(b,2);
					grid.getChildren().addAll(l,b);
					i++;
				}
				if(prop.isTextField()){
					Label l = new Label(prop.name());
					TextField t = new TextField();
					t.setEditable(editable);
					t.setId(f.getName());
					grid.setRowIndex(l, i);
					grid.setRowIndex(t, i);
					grid.setColumnIndex(l, 1);
					grid.setColumnIndex(t,2);
					grid.getChildren().addAll(l,t);
					i++;
				}
			}
		}
		return grid;
	}
	public static void writeValuesToDetailPane(Pane p, Object valueHolder){
		if(valueHolder == null){
			return;
		}
		try {
			for (Node n : p.getChildren()) {
				if (n instanceof CheckBox) {
					CheckBox box = (CheckBox) n;
					String name = box.getId();
					for (PropertyDescriptor property : Introspector
							.getBeanInfo(valueHolder.getClass()).getPropertyDescriptors()) {
						if (property.getName().equals(name)) {
							box.setSelected((Boolean) property
									.getReadMethod().invoke(valueHolder));
							break;
						}
					}
				}
				if (n instanceof TextField) {
					TextField field = (TextField) n;
					String name = field.getId();
					for (PropertyDescriptor property : Introspector
							.getBeanInfo(valueHolder.getClass()).getPropertyDescriptors()) {
						if (property.getName().equals(name)) {
							field.setText((String) property.getReadMethod()
									.invoke(valueHolder));
							break;
						}
					}

				}
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getValuesFromDetailPane(Pane p, Object valueHolder){
		if(valueHolder == null){
			return;
		}
		try{
			for(Node n : p.getChildren()){
				if(n instanceof CheckBox){
					CheckBox box = (CheckBox) n;
					String name = box.getId();
					for (PropertyDescriptor property : Introspector
							.getBeanInfo(valueHolder.getClass()).getPropertyDescriptors()) {
						if (property.getName().equals(name)) {
					          property.getWriteMethod().invoke(valueHolder,box.isSelected());
							break;
						}
					}
				}if (n instanceof TextField) {
					TextField field = (TextField) n;
					String name = field.getId();
					for (PropertyDescriptor property : Introspector
							.getBeanInfo(valueHolder.getClass()).getPropertyDescriptors()) {
						if (property.getName().equals(name)) {
							property.getWriteMethod()
									.invoke(valueHolder,field.getText());
							break;
						}
					}

				}
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
