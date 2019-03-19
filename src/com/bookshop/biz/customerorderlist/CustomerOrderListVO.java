package com.bookshop.biz.customerorderlist;

public class CustomerOrderListVO {
	private String customerOrder_OrderNum;
	private String book_BookNum;
	private int qty;
	private int amount;
	
	private String book_BookName;

	public String getCustomerOrder_OrderNum() {
		return customerOrder_OrderNum;
	}

	public void setCustomerOrder_OrderNum(String customerOrder_OrderNum) {
		this.customerOrder_OrderNum = customerOrder_OrderNum;
	}

	public String getBook_BookNum() {
		return book_BookNum;
	}

	public void setBook_BookNum(String book_BookNum) {
		this.book_BookNum = book_BookNum;
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

	public String getBook_BookName() {
		return book_BookName;
	}

	public void setBook_BookName(String book_BookName) {
		this.book_BookName = book_BookName;
	}

	@Override
	public String toString() {
		return "CustomerOrderListVO [customerOrder_OrderNum=" + customerOrder_OrderNum + ", book_BookNum="
				+ book_BookNum + ", qty=" + qty + ", amount=" + amount + ", book_BookName=" + book_BookName + "]";
	}
	
	
}
