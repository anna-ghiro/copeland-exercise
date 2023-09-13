package com.example.copelandexercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.*;


@Service
public class PublisherService {

    @Autowired
    JmsTemplate jmsTemplate;

//    @Autowired
//    public PublisherService(JmsTemplate jmsTemplate) {
//        this.jmsTemplate = jmsTemplate;
//    }

    public void sendMessage(String inputMessage) {

//        JmsConnectionFactory factory = new JmsConnectionFactory("amqp://localhost:5672");
//        Connection connection = factory.createConnection("admin", "password");
//        connection.start();
//
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//        Destination destination = session.createTopic("RandomTopic");
//
//        MessageProducer publisher = session.createProducer(destination);
//
//        TextMessage msg = session.createTextMessage(inputMessage);
//
//        publisher.send(msg);
//
//        connection.close();

        jmsTemplate.convertAndSend("RandomTopic", inputMessage);

    }
}
