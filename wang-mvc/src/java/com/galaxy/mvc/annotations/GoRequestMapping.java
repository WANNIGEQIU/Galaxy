package com.galaxy.mvc.annotations;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GoRequestMapping {
    String value() default "/";

}
