package com.bookshop.biz.customerorder;

import java.sql.Date;

public class CustomerOrderVO {
	private String orderNum;
	private String Customer_CustomerId;
	private Date orderDate;
	private int paymentAmount;
	private String zipCode;
	private String baseAddress;
	private String detailAddress;
	private String cardNum;
	private String expirationDate;
	private String cardType;
	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getCustomer_CustomerId() {
		return Customer_CustomerId;
	}
	public void setCustomer_CustomerId(String customer_CustomerId) {
		Customer_CustomerId = customer_CustomerId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
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
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	@Override
	public String toString() {
		return "CustomerOrderVO [orderNum=" + orderNum + ", Customer_CustomerId=" + Customer_CustomerId
				+ ", OrderDate=" + orderDate + ", paymentAmount=" + paymentAmount + ", zipCode=" + zipCode + ", baseAddress=" + baseAddress
				+ ", detailAddress=" + detailAddress + ", cardNum=" + cardNum + ", expirationDate=" + expirationDate
				+ ", cardType=" + cardType + "]";
	}
	
	
}
