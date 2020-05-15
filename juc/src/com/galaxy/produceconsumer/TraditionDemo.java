package com.galaxy.produceconsumer;

import com.galaxy.produceconsumer.resource.MyNumber;
import jdk.dynalink.linker.support.CompositeTypeBasedGuardingDynamicLinker;

import java.util.concurrent.TimeUnit;

/**
 * 生产消费 传统demo
 *  初始值 0 加1减1 操作
 */
public class TraditionDemo {

    public static void main(String[] args) {
        MyNumber number = new MyNumber();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                    number.addition();
            }

        }, "add-").start();


        new Thread(() -> {
            for (int i = 0; i < 3; i++) {

                    number.Subtraction();
            }
        }, "sub-").start();
    }
}
