package org.webservices.dao;

import java.util.List;

import org.webservices.model.GcdNumbers;

public interface GcdDAO {

	public String storeNumsAndGcd(GcdNumbers gcdNumbers);
	public List<GcdNumbers> getGcdNumbers();
	public List<Integer> getGcdValues();
	public int gcdSum();

}
