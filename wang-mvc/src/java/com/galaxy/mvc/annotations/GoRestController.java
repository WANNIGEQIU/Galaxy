package com.galaxy.mvc.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@GoResponseBody
@GoController
public @interface GoRestController {
    String value() default "";

}
