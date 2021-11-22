package com.nt.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class EmployeeDetails implements Serializable {
	
	private Integer empno;
	@NonNull
	private String name;
	@NonNull
	private String address;
	@NonNull
	private float salary;
	private Department dept;

}
