package com.example.copelandexercise;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.jms.*;
import java.util.Scanner;

@SpringBootApplication
@EnableAsync
public class CopelandExerciseApplication {

	public static void main(String[] args) throws JMSException {
		SpringApplication.run(CopelandExerciseApplication.class, args);

		JmsConnectionFactory factory = new JmsConnectionFactory("amqp://localhost:5672");
//		amqp://localhost:5672
		Connection connection = factory.createConnection("admin", "password");
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Destination destination = session.createTopic("RandomTopic");

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
