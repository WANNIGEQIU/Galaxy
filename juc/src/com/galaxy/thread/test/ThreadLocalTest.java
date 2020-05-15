package com.galaxy.thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Message {
    private String info;

    public Message(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Message{info: " + info + "}";
    }
}

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<Message> objectThreadLocal = new ThreadLocal<>();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            Message message = new Message("啊哈哈");
            objectThreadLocal.set(message);
            System.out.println(Thread.currentThread().getName() + "\t" + objectThreadLocal.get());
        });
        service.execute(() -> {
            Message message = new Message("嘿嘿啊");
            objectThreadLocal.set(message);
            System.out.println(Thread.currentThread().getName() + "\t" + objectThreadLocal.get());

        });
        service.shutdown();
    }
}

