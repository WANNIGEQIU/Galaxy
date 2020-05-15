package com.galaxy.singleton;

/**
 * 登记式
 */
public class Singleton6 {

    private  Singleton6(){}
    private static class inner{
        private static final Singleton6 SINGLETON_6 = new Singleton6();
    }
    public  static Singleton6 singleton6() {
        return inner.SINGLETON_6;
    }
}
