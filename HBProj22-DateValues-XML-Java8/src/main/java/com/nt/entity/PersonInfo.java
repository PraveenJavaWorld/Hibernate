package com.nt.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data
public class PersonInfo implements Serializable {
	
	private int pid;
	private String pname;
	private String address;
	private LocalDateTime dob;
	private LocalDate dom;
	private LocalTime doj;

}
