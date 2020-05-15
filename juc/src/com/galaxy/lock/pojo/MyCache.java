package com.galaxy.lock.pojo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    public void write(String key,Object v) {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"\t 写入数据->"+v);
        try {
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key, v);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }

    public void read(String k) {
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"\t 读取数据");
        try {
            // 模拟延迟
            TimeUnit.MICROSECONDS.sleep(300);
            Object o = map.get(k);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成 ->"+o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }

    }
}
