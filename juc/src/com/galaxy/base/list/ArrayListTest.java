package com.galaxy.base.list;

import com.galaxy.base.collection.CollectionTest;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayListTest<E>  implements CollectionTest<E> {

    transient Object[] elements;
    private static final Object[] EMPTY_ELEMENT = {};
    transient int size;
    private static final int DEFAULT_CAPACITY = 12;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {

        return checkElement(o) >=0;
    }

    @Override
    public Object[] toArray() {

        return Arrays.copyOf(elements,size);
    }

   
    public ArrayListTest(int i) {
        if (i > 0) {
            elements = new Object[i];
        }else if (i == 0) {
            elements = EMPTY_ELEMENT;
        }else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean add(E o) {
        if (size == elements.length)
            elements = grow(size + 1);
        elements[size] = o;
        size++;
        return true;
    }

    private int checkElement(Object o) {

        for (int i = 0; i < size; i++) {
            if (Objects.equals(o,elements[i])) {
                return i;
            }else {
                continue;
            }
        }
        return -1;
    }


    @Override
    public void clear() {
        final Object[] objects = elements;
        for (int i = 0; i < elements.length; i++) {
            objects[i] = null;
        }
        size = 0;

    }

    public int getSize(){
        return elements.length;
    }

    public ArrayListTest() {
        this.elements = EMPTY_ELEMENT;
    }


     public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
     }

     @Override
     public E remove(Object o){
        int index = -1;
        if (o == null)
            throw new NullPointerException();
         Object[] objects = Arrays.copyOf(elements, size - 1);
         for (int i = 0; i < elements.length; i++) {
             if (Objects.equals(o,elements[i]))
                 index = i;
         }
            if (index == -1)
                throw new NoSuchElementException();
         for (int i = 0; i < objects.length; i++) {
             if (index == i)
                 objects[i] = elements[i + 1];
             else
                 objects[i] = elements[i];

         }
         size--;
         elements = objects;
         return (E) o;
     }

    /**
     * @param init
     * @return
     */
    private Object[] grow(int init) {
        int oldLength = elements.length;
        int newLength = elements.length + (oldLength >> 1);
        if (newLength - oldLength <= 0)
            return Arrays.copyOf(elements, Math.max(DEFAULT_CAPACITY, init));
        if (init < 0)
            throw new OutOfMemoryError();
        return (newLength - MAX_CAPACITY <= 0)
                ? Arrays.copyOf(elements, newLength) : Arrays.copyOf(elements, MAX_CAPACITY);
    }

    private void checkIndex(int index) {
        if (index >= elements.length || index < 0)
            throw new ArrayIndexOutOfBoundsException();
    }


}
