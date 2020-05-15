package com.galaxy.thread.pojo;

public class ResourceTest extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "\t extends-" + i);
        }
    }
}
