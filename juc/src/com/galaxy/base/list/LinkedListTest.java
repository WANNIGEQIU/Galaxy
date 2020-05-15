package com.galaxy.base.list;

import com.galaxy.base.collection.CollectionTest;

import javax.swing.border.EmptyBorder;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedListTest<E> implements CollectionTest<E> {

    private transient int size = 0;
    private transient Node<E> first;
    private transient Node<E> last;


    private static class Node<E> {

        E data;
        Node<E> prev;
        Node<E> next;

        private Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }



    }

    public LinkedListTest() {
    }

    private Node<E> eNode(int index) {
        Node<E> x = first;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public E getFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException("元素不存在");
        return f.data;
    }

    public E getLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NullPointerException("集合为空");
        return l.data;

    }

    @Override
    public boolean contains(Object o) {
        if (o == null)
            return false;

        for (Node<E> node = first; node != null; node = node.next) {
            if (Objects.equals(o, node.data))
                return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        int x = 0;
        for (Node<E> node = first; node != null; node = node.next) {
            objects[x++] = node.data;
        }
        return objects;
    }

    @Override
    public boolean add(E o) {
        linkLast(o);
        return true;
    }

    @Override
    public E remove(Object o) {
        return null;
    }


    @Override
    public void clear() {
        for (Node<E> i = first; i != null; ) {
            Node<E> item = i.next;
            i.prev = null;
            i.data = null;
            i.next = null;
            i = item;
        }
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index,size);
        return eNode(index).data;
    }

    private void linkLast(E e) {
        final Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)  // 集合空
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    private void linkFirst(E e) {
        final Node<E> f = first;
        Node<E> eNode = new Node<>(null, e, f);
        first = eNode;
        if (f == null)
            last = eNode;
        else
            f.prev = eNode;
        size++;
    }
}
