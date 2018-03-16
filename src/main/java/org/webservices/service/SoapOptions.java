package org.webservices.service;

import java.util.List;

import javax.jws.WebResult;
import javax.jws.WebService;


@WebService(name="GcdProcessor")
public interface SoapOptions {

	public @WebResult(name="JMSGCD") int gcd();
	public @WebResult(name="GcdValues") List<Integer> gcdList();
	public @WebResult(name="GCDSum") int gcdSum();
	
	
}
