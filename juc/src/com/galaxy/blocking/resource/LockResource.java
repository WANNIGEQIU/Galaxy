package com.galaxy.blocking.resource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockResource {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int anInt;
    public  void produce() {
        lock.lock();
      try {
          while (anInt != 0) {
              try {
                  System.out.println(Thread.currentThread().getName()+"----wait...");
                  condition.await();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
          anInt++;
          System.out.println(Thread.currentThread().getName()+"\t生产---"+anInt);
         condition.signalAll();

      } catch (Exception e) {
          e.printStackTrace();
      }finally {
          lock.unlock();
      }
    }

    public void consumer() {
        lock.lock();
        try {
            while (anInt == 0) {
                try {
                    System.out.println(Thread.currentThread().getName()+"----wait...");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            anInt--;
            System.out.println(Thread.currentThread().getName()+"\t消费---"+anInt);
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockResource resource = new LockResource();

        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                resource.produce();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"生产线程").start();

        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                resource.consumer();
            }

        },"消费线程").start();

    }

}
