package com.nt.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class SuperMarket_Membership implements Serializable {
	
	//bean properties
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mid;
	private String name;
	private String address;
	private Integer rewardpoints;
	//getter and setters
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getRewardpoints() {
		return rewardpoints;
	}
	public void setRewardpoints(Integer rewardpoints) {
		this.rewardpoints = rewardpoints;
	}
	
	@Override
	public String toString() {
		return "SuperMarket_Membership [mid=" + mid + ", name=" + name + ", address=" + address + ", rewardpoints="
				+ rewardpoints + "]";
	}
	
	

}
