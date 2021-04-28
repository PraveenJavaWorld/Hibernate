package com.nt.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Table(name = "PERSON_INFO2")
public class PersonInfo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	
	@Column(length = 20)
	@Type(type = "string")
	private String pname;
	
	@Column(length = 20)
	@Type(type = "string")
	private String address;
	
	private LocalDateTime dob;
	
	private LocalDate dom;
	
	private LocalTime doj;

}
