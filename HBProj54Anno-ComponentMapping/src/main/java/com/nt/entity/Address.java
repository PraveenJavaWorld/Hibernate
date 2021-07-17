package com.nt.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {
	
	private String doorNo;
	private String streetName;
	private String city;
	private String state;
	private String country;
	private Long pincode;

}
