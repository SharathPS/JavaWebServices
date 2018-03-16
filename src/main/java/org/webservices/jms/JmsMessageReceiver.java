package org.webservices.jms;



import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsMessageReceiver {
	private static String serverUrl = "vm://localhost";
	private static final String queueName = "intValueName";
	private static String propertyName = "intValue";
	@SuppressWarnings("rawtypes")
	private static List listAll = new ArrayList();
	private static boolean allDiscarded = false;

	public static void receiveMessage(String queueName) throws JMSException {
		ConnectionFactory factory;
		Connection connection = null;
		Session session;
		Destination destination;
		MessageConsumer messageConsumer;

		try {
			factory = new ActiveMQConnectionFactory(serverUrl);

			connection = factory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(queueName);

			messageConsumer = session.createConsumer(destination);
			connection.start();
			messageConsumer.receive(1);

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getMessage(String queueName) throws JMSException {
		ConnectionFactory factory;
		Connection connection = null;
		Session session;
		Queue queue;
		List list = new ArrayList();
		int index = 0;

		try {
			factory = new ActiveMQConnectionFactory(serverUrl);

			connection = factory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = session.createQueue(queueName);

			connection.start();

			QueueBrowser browser = session.createBrowser(queue);
			Enumeration e = browser.getEnumeration();
			while (e.hasMoreElements()) {
				Message message = (Message)e.nextElement();
				list.add(index++, message.getIntProperty(propertyName));
			}
			browser.close();
			session.close();

			return list;
		} catch (JMSException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getAllMessage() throws JMSException {
		try {
			if (listAll.size() == 0 && !allDiscarded) {
				for (int i=0; i<JmsMessageSender.getIndex(); i++) {
					String name = queueName+new Integer(i).toString();
					listAll.add(JmsMessageReceiver.getMessage(name));
				}
			}
			return listAll;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public static void discard() throws JMSException {
		try {
			listAll.remove(0);
			if (listAll.size() == 0) {
				allDiscarded = true;
				receiveMessage(queueName);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
}