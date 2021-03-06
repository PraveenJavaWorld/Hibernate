package com.nt.entity;

public class Product {
	
	//bean properties
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;
	
	//setters and getters
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getQty() {
		return qty;
	}
	public void setQty(Float quantity) {
		this.qty = quantity;
	}
	
	
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", price=" + price + ", quantity=" + qty + "]";
	}
	
	

}
