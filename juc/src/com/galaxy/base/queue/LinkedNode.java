package com.galaxy.base.queue;

import java.util.Arrays;

public class LinkedNode<E> {


    private Node<E> first;
    private Node<E> last;
    private int capacity;
    private int count;
    private Object[] result= {};


    public boolean add(E e) {
        linkedLast(e);
        checkCapacity();

        return true;

    }

    private void linkedLast(E e) {
        final Node<E> l = last;
        Node<E> eNode = new Node<>(l, e, null);
        last = eNode;
        if (l == null)
            first = eNode;
        else
            l.next = eNode;
        count++;
    }


    private void checkCapacity() {
        if (count > capacity) {
            this.capacity = capacity + size();
        }
    }
    // get fist node
    public E poll () {
        final Node<E> f = first;
        if (f == null) throw new RuntimeException("为队列为空");
       return removeFirstNode(f);
    }

    private E removeFirstNode (Node<E> first) {
         // 返回头元素
        E headItem = first.item;
        Node<E> nextItem = first.next;
        first.item = null;
        first.next = null;
        // 新的头元素
        this.first = nextItem;
        if (nextItem == null)
            last = null;
        else
            nextItem.prev = null;
        this.count--;
        return headItem;
    }


    public LinkedNode(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        this.capacity = capacity;
    }

    public LinkedNode() {
        this(Integer.MAX_VALUE);
    }



    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;
        Node(Node<E> prev,E e,Node<E> next) {
            this.item = e;
            this.prev = prev;
            this.next = next;
        }
    }

    public int size() {return this.count;}

    public int getCapacity(){return capacity;}


    private Object[] toArray() {
         result = new Object[count];
        int i = 0;
        for (Node<E> x = first; x != null; x= x.next) {
            result[i++] = x.item;
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    public static void main(String[] args) {
        LinkedNode<Integer> node = new LinkedNode<>(2);
        System.out.println("init\t"+node.size()+"\t"+node.getCapacity());
        node.add(1);
        node.add(2);
        node.add(3);
        node.add(4);
        System.out.println("init\t"+node.size()+"\t"+node.getCapacity());
        System.out.println();
        Integer poll = node.poll();
        System.out.println(poll);
        System.out.println("init\t"+node.size()+"\t"+node.getCapacity());


        System.out.println();
        Integer poll1 = node.poll();
        System.out.println(poll1);
        System.out.println("init\t"+node.size()+"\t"+node.getCapacity());

        System.out.println(node);


    }
}
