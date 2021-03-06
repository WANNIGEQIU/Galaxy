package com.galaxy.mvc.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GoBasePackageScan {
    String value() default "";

}
