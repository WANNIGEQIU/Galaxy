package com.galaxy.base.stack;

import java.util.Arrays;

public class ArrayStack<E> {

    private int count;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;

    public ArrayStack() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    public ArrayStack(int init) {
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

    private void checkCapacity() {
        if (count == data.length) {
            int length = data.length;
            int l = length + (length >> 1);
            Object[] objects = Arrays.copyOf(data, l);
            data = objects;
            objects = null;
        }

    }
    private void checkStack() {
        if (data.length == 0)
            throw new RuntimeException("栈为空");
    }
    public E pop() {
        checkStack();
        count--;
        return (E) data[count];
    }

    public E peek() {
        checkStack();
        return (E) data[count - 1];
    }

    @Override
    public String toString() {
        Object[] objects = Arrays.copyOf(data, count);
        return Arrays.toString(objects);
    }
}
