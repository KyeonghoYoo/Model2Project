package com.bookshop.biz.customerorderlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bookshop.biz.shoppingbasketlist.ShoppingBasketListVO;
import com.multicampus.biz.common.JDBCUtil;

public class CustomerOrderListDAO {
	// JDBC ���� ����
		private Connection conn = null;
		private PreparedStatement stmt = null;
		private ResultSet rs = null;

		// SQL ��ɾ�
		private final String CUSTOMERORDERLIST_INSERT = "insert into customerorderlist(CustomerOrder_OrderNum, Book_BookNum, Qty, Amount) values(?,?,?,?)";
		private final String CUSTOMERORDERLIST_DELETE = "delete from shoppingbasketlist where customer_customerid = ? and book_booknum = ?";
		private final String CUSTOMERORDERLIST_LIST =   "select * from customerorderlist a, book b where a.Book_BookNum = b.BookNum and a.CustomerOrder_OrderNum = ? order by book_booknum desc";

		// CRUD ���� �޼ҵ�
		// ��ٱ��� ���
		public void insertCustomerOrderList(CustomerOrderListVO vo) {
			System.out.println("===> JDBC ������� insertCustomerOrderList() ��� ó��");
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(CUSTOMERORDERLIST_INSERT);
				stmt.setString(1, vo.getCustomerOrder_OrderNum());
				stmt.setString(2, vo.getBook_BookNum());
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
				stmt = conn.prepareStatement(CUSTOMERORDERLIST_DELETE);
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
		public List<CustomerOrderListVO> getCustomerOrderListList(CustomerOrderListVO vo) {
			System.out.println("===> JDBC ������� getCustomerOrderListList() ��� ó��");
			List<CustomerOrderListVO> customerOrderListList = new ArrayList<CustomerOrderListVO>();
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(CUSTOMERORDERLIST_LIST);
				stmt.setString(1, vo.getCustomerOrder_OrderNum());
				rs = stmt.executeQuery();
				while (rs.next()) {
					CustomerOrderListVO customerOrderList = new CustomerOrderListVO();
					customerOrderList.setCustomerOrder_OrderNum(rs.getString("CUSTOMERORDER_ORDERNUM"));
					customerOrderList.setBook_BookNum(rs.getString("BOOK_BOOKNUM"));
					customerOrderList.setBook_BookName(rs.getString("BOOKNAME"));
					customerOrderList.setQty(rs.getInt("QTY"));
					customerOrderList.setAmount(rs.getInt("AMOUNT"));
					customerOrderListList.add(customerOrderList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
			return customerOrderListList;
		}
}
