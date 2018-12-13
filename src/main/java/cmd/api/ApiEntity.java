package cmd.api;

import java.lang.annotation.*;

/**
 * Created by pc1 on 11/28/2018.
 */

@Inherited
@Target(ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ApiEntity {
    short cmd() default -1;
}
