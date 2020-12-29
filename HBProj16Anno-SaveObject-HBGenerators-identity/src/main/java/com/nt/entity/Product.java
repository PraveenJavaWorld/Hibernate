package com.nt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Product {

	// bean properties
	@Id
	@GenericGenerator(name = "gen1",strategy = "identity")
	@GeneratedValue(generator = "gen1")
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;

}
