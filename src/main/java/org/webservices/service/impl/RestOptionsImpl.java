package org.webservices.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Component;
import org.webservices.business.CalculateGcd;
import org.webservices.dao.GcdDAOImpl;
import org.webservices.jms.JmsMessageSender;
import org.webservices.model.GcdNumbers;
import org.webservices.service.RestOptions;

@Component
public class RestOptionsImpl implements RestOptions {
	private final String queueName = "intValueName";

	@Override
	public List<Integer> list() {
		GcdDAOImpl impl = new GcdDAOImpl();		
		List<GcdNumbers> gcdNumbers = impl.getGcdNumbers();
		List<Integer> gcdInts = new ArrayList<Integer>();

		for(GcdNumbers gcd : gcdNumbers) {
			gcdInts.add(gcd.getNum1());
			gcdInts.add(gcd.getNum2());
		}

		return gcdInts ;
	}

	@Override
	public String push(int i1, int i2) {

		try 
		{
			CalculateGcd c = new CalculateGcd(i1,i2);
			GcdNumbers gcd = new GcdNumbers();
			gcd.setNum1(i1);
			gcd.setNum2(i2);
			gcd.setGcdValue(c.calGcdValue());		
			GcdDAOImpl impl = new GcdDAOImpl();		
			String result  = impl.storeNumsAndGcd(gcd);
			if(result.equals("success")) {
				JmsMessageSender.sendMessage(queueName, i1, i2);
				return (HttpStatus.OK).toString();
			}
			else
			{
				throw new Exception();
			}
		}
		catch (Exception e) 
		{
			return (HttpStatus.INTERNAL_SERVER_ERROR).toString();
		}

	}


}
