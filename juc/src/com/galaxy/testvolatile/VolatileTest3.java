package com.galaxy.testvolatile;

/**
 * 应用
 */
public class VolatileTest3 {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                SingletonIns.getInstance();
            },String.valueOf(i)).start();
        }
    }

}
