package com.galaxy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/name")
    public ModelAndView testUser(String name) {
        log.info("用户名："+name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("username",name);
        return modelAndView;
    }
    /**
     * 测试拦截器
     */
    @ResponseBody
     public boolean testInterceptor(String name) {
         if ("tom".equalsIgnoreCase(name)) {
             log.info("测试拦截器：{}",name);
             return true;
         }else {
             log.warn("权限验证失败：{}",name);
             return false;
         }
     }
    public ModelAndView testInterceptor(ModelAndView modelAndView,String string) {
        log.info("拦截器：{}",string);
        modelAndView.setViewName("user");
        modelAndView.addObject("username",string);
        return modelAndView;
    }

    @RequestMapping("/fail")
    @ResponseBody
    public String fail() {
        return "权限验证失败";
    }
}
