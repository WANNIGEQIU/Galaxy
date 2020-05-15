package com.galaxy.mvc.annotations;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GoRequestParameter {
    String value() default "";
    boolean required() default true;

}
