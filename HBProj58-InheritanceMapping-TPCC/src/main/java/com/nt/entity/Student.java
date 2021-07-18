package com.nt.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Student extends Person {
	
	private String college;
	private String branch;
	private Float average;

}
