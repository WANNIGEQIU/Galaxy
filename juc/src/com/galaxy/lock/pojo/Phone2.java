package com.galaxy.lock.pojo;


import java.util.concurrent.locks.ReentrantLock;

public class Phone2 implements Runnable{

    ReentrantLock reentrantLock = new ReentrantLock(true);
    @Override
    public void run() {
        get();
    }

    public void get() {
        reentrantLock.lock();
       try {
           System.out.println(Thread.currentThread().getName()+"\t"+"get-------------");
           set();
       }finally {
           reentrantLock.unlock();
       }

    }

    public void set(){
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t"+"set-------------");
        }finally {
            reentrantLock.unlock();
        }    }
}
