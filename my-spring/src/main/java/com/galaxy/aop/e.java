package com.galaxy.aop;


import com.galaxy.aop.Aspect.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.galaxy.aop")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class e {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(e.class);
        User user = context.getBean("user", User.class);
        String buy = user.buy();
        System.out.println(buy+"嘻嘻嘻");
    }
}
