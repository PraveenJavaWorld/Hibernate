package com.nt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Data
@Entity
public class Product {

	// bean properties
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)*/
	
	@Id
	@SequenceGenerator(name = "gen1",
						sequenceName = "PRODUCT_SEQUENCE",
						 allocationSize = 1)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.AUTO) 
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;

}
