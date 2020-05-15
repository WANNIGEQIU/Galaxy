package com.galaxy.produceconsumer;

import com.galaxy.produceconsumer.resource.ABC;

/**
 * 多线程按顺序调用 a->b->c
 */
public class ConditionTest {
    public static void main(String[] args) {
        ABC abc = new ABC();


        // c
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                abc.printC();
            }, "线程-c").start();
        }
        // a
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                abc.printA();
            }, "线程-a").start();
        }

        // b
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                abc.printB();
            }, "线程-b").start();
        }


    }
}
