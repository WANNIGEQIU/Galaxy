package com.galaxy.safecollection;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {
    public static void main(String[] args) {
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> stringIntegerMap = Collections.synchronizedMap(hashMap);
        Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(()->{
                hashMap.put(String.valueOf(finalI), finalI);
                hashMap.forEach((ks,vs)->{
                    System.out.println(ks+"-------"+vs);
                });
            },String.valueOf(i)).start();
        }
    }
}
