package com.galaxy.lock.pojo;

public class Phone {


    public  synchronized void msg() {
        System.out.println(Thread.currentThread().getName()+"\t"+"发送消息");
        mail();
    }


    public  synchronized void mail() {
        System.out.println(Thread.currentThread().getName()+"\t"+"发送邮件");

    }


}
