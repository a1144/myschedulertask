package com.shuangyu.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitmqConfig {
    @Value("${spring.rabbitmq.host}")
    private String address;
    @Value("${spring.rabbitmq.port}")
    private String port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;
    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(address + ":" + port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(publisherConfirms);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    @Bean
    public Queue userQueue(){
        return new Queue("queue");
    }

    @Bean
    public Queue bbsEmailQueue(){
        return new Queue("bbsQueue");
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("exchange");
    }

    @Bean
    TopicExchange bbsEmailTopicExchange(){
        return new TopicExchange("bbsTopicExchange");
    }

    @Bean
    Binding bindingExchange(Queue userQueue,TopicExchange topicExchange){
        return BindingBuilder.bind(userQueue).to(topicExchange).with("topic.test");
    }

    @Bean
    Binding bindingBBSExchange(Queue bbsEmailQueue,TopicExchange bbsEmailTopicExchange){
        return BindingBuilder.bind(bbsEmailQueue).to(bbsEmailTopicExchange).with("topic.duowan.tiandao.email");
    }

}
