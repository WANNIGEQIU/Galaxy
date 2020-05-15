package com.galaxy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @RequestMapping("/test")
    public ModelAndView testUser(String name) {
        log.info("用户名："+name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("username",name);
        return modelAndView;
    }


    @GetMapping("/he")
    @ResponseBody
    public String he(@RequestParam("name") String string) {
        System.out.println(string);
        return "hi "+string;
    }
}
