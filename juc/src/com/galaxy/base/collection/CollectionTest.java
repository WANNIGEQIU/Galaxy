package com.galaxy.base.collection;

public interface CollectionTest<E> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Object[] toArray();

    boolean add(E o);

    E remove(Object o);

    void clear();

    E get(int index);


}
