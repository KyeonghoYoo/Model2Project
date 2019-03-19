package com.bookshop.biz.shoppingbasketlist;

import java.sql.Date;

public class ShoppingBasketListVO {
	private String customer_customerId;
	private String book_bookNum;
	private Date addDate;
	private int qty;
	private int amount;
	
	private String book_bookName; 

	public String getCustomer_customerId() {
		return customer_customerId;
	}
	public void setCustomer_customerId(String customer_customerId) {
		this.customer_customerId = customer_customerId;
	}
	public String getBook_bookNum() {
		return book_bookNum;
	}
	public void setBook_bookNum(String book_bookNum) {
		this.book_bookNum = book_bookNum;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getBook_bookName() {
		return book_bookName;
	}
	public void setBook_bookName(String book_bookName) {
		this.book_bookName = book_bookName;
	}
	@Override
	public String toString() {
		return "ShoppingBasketListVO [customer_customerId=" + customer_customerId + ", book_bookNum=" + book_bookNum
				+ ", addDate=" + addDate + ", qty=" + qty + ", amount=" + amount + ", book_bookName=" + book_bookName + "]";
	}
	
}
