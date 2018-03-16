package org.webservices.jms;



import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsMessageSender {
	private static String serverUrl = "vm://localhost";
	private static int index = 0;
	private static String propertyName = "intValue";

	public static void sendMessage(String queueName, int intValue1, int intValue2) throws JMSException {
		ConnectionFactory factory;
		Connection connection = null;
		Session session;
		MessageProducer messageProducer;
		Destination destination;
		Message message;

		try {
			
			queueName = queueName+new Integer(index++).toString();
			factory = new ActiveMQConnectionFactory(serverUrl);
			connection = factory.createConnection();
			session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(queueName);
			messageProducer = session.createProducer(null);

			message = session.createMessage();
			message.setIntProperty(propertyName, intValue1);
			messageProducer.send(destination, message);
			message.setIntProperty(propertyName, intValue2);
			messageProducer.send(destination, message);

			session.close();
		} catch (JMSException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public static int getIndex() {
		return index;
	}
}