package com.galaxy.life;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Person {

    private Integer id;
    private String name;

    /**
     * 相当于xml中init-method
     */
    @PostConstruct
    public void init() {
        System.out.println("3---init");
    }

    @Override
    public String toString() {
        return "4---run--" + name;
    }

    /**
     * 相当于xml中dest-method
     */
    @PreDestroy
    public void destroy() {
        System.out.println("5---destroy");
    }

    public Person() {
        System.out.println("1---new");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        System.out.println("2---set");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
