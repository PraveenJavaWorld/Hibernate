package com.nt.entity;

import lombok.Data;

@Data
public class Address {
	
	private String doorNo;
	private String streetName;
	private String city;
	private String state;
	private String country;
	private Long pincode;

}
