package com.galaxy.mvc.controller;

import com.galaxy.mvc.annotations.GoService;

@GoService("service")
public class TestService {

    public String hello(String name) {
        return "hello "+name;
    }
}
