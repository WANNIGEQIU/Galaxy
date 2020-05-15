    package com.galaxy.testcas;

    import java.util.concurrent.TimeUnit;
    import java.util.concurrent.atomic.AtomicInteger;

    /**
     * aba
     */
    public class CasDemo2 {
        public static void main(String[] args) throws InterruptedException {
            AtomicInteger atomicInteger = new AtomicInteger(2021);
            new Thread(()->{
               System.out.println(Thread.currentThread().getName()+"\t get value--"+atomicInteger.get());
                try {
                    TimeUnit.MICROSECONDS.sleep(700);
                    boolean b = atomicInteger.compareAndSet(2021, 1080);
                    System.out.println(Thread.currentThread().getName()+"\t state--"+b);
                    System.out.println(Thread.currentThread().getName()+"\t value--"+atomicInteger.get());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"线程1").start();

            TimeUnit.MICROSECONDS.sleep(300);
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t get value--"+atomicInteger.get());
                try {

                    boolean b = atomicInteger.compareAndSet(2021, 2020);
                    System.out.println(Thread.currentThread().getName()+"\t state--"+b);
                    System.out.println(Thread.currentThread().getName()+"\t value--"+atomicInteger.get());
                    System.out.println("*********改回以前的值**********");
                    boolean bb = atomicInteger.compareAndSet(2020, 2021);
                    System.out.println(Thread.currentThread().getName()+"\t state--"+bb);
                    System.out.println(Thread.currentThread().getName()+"\t value--"+atomicInteger.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"线程ABA").start();
        }
    }
