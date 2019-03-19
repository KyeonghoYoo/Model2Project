package com.bookshop.biz.shoppingbasketlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bookshop.biz.book.BookVO;
import com.multicampus.biz.common.JDBCUtil;

public class ShoppingBasketListDAO {
	// JDBC ���� ����
		private Connection conn = null;
		private PreparedStatement stmt = null;
		private ResultSet rs = null;

		// SQL ��ɾ�
		private final String ShoppingBasketList_INSERT = "insert into shoppingbasketlist(customer_customerid, book_bookNum, adddate, qty, amount) values(?,?,now(),?,?)";
		private final String ShoppingBasketList_DELETE = "delete from shoppingbasketlist where customer_customerid = ? and book_booknum = ?";
		private final String ShoppingBasketList_LIST =   "select * from shoppingbasketlist a, book b where a.Book_BookNum = b.BookNum and a.Customer_CustomerId = ? order by book_booknum desc";

		// CRUD ���� �޼ҵ�
		// ��ٱ��� ���
		public void insertShoppingBasketList(ShoppingBasketListVO vo) {
			System.out.println("===> JDBC ������� insertShoppingBasketList() ��� ó��");
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(ShoppingBasketList_INSERT);
				stmt.setString(1, vo.getCustomer_customerId());
				stmt.setString(2, vo.getBook_bookNum());
				stmt.setInt(3, vo.getQty());
				stmt.setInt(4, vo.getAmount());
				stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(stmt, conn);
			}
		}


		// ��ٱ��� ����
		public void deleteShoppingBasketList(ShoppingBasketListVO vo) {
			System.out.println("===> JDBC ������� deleteShoppingBasketList() ��� ó��");
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(ShoppingBasketList_DELETE);
				stmt.setString(1, vo.getCustomer_customerId());
				stmt.setString(2, vo.getBook_bookNum());
				stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(stmt, conn);
			}
		}


		// ��ٱ��� ��� �˻�
		public List<ShoppingBasketListVO> getShoppingBasketList(String CustomerId) {
			System.out.println("===> JDBC ������� getShoppingBasketList() ��� ó��");
			List<ShoppingBasketListVO> shoppingBasketList = new ArrayList<ShoppingBasketListVO>();
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(ShoppingBasketList_LIST);
				stmt.setString(1, CustomerId);
				rs = stmt.executeQuery();
				while (rs.next()) {
					ShoppingBasketListVO shoppingBasket = new ShoppingBasketListVO();
					shoppingBasket.setCustomer_customerId(rs.getString("CUSTOMER_CUSTOMERID"));
					shoppingBasket.setBook_bookName(rs.getString("BOOKNAME"));
					shoppingBasket.setBook_bookNum(rs.getString("BOOK_BOOKNUM"));
					shoppingBasket.setAddDate(rs.getDate("ADDDATE"));
					shoppingBasket.setQty(rs.getInt("QTY"));
					shoppingBasket.setAmount(rs.getInt("AMOUNT"));
					shoppingBasketList.add(shoppingBasket);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
			return shoppingBasketList;
		}
}
