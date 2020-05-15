package com.galaxy.singleton.test;

import com.galaxy.singleton.*;

public class SingletonTest {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(()->{


            },String.valueOf(i)).start();
        }
    }
}
