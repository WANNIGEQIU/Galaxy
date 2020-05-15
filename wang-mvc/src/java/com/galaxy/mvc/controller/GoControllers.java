package com.galaxy.mvc.controller;

import com.galaxy.mvc.annotations.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.util.logging.Logger;

@GoController
@GoRequestMapping("/api")
public class GoControllers {

    @GoAutowired("service")
    private TestService testService;

    @GoRequestMapping("/hello")
    @GoResponseBody
    public String hello(@GoRequestParameter("test") String name, HttpServletResponse response, HttpServletRequest request) {
        String hello = testService.hello(name);
        System.out.println(hello);
        return hello;


    }


}
