package com.galaxy.aop.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BuyAspect {

    /**
     * 使用注解定义切点
     */
    @Pointcut(value = "execution(* com.galaxy.aop.Aspect.Buy.buy(..)))")
    public void point(){}

    @Before("point()")
    public void haha() {
        System.out.println("前置通知....");
    }

    @After(value = "execution(* com.galaxy.aop.Aspect.Buy.buy(..))")
    public void after() {
        System.out.println("after.....");
    }

    @AfterReturning(value = "execution(* com.galaxy.aop.Aspect.Buy.buy(..))")
    public void ar() {
        System.out.println("AfterReturning......");
    }

    @Around(value = "execution(* com.galaxy.aop.Aspect.Buy.buy(..))")
    public void around(ProceedingJoinPoint point) {
        try {
            System.out.println(point+"--a");
            point.proceed();
            System.out.println(point+"--b");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
