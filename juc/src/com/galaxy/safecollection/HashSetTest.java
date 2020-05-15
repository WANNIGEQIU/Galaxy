package com.galaxy.safecollection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * HashSet 不安全 demo
 */
public class HashSetTest {

    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>();
       Set<String> synchronizedSet = Collections.synchronizedSet(hashSet);
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                for (int i1 = 0; i1 < 11; i1++) {
                    hashSet.add(i1+"");
                    System.out.println(Thread.currentThread().getName()+"---"+hashSet);
                }
            },"LIST--"+i).start();
        }

    }

}
