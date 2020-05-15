package com.galaxy.testcas;

import java.util.FormatFlagsConversionMismatchException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

/**
 * AtomicStampedReference
 */
public class ReferenceTest1 {
    public static void main(String[] args) throws InterruptedException {
        User u1 = new User("小狗", 2);
        User u2 = new User("小猫", 1);
        User u3 = new User("小龟", 1);
        // 默认版本号
        int stamp = 100;
        AtomicStampedReference<User> reference = new AtomicStampedReference<>(u1, stamp);
    new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t get..."+reference.getReference()+":"+reference.getStamp());
        try {
            TimeUnit.SECONDS.sleep(1);
            if (reference.compareAndSet(u1,u2,stamp,stamp+1)) {

                System.out.println(Thread.currentThread().getName()+"\t set..."+reference.getReference()+":"+reference.getStamp());
            }else {

                System.out.println(Thread.currentThread().getName()+"\t fail....");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }).start();


      new Thread(()->{
          System.out.println(Thread.currentThread().getName()+"\t get2..."+reference.getReference()+":"+reference.getStamp());
            reference.compareAndSet(u1,u2,stamp,stamp+1);
            // do sth
          reference.compareAndSet(u2,u1,stamp,stamp+1);
          System.out.println(Thread.currentThread().getName()+"\t end2..."+reference.getReference()+":"+reference.getStamp());

      }).start();
    }
}
