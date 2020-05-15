package com.galaxy.life;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.galaxy.life")
public class LifeConfig {

    @Bean
    public Person person() {
        Person person = new Person();
        person.setId(1);
        person.setName("haha");
        return person;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifeConfig.class);
        Person person = (Person) context.getBean("person");
        System.out.println(person);
        context.close();


    }
}
