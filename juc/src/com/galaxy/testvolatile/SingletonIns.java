package com.galaxy.testvolatile;

public class SingletonIns {
    private static volatile SingletonIns ins = null;

    private  SingletonIns(){
        System.out.println( Thread.currentThread().getName()+"\t 构造方法 singleton");
    }

    public static  SingletonIns getInstance() {
        if(ins == null) {
            synchronized (SingletonIns.class){
                if (ins == null) {
                    ins = new SingletonIns();
                }
            }
        }
        return ins;
    }


}
