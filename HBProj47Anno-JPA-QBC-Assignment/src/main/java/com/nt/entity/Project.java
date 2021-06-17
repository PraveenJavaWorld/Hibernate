package com.nt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Table(name = "PROJECTS_INFO")
public class Project implements Serializable{
	@Id
	//@GeneratedValue(generator = "PROJECT_SEQUENCE",strategy = GenerationType.AUTO)
	@Column(name = "PROJECT_ID")
	private Integer projID;
	
	@Column(name = "PROJECT_NAME",length = 20)
	@Type(type = "string")
	private String projName;
	
	@Column(name = "COST")
	private Float cost;
	
	@Column(name = "TEAM_SIZE")
	private Integer teamSize;
	
	@Column(name = "COMPANY",length = 20)
	@Type(type = "string")
	private String company;
	
	@Column(name = "LOCATION",length = 20)
	@Type(type = "string")
	private String location;

}
