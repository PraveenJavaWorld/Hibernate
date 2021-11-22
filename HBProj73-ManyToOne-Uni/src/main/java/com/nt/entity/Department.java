package com.nt.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {
	
	private Integer deptno;
	@NonNull
	private String name;
	@NonNull
	private String location;
	@NonNull
	private Integer capacity;

}
