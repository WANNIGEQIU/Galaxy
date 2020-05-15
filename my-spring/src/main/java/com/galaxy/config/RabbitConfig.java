package com.galaxy.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private static final String QUEUE_A = "MQ_A";
    private static final String QUEUE_B = "MQ_B";

    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A);
    }

    @Bean
    public Queue queueB() {
        return new Queue(QUEUE_B);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("EXC");
    }

    @Bean
    public Binding bindingA(Queue queueA, TopicExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange).with("msg.a");
    }

    @Bean
    public Binding bindingB(Queue queueB, TopicExchange exchange) {
        return BindingBuilder.bind(queueB).to(exchange).with("msg.#");
    }


}
