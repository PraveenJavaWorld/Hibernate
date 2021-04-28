package com.nt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Table(name = "PERSON_INFO1")
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
	
	@Temporal(TemporalType.TIMESTAMP)//default timestamp
	private Date dob;
	
	@Temporal(TemporalType.DATE)
	private Date dom;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date doj;

}
