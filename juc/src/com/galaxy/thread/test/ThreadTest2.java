package com.galaxy.thread.test;

import com.galaxy.thread.pojo.ResourceTest2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程 callable
 */
public class ThreadTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ResourceTest2 test2 = new ResourceTest2();
        FutureTask<Integer> futureTask = new FutureTask<>(test2);
        new Thread(futureTask).start();
        System.out.println("return\t"+futureTask.get());
    }

}
