package com.galaxy.base.queue;

import java.util.Arrays;

public class ArrayQueue<E> {
    private int count;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;

    public ArrayQueue() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    public ArrayQueue(int init) {
        if (init > 0)
            this.data = new Object[init];
        else if (init == 0)
            this.data = new Object[DEFAULT_CAPACITY];
        else
            throw new IllegalArgumentException();
    }

    public int size() {
        return count;
    }

    public boolean add(E e) {
        checkCapacity();
        data[count] = e;
        count = count + 1;
        return true;
    }

    public E pop() {
        checkQueue();
        Object datum = data[0];
        count--;
        Object[] objects = new Object[count];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = data[i+1];
        }
        Object[] copyOf = Arrays.copyOf(objects, data.length);
        data = copyOf;
        return (E) datum;

    }

    public int getCount() {
        return data.length;
    }

    public E peek() {
        checkQueue();
        return (E) data[0];
    }

    @Override
    public String toString() {
        Object[]  objects = Arrays.copyOf(data,count);
      return Arrays.toString(objects);
    }

    private void checkCapacity() {
        if (count == data.length) {
            int length = data.length;
            int l = length + (length >> 1);
            Object[] objects = Arrays.copyOf(data, l);
            data = objects;
            objects = null;
        }

    }

    private void checkQueue() {
        if (data.length == 0)
            throw new RuntimeException("队列为空");
    }
}
