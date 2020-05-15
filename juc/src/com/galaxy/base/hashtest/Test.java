package com.galaxy.base.hashtest;

import java.util.Objects;

/**
 * hashCode
 */
public class Test {
    public static void main(String[] args) {
        user user = new user("wu",12);
        user user1 = new user("wu",12);
        System.out.println(user==user1);        // false
        System.out.println(user.equals(user1));  // true
        System.out.println(user.hashCode() == user1.hashCode()); // true

    }
}

 class  user{
    String name;
    Integer age;

    public user(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public user(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        user user = (user) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}