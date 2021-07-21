package com.nt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
public class JobSeeker implements Serializable {
	
	@Id
	@GeneratedValue
	@Type(type = "int")
	private Integer id;
	
	@Column(length = 15)
	@Type(type = "string")
	private String name;
	
	@Column(length = 15)
	@Type(type = "string")
	private String address;
	
	@Lob
	//@Type(type = "binary")
	private byte[] photo;
	
	@Lob
	//@Type(type = "characters")
	private char[] resume;

}
