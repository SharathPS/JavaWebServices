package org.webservices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gcd_numbers")
public class GcdNumbers {
	
	private int sno;
	private int num1;
	private int num2;
	private int gcdValue;

	@Id	
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column(name = "sno", unique = true, nullable = false)
	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "num1", nullable = false)
	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	@Column(name = "num2", nullable = false)
	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}
	
	@Column(name = "gcd_val", nullable = false)
	public int getGcdValue() {
		return gcdValue;
	}

	public void setGcdValue(int gcdValue) {
		this.gcdValue = gcdValue;
	}
	
	public GcdNumbers() {
		
	}
	
	public GcdNumbers(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
	}

}
