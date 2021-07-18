package com.nt.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Employee extends Person {
	
	private String designation;
	private Float salary;
	private Integer deptNo;

}
