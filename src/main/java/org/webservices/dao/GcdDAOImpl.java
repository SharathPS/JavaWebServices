package org.webservices.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.webservices.connection.ConnectionHandler;
import org.webservices.model.GcdNumbers;

public class GcdDAOImpl implements GcdDAO{
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(GcdDAOImpl.class);

	SessionFactory sessionFactory = null;
	public GcdDAOImpl() 
	{
		sessionFactory = ConnectionHandler.getSessionFactory();
	}

	@Override
	public String storeNumsAndGcd(GcdNumbers gcdNumbers) {

		String res = "fail";
		if(sessionFactory!= null) {
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(gcdNumbers);
				if(gcdNumbers.getSno()>0) {
					logger.info("Numbers inserted into database");
				}
				session.getTransaction().commit();
				res="success";
			}catch(Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
			}
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GcdNumbers> getGcdNumbers() {

		List<GcdNumbers> gcdNumbers = new ArrayList<GcdNumbers>();
		if(sessionFactory!= null) {
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				String queryStr = "select * from gcd_numbers";
				Query query = session.createSQLQuery(queryStr).addEntity(GcdNumbers.class);
				gcdNumbers=(List<GcdNumbers>) query.list();

			}catch(Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
			}
		}
		return gcdNumbers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getGcdValues() {
		// TODO Auto-generated method stub
		List<Integer> gcdNumbers = new ArrayList<Integer>();
		if(sessionFactory!= null) {
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				String queryStr = "select gcd_val from gcd_numbers";
				Query query = session.createSQLQuery(queryStr);
				gcdNumbers=(List<Integer>) query.list();
			}catch(Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
			}
		}
		return gcdNumbers;
	}

	@Override
	public int gcdSum() {

		int res =0 ;
		if(sessionFactory!= null) {
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				String queryStr = "select sum(gcd_val) from gcd_numbers";
				Query query = session.createSQLQuery(queryStr);
				@SuppressWarnings("rawtypes")
				List listResult = query.list();
				Number num  = (Number) listResult.get(0);
				res = num.intValue();
			}catch(Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
			}
		}
		return res;
	}


}
