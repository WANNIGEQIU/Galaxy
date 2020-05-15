package com.galaxy.synchronize.base;

public class Person {
    private Long id;
    private String name;
    private Integer age;


    /**
     * 普通同步方法
     */
    public synchronized void test() {
        for (int i = 0; i < 2; i++) {
            System.out.println(Thread.currentThread().getName() + "\t print->" + i);
        }
    }

    /**
     * 静态同步方法
     */
    public synchronized static void testSta() {
        for (int i = 0; i < 2; i++) {
            System.out.println(Thread.currentThread().getName() + "\t static->" + i);
        }
    }

    public void aVoid() {
        synchronized (this) {
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName() + "\t this->" + i);
            }
        }
    }

    public void aVoids() {
        synchronized (Person.class) {
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName() + "\t class->" + i);
            }
        }
    }
}
