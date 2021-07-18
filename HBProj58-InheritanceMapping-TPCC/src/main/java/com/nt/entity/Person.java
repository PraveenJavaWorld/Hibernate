package com.nt.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Person implements Serializable {
	
	private Integer id;
	private String name;
	private String address;

}
