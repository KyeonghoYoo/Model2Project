package com.bookshop.biz.creditcardinfo;

public class CreditCardInfoVO {
	private String customer_CustomerId;
	private String cardNum;
	private String expirationDate;
	private String cardType;
	public String getCustomer_CustomerId() {
		return customer_CustomerId;
	}
	public void setCustomer_CustomerId(String customer_CustomerId) {
		this.customer_CustomerId = customer_CustomerId;
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
		return "CreditCardInfoVO [customer_CustomerId=" + customer_CustomerId + ", cardNum=" + cardNum
				+ ", expirationDate=" + expirationDate + ", cardType=" + cardType + "]";
	}

	
}
