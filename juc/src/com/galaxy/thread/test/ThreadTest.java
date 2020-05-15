package com.galaxy.thread.test;

import com.galaxy.thread.pojo.ResourceTest;

/**
 *  线程创建 extends
 */
public class ThreadTest {
    public static void main(String[] args) {
        ResourceTest test = new ResourceTest();
        Thread thread = new Thread(test);
        thread.start();
        System.out.println(Thread.currentThread().getName()+"\t over...");
    }
}
