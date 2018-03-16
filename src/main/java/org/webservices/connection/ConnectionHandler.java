package org.webservices.connection;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.LoggerFactory;

public class ConnectionHandler {

	private static SessionFactory sessionFactory;
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ConnectionHandler.class);

	public static SessionFactory getSessionFactory() {
		try 
		{
			if (sessionFactory == null) 
			{
				Configuration configuration = new Configuration().configure();
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);           
			}
		}
		catch(HibernateException e)
		{
			logger.info("Exception in Connection Handler: "+e.getMessage());
		}

		return sessionFactory;
	}
}
