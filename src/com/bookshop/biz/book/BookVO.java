package com.bookshop.biz.book;

//1. VO(Value Object == DTO(Data Transfer Object)) 클래스
public class BookVO {
	private String bookNum;
	private String bookName;
	private int stockQty;
	private int price;
	private String url;
	

	public String getBookNum() {
		return bookNum;
	}
	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getStockQty() {
		return stockQty;
	}
	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "BookVO [bookNum=" + bookNum + ", bookName=" + bookName + ", stockQty=" + stockQty + ", price=" + price
				+", url=" + url+ "]";
	}
	
}
