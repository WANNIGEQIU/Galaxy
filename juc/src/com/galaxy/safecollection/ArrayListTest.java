package com.galaxy.safecollection;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 线程不安全demo
 */
public class ArrayListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Vector<String> list1 = new Vector<>();
        List<String> list2 = Collections.synchronizedList(list);
        CopyOnWriteArrayList<String> list3 = new CopyOnWriteArrayList <>();

        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                for (int i1 = 0; i1 < 11; i1++) {
                    list3.add(i1+"");
                    System.out.println(Thread.currentThread().getName()+"---"+list3);
                }
            },"LIST--"+i).start();
        }




    }
}
