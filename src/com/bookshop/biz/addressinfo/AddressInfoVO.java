package com.bookshop.biz.addressinfo;

public class AddressInfoVO {
	private String customer_CustomerId;
	private String zipCode;
	private String baseAddress;
	private String detailAddress;
	
	public String getCustomer_CustomerId() {
		return customer_CustomerId;
	}
	public void setCustomer_CustomerId(String customer_CustomerId) {
		this.customer_CustomerId = customer_CustomerId;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getBaseAddress() {
		return baseAddress;
	}
	public void setBaseAddress(String baseAddress) {
		this.baseAddress = baseAddress;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
	@Override
	public String toString() {
		return "AddressInfoVO [Customer_CustomerId=" + customer_CustomerId + ", ZipCode=" + zipCode + ", BaseAddress="
				+ baseAddress + ", DetailAddress=" + detailAddress + "]";
	}
	
}
