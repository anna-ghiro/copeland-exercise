package com.example.copelandexercise;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.jms.*;
import java.util.Scanner;

@SpringBootApplication
@EnableAsync
public class CopelandExerciseApplication {

	public static void main(String[] args) throws JMSException {


		ConfigurableApplicationContext applicationContext = SpringApplication.run(CopelandExerciseApplication.class, args);

		String brokerUrl = applicationContext.getEnvironment().getProperty("spring.activemq.broker-url");
		String user = applicationContext.getEnvironment().getProperty("spring.activemq.user");
		String password = applicationContext.getEnvironment().getProperty("spring.activemq.password");
		String topicName = applicationContext.getEnvironment().getProperty("spring.activemq.topic");

		JmsConnectionFactory factory = new JmsConnectionFactory(brokerUrl);

		Connection connection = factory.createConnection(user, password);
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Destination destination = session.createTopic(topicName);

		MessageProducer publisher = session.createProducer(destination);

		Scanner input = new Scanner(System.in);
		String response;
		do {
			System.out.println("Enter message: ");
			response = input.nextLine();

			TextMessage msg = session.createTextMessage(response);

			publisher.send(msg);

		} while (!response.equalsIgnoreCase("Quit"));
		input.close();

		connection.close();
	}

}
