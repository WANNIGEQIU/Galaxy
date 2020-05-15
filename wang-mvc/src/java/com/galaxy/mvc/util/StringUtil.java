package com.galaxy.mvc.util;

import com.galaxy.mvc.annotations.GoAutowired;
import com.galaxy.mvc.controller.GoControllers;

import java.lang.reflect.Field;

public class StringUtil {

    public static boolean isEmpty(String string) {
        return "".equalsIgnoreCase(string) || string == null;
    }

    public static void main(String[] args) {

        Field[] declaredFields = GoControllers.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
             if (declaredField.isAnnotationPresent(GoAutowired.class)){
                 System.out.println("hello");
             }else {
                 System.out.println(declaredField);

             }
        }
    }
}
