package com.galaxy.mvc.annotations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GoAutowired {
    String value() default "";

}
