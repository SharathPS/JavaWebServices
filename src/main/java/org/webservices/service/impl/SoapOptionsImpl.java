package org.webservices.service.impl;

import java.util.List;

import org.webservices.business.CalculateGcd;
import org.webservices.dao.GcdDAOImpl;
import org.webservices.jms.JmsMessageReceiver;
import org.webservices.service.SoapOptions;

public class SoapOptionsImpl implements SoapOptions {

	@SuppressWarnings("unchecked")
	@Override
	public int gcd() {
		@SuppressWarnings("rawtypes")
		List listAll, listItem;

		try
		{
			listAll = JmsMessageReceiver.getAllMessage();
			if (listAll == null || listAll.size() == 0) {
				return 0;
			}
			listItem = (List<Integer>)listAll.get(0);
			CalculateGcd c = new CalculateGcd((int) listItem.get(0),(int) listItem.get(1));
			JmsMessageReceiver.discard();
			return c.calGcdValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}

	}

	@Override
	public List<Integer> gcdList() {
		GcdDAOImpl gcdImpl = new GcdDAOImpl();		
		List<Integer> gcdNumbers = gcdImpl.getGcdValues();
		return gcdNumbers;

	}

	@Override
	public int gcdSum() {
		GcdDAOImpl gcdImpl = new GcdDAOImpl();		
		return gcdImpl.gcdSum();

	}

}
