package com.nt.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class StudentInfo implements Serializable {
	
	private Integer sno;
	private String sname;
	private Float average;
	private Address address;

}
