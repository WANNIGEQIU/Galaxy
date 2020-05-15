package com.galaxy.thread.pojo;

import java.util.concurrent.Callable;

public class ResourceTest2 implements Callable {
    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "\t Callable-" + i);
        }
        return 1024;
    }
}
