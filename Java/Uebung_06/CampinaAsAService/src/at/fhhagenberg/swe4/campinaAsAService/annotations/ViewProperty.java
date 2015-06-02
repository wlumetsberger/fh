package at.fhhagenberg.swe4.campinaAsAService.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ViewProperty {
	String name() default "";
	boolean showInDefaultModel() default true;
	boolean isTextField() default true;
}
