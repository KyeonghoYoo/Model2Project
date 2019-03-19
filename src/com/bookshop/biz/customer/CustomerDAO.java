package com.bookshop.biz.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.multicampus.biz.common.JDBCUtil;
import com.multicampus.biz.user.UserVO;

public class CustomerDAO {
	// JDBC ���� ����
		private Connection conn = null;
		private PreparedStatement stmt = null;
		private ResultSet rs = null;
		
		// SQL ��ɾ�
		private final String CUSTOMER_GET = 		"select * from customer where customerid=? and password=?";
		private final String CUSTOMER_INSERT = 		"insert into customer(customerid, password, name) values(?,?,?)";
		
		// CRUD ���� �޼ҵ�
		// ȸ�� ����
		public boolean insertCustomer(CustomerVO vo) {
			System.out.println("===> JDBC ������� insertCustomer() ��� ó��");
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(CUSTOMER_INSERT);
				stmt.setString(1, vo.getCustomerId());
				stmt.setString(2, vo.getPassword());
				stmt.setString(3, vo.getName());
				stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.close(stmt, conn);
			}
			return true;
		}
		// ȸ�� �� ��ȸ
		public CustomerVO getCustomer(CustomerVO vo) {
			System.out.println("===> JDBC ������� getCustomer() ��� ó��");
			CustomerVO customer = null;
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(CUSTOMER_GET);
				stmt.setString(1, vo.getCustomerId());
				stmt.setString(2, vo.getPassword());
				rs = stmt.executeQuery();
				if(rs.next()){
					customer = new CustomerVO();
					customer.setCustomerId(rs.getString("CUSTOMERID"));
					customer.setPassword(rs.getString("PASSWORD"));
					customer.setName(rs.getString("NAME"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
			return customer;
		}
}
