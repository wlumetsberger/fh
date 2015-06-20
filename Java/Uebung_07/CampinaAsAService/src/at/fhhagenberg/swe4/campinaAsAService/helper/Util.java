package at.fhhagenberg.swe4.campinaAsAService.helper;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import at.fhhagenberg.swe4.campinaAsAService.annotations.ViewProperty;
import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;
import at.fhhagenberg.swe4.campinaAsAService.models.BaseViewModel;

/**
 * Helper Class to Generate TableView,
 * and DetailPane out of a Pojo with
 * ViewProperty -> annotations
 * 
 * @author Wolfgang
 *
 */
public class Util {

	/**
	 * For a Model with @ViewProperty
	 * Annotation This Method can produce
	 * correct TableModel with all default
	 * Fields
	 * 
	 * @param clazz
	 * @return
	 * @return
	 */
	public static <T> List<TableColumn<T, ?>> getColumns(
			Class clazz) {

		List<TableColumn<T, ?>> retVal = new ArrayList();
		Field[] fields = clazz
				.getDeclaredFields();
		for (Field f : fields) {
			Annotation anno = f
					.getDeclaredAnnotation(ViewProperty.class);
			if (anno != null) {
				ViewProperty prop = (ViewProperty) anno;
				if (prop.showInDefaultModel()) {
					if (f.getType().equals(
							boolean.class)
							|| f.getType().equals(
									Boolean.class)) {
						TableColumn<T, Boolean> col = new TableColumn<T, Boolean>(
								prop.name());
						col.setCellValueFactory(
								new PropertyValueFactory<T, Boolean>(
								f.getName()));
						retVal.add(col);
					} else if (f.getType()
							.equals(
									LocalDateTime.class)) {
						TableColumn<T, LocalDateTime> col = 
								new TableColumn<T, LocalDateTime>(prop.name());
						col.setCellValueFactory(
								new PropertyValueFactory<T, LocalDateTime>(
								f.getName()));
						retVal.add(col);
					} else {
						TableColumn<T, String> col = new TableColumn<T, String>(
								prop.name());
						col.setCellValueFactory(new PropertyValueFactory<T, String>(
								f.getName()));
						retVal.add(col);
					}
				}
			}
		}
		return retVal;
	}

	/**
	 * Generate a TableView for a Given
	 * Class
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> TableView<T> generateTableView(
			Class clazz) {
		TableView<T> view = new TableView<T>();
		view.setEditable(true);
		view.getColumns().addAll(
				Util.<T> getColumns(clazz));
		return view;

	}

	/**
	 * Generates a DetailDialog for a
	 * given Class editable property
	 * defines if the value is editable
	 * 
	 * @param clazz
	 * @param editable
	 * @return
	 */
	public static <T> GridPane generateDetailDialog(
			Class clazz, boolean editable) {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10,
				10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		Field[] fields = clazz
				.getDeclaredFields();
		int i = 1;
		for (Field f : fields) {
			Annotation anno = f
					.getDeclaredAnnotation(ViewProperty.class);
			if (anno != null) {
				ViewProperty prop = (ViewProperty) anno;

				if (f.getType().equals(
						boolean.class)) {
					Label l = new Label(
							prop.name());
					CheckBox b = new CheckBox();
					b.setId(f.getName());
					b.setDisable(!editable);
					grid.setRowIndex(l, i);
					grid.setRowIndex(b, i);
					grid.setColumnIndex(l, 1);
					grid.setColumnIndex(b, 2);
					grid.getChildren().addAll(l,
							b);
					i++;
				} else if (f.getType().equals(
						LocalDateTime.class)) {
					Label l = new Label(
							prop.name());
					DatePicker picker = new DatePicker();
					picker.setDisable(!editable);
					picker.setId(f.getName());
					grid.setRowIndex(l, i);
					grid.setRowIndex(picker, i);
					grid.setColumnIndex(l, 1);
					grid.setColumnIndex(picker, 2);
					grid.getChildren().addAll(l,
							picker);
					i++;
				} else if (prop.isComboBox()) {
					Class c = f.getType();
					Method m;
					try {
						m = c
								.getDeclaredMethod("getDao");
						if (m != null) {
							Dao o = (Dao) m
									.invoke(
											c.newInstance(),
											null);
							ComboBox box = new ComboBox();
							Label l = new Label(
									prop.name());
							box.setId(f.getName());
							grid.setRowIndex(l, i);
							grid.setRowIndex(box, i);
							grid.setColumnIndex(l, 1);
							grid.setColumnIndex(box,
									2);
							grid.getChildren()
									.addAll(l, box);
							i++;
						}
					} catch (
							SecurityException
							| NoSuchMethodException
							| IllegalAccessException
							| InvocationTargetException
							| IllegalArgumentException
							| InstantiationException e) {
						System.out
								.println("cannot write value");
					}

				} else if (prop.isTextField()) {
					Label l = new Label(
							prop.name());
					TextField t = new TextField();
					t.setEditable(editable);
					t.setId(f.getName());
					grid.setRowIndex(l, i);
					grid.setRowIndex(t, i);
					grid.setColumnIndex(l, 1);
					grid.setColumnIndex(t, 2);
					grid.getChildren().addAll(l,
							t);
					i++;
				}
			}
		}
		return grid;
	}

