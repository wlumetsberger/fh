package at.fhhagenberg.swe4.campinaAsAService.rmi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation which can be used like hibernate annotations It defines if a field
 * is key and the FieldName in DataBase
 * 
 * @author Wolfgang
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataBaseFieldProperty {

	boolean key() default false;
	boolean generated() default false;
	boolean foreign() default false;
	String column();
	DataBaseConnections connectionType() default DataBaseConnections.No;
	Class foreignClass() default Object.class;
}
