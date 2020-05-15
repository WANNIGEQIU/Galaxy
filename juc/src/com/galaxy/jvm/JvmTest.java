package com.galaxy.jvm;

import java.util.concurrent.TimeUnit;

public class JvmTest {

    public static void main(String[] args) throws InterruptedException {
        int a = 10;
        System.out.println(a);
        TimeUnit.SECONDS.sleep(30);
        System.out.println("hello");
    }
}
