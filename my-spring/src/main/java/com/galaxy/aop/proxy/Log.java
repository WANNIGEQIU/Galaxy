package com.galaxy.aop.proxy;

public class Log {

    public static void beFore(String mName,String s){
        System.out.println(mName+"--param--"+ s);
    }

    public static void beAfter(String mName,Object re) {
        System.out.println(mName+"---"+re);
    }



}
