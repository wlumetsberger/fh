package at.fhhagenberg.swe4.campinaAsAService.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
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
}