	/**
	 * Write values to DetailPane fields
	 * 
	 * @param p
	 * @param valueHolder
	 */
	public static void writeValuesToDetailPane(
			Pane p, Object valueHolder) {
		if (valueHolder == null) {
			return;
		}
		try {
			for (Node n : p.getChildren()) {
				System.out.println(n);
				if (n instanceof CheckBox) {
					CheckBox box = (CheckBox) n;
					String name = box.getId();
					for (PropertyDescriptor property : Introspector
							.getBeanInfo(
									valueHolder
											.getClass())
							.getPropertyDescriptors()) {
						if (property.getName()
								.equals(name)) {
							box.setSelected((Boolean) property
									.getReadMethod()
									.invoke(valueHolder));
							break;
						}
					}
				} else if (n instanceof TextField) {
					TextField field = (TextField) n;
					String name = field.getId();
					for (PropertyDescriptor property : Introspector
							.getBeanInfo(
									valueHolder
											.getClass())
							.getPropertyDescriptors()) {
						if (property.getName()
								.equals(name)) {
							Object value = property
									.getReadMethod()
									.invoke(valueHolder);
							if (value != null) {
								field.setText(String
										.valueOf(value));
							}

							break;
						}
					}
				} else if (n instanceof ComboBox) {
					ComboBox box = (ComboBox) n;
					String name = box.getId();
					for (PropertyDescriptor property : Introspector
							.getBeanInfo(
									valueHolder
											.getClass())
							.getPropertyDescriptors()) {
						if (property.getName()
								.equals(name)) {
							BaseViewModel model = (BaseViewModel) property
									.getReadMethod()
									.invoke(valueHolder);
							if (model != null) {
								Dao dao = model
										.getDao();
								List elements = dao
										.findAll();
								box.setItems(FXCollections
										.observableArrayList(elements));
								box.setValue(model);
							} else {
								try {
									model = (BaseViewModel) property
											.getPropertyType()
											.newInstance();
									Dao dao = model
											.getDao();
									List elements = dao
											.findAll();
									box.setItems(FXCollections
											.observableArrayList(elements));
									box.setValue(elements
											.get(0));

								} catch (InstantiationException e) {
									// TODO Auto-generated
									// catch block
									e.printStackTrace();
								}
							}

							break;
						}
					}
				} else if (n instanceof DatePicker) {
					DatePicker picker = (DatePicker) n;
					String name = picker.getId();
					LocalDate date = picker
							.getValue();
					for (PropertyDescriptor property : Introspector
							.getBeanInfo(
									valueHolder
											.getClass())
							.getPropertyDescriptors()) {
						if (property.getName()
								.equals(name)) {
							LocalDateTime dateTime = (LocalDateTime) property
									.getReadMethod()
									.invoke(valueHolder);
							if (dateTime != null) {
								picker
										.setValue((dateTime
												.toLocalDate()));
							} else {
								picker
										.setValue(LocalDate
												.now());
							}
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

	/**
	 * Read Values from DetailPane to
	 * ValueHolder Object
	 * 
	 * @param p
	 * @param valueHolder
	 */
	public static void getValuesFromDetailPane(
			Pane p, Object valueHolder) {
		if (valueHolder == null) {
			return;
		}
		try {
			for (Node n : p.getChildren()) {
				if (n instanceof CheckBox) {
					CheckBox box = (CheckBox) n;
					String name = box.getId();
					for (PropertyDescriptor property : Introspector
							.getBeanInfo(
									valueHolder
											.getClass())
							.getPropertyDescriptors()) {
						if (property.getName()
								.equals(name)) {
							property.getWriteMethod()
									.invoke(valueHolder,
											box.isSelected());
							break;
						}
					}
				} else if (n instanceof TextField) {
					TextField field = (TextField) n;
					String name = field.getId();
					for (PropertyDescriptor property : Introspector
							.getBeanInfo(
									valueHolder
											.getClass())
							.getPropertyDescriptors()) {
						if (property.getName()
								.equals(name)) {
							if (property
									.getPropertyType()
									.equals(String.class)) {
								property
										.getWriteMethod()
										.invoke(
												valueHolder,
												field.getText());
							} else if (property
									.getPropertyType()
									.equals(Double.class)) {
								try {
									Double help = Double
											.parseDouble(field
													.getText());
									property
											.getWriteMethod()
											.invoke(
													valueHolder,
													help);
								} catch (Exception ex) {
									System.out
											.println("Cannot parse value");
								}
							}

							break;
						}
					}
				} else if (n instanceof ComboBox) {
					ComboBox box = (ComboBox) n;
					String name = box.getId();
					for (PropertyDescriptor property : Introspector
							.getBeanInfo(
									valueHolder
											.getClass())
							.getPropertyDescriptors()) {
						if (property.getName()
								.equals(name)) {
							property.getWriteMethod()
									.invoke(valueHolder,
											box.getValue());
							break;
						}
					}
				} else if (n instanceof DatePicker) {
					DatePicker picker = (DatePicker) n;
					String name = picker.getId();
					for (PropertyDescriptor property : Introspector
							.getBeanInfo(
									valueHolder
											.getClass())
							.getPropertyDescriptors()) {
						if (property.getName()
								.equals(name)) {
							LocalDateTime help = LocalDateTime
									.of(((LocalDate) picker
											.getValue()),
											LocalTime.MIN);
							property.getWriteMethod()
									.invoke(valueHolder,
											help);
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
