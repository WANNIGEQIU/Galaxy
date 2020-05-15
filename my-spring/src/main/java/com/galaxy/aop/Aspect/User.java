package com.galaxy.aop.Aspect;

import org.springframework.stereotype.Component;

@Component
public class User implements Buy{
    @Override
    public String buy() {
        System.out.println("购买了个switch");
        return "switch";
    }
}
