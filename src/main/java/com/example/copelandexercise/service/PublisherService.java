package com.example.copelandexercise.service;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.*;


@Service
public class PublisherService {

    @Value("${spring.activemq.broker-url}")
    String brokerUrl;

    @Value("${spring.activemq.user}")
    String user;

    @Value("${spring.activemq.password}")
    String password;

    @Value("${spring.activemq.topic}")
    String topicName;

    public void sendMessage(String inputMessage) throws JMSException {

        JmsConnectionFactory factory = new JmsConnectionFactory(brokerUrl);
        Connection connection = factory.createConnection(user, password);
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createTopic(topicName);

        MessageProducer publisher = session.createProducer(destination);

        TextMessage msg = session.createTextMessage(inputMessage);

        publisher.send(msg);

        connection.close();
    }
}
