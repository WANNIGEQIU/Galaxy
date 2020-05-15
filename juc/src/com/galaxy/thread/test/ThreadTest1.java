package com.galaxy.thread.test;

import com.galaxy.thread.pojo.ResourceTest1;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 *  线程创建 implements
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        ResourceTest1 test1 = new ResourceTest1();
        new Thread(test1).start();
    }
}
