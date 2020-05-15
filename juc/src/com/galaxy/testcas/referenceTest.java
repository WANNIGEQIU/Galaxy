package com.galaxy.testcas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子引用类
 */
public class referenceTest {
    public static void main(String[] args) {
        User user1 = new User("user1", 18);
        User user2 = new User("user2", 19);
        AtomicReference<User> reference = new AtomicReference<>();
        reference.set(user1);
        System.out.println(reference.compareAndSet(user1,user2)+"\t"+reference.get().toString());
        System.out.println(reference.compareAndSet(user1,user2)+"\t"+reference.get().toString());

    }
}



