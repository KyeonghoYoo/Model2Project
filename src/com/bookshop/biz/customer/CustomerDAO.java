package com.bookshop.biz.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.multicampus.biz.common.JDBCUtil;
import com.multicampus.biz.user.UserVO;

public class CustomerDAO {
	// JDBC 관련 변수
		private Connection conn = null;
		private PreparedStatement stmt = null;
		private ResultSet rs = null;
		
		// SQL 명령어
		private final String CUSTOMER_GET = 		"select * from customer where customerid=? and password=?";
		private final String CUSTOMER_INSERT = 		"insert into customer(customerid, password, name) values(?,?,?)";
		
		// CRUD 관련 메소드
		// 회원 가입
		public boolean insertCustomer(CustomerVO vo) {
			System.out.println("===> JDBC 기반으로 insertCustomer() 기능 처리");
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
		// 회원 상세 조회
		public CustomerVO getCustomer(CustomerVO vo) {
			System.out.println("===> JDBC 기반으로 getCustomer() 기능 처리");
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
