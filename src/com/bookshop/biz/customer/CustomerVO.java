package com.bookshop.biz.customer;

public class CustomerVO {
	private String customerId;
	private String password;
	private String name;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CustomerVO [customerId=" + customerId + ", password=" + password + ", name=" + name + "]";
	}
	
	
}
