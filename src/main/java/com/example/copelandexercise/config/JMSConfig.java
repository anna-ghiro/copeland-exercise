package com.example.copelandexercise.config;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@EnableJms
@Configuration
public class JMSConfig {

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        ActiveMQConnectionFactory amqConnectionFactory = new ActiveMQConnectionFactory("amqp://localhost:5672");
//        amqConnectionFactory.setSendTimeout(5000);
//        return amqConnectionFactory;
//    }

//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
//        return converter;
//    }

    @Bean
    public JmsTemplate eventJmsTemplate() {
        ActiveMQConnectionFactory amqConnectionFactory = new ActiveMQConnectionFactory("amqp://localhost:5672");
        JmsTemplate jmsTemplate = new JmsTemplate(amqConnectionFactory);
        jmsTemplate.setPubSubDomain(true);
//        jmsTemplate.setMessageConverter(jsonMessageConverter());
        return jmsTemplate;
    }
}
