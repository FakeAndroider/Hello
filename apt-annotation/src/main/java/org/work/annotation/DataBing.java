package org.work.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*dataBing*/
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.CONSTRUCTOR)
public @interface DataBing {
}