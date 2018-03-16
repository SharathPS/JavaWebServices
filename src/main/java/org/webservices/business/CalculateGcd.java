package org.webservices.business;

import java.math.BigInteger;

public class CalculateGcd{

	private int num1;
	private int num2;
	
	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public CalculateGcd(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
	}

	public int calGcdValue() {		
		BigInteger b1 = BigInteger.valueOf(num1);
	    BigInteger b2 = BigInteger.valueOf(num2);
	    BigInteger gcd = b1.gcd(b2);
	    return gcd.intValue();
	}

}
