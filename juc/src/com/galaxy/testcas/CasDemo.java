package com.galaxy.testcas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {

    public static void main(String[] args) {
            AtomicInteger integer = new AtomicInteger(5);
            // doing sth
            System.out.println(integer.compareAndSet(5,2019)+"\t"+integer.get());
            System.out.println(integer.compareAndSet(5,1024)+"\t"+integer.get());
         integer.getAndIncrement();
         integer.get();
    }

}
