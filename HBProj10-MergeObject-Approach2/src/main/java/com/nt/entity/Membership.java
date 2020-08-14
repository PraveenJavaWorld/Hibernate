package com.nt.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Membership implements Serializable{
	
	private Long mid;
	private String name;
	private String address;
	private Long rewardPoints;
	
	
	public Long getMid() {
		return mid;
	}
	public void setMid(Long mid) {
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
	public Long getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Long rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
	
	@Override
	public String toString() {
		return "Membership [mid=" + mid + ", name=" + name + ", address=" + address + ", rewardPoints=" + rewardPoints
				+ "]";
	}
	
	
	
	

}
