package com.galaxy.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyU {
    private proxyImpl proxy;

    public ProxyU(proxyImpl proxyImpl) {
        super();
        this.proxy = proxyImpl;
    }

    public Object getProxy() {
        ClassLoader classLoader = proxy.getClass().getClassLoader();

        return Proxy.newProxyInstance(classLoader, proxy.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object p, Method method, Object[] args) throws Throwable {
                Log.beFore(method.getName(), Arrays.toString(args));
                Object invoke = method.invoke(proxy, args);
                Log.beAfter(method.getName(), invoke);
                return invoke;
            }
        });
    }

}

