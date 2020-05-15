package com.galaxy.singleton;

/**
 * 枚举
 */
public enum  Singleton2 {
    INSTANCE("tom")
    ;

    String name;
    Singleton2(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }



}


