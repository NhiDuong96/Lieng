package cmd.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by pc1 on 11/27/2018.
 */
@Target({
        ElementType.METHOD,
        ElementType.FIELD
        })
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiField {
        String type() default "";
}
