package com.bookshop.biz.customerorder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bookshop.biz.addressinfo.AddressInfoVO;
import com.multicampus.biz.common.JDBCUtil;

//2. DAO(Data Access Object) Ŭ����
public class CustomerOrderDAO {
	// JDBC ���� ����
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// SQL ��ɾ�
	private final String CUSTOMERORDER_INSERT = "insert into customerorder(OrderNum, Customer_CustomerId, OrderDate, PaymentAmount, ZipCode,"
												+ " BaseAddress, DetailAddress, CardNum, ExpirationDate, CardType) values((select count(*)+1 from customerorder a),"
												+ "?,now(),?,?,?,?,?,?,?)";

	private final String CUSTOMERORDER_UPDATE = "update addressinfo set zipcode=?, baseaddress=?, detailaddress=? where customer_customerid = ? and zipcode=?";
	private final String CUSTOMERORDER_DELETE = "delete from customerorder where ordernum = ?";
	private final String CUSTOMERORDER_GET = 	"select * from customerorder where ordernum = ?";
	private final String CUSTOMERORDER_LIST = 	"select * from customerorder where Customer_CustomerId = ?";
	
	private final String CUSTOMERORDER_LASTESTORDERNUM = "select max(ordernum) from customerorder where customer_customerid = ?";

	// CRUD ���� �޼ҵ�
	// �ֹ� ���
	public boolean insertCustomerOrder(CustomerOrderVO vo) {
		System.out.println("===> JDBC ������� insertCustomerOrder() ��� ó��");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CUSTOMERORDER_INSERT);
			stmt.setString(1, vo.getCustomer_CustomerId());
			stmt.setInt(2, vo.getPaymentAmount());
			stmt.setString(3, vo.getZipCode());
			stmt.setString(4, vo.getBaseAddress());
			stmt.setString(5, vo.getDetailAddress());
			stmt.setString(6, vo.getCardNum());
			stmt.setString(7, vo.getExpirationDate());
			stmt.setString(8, vo.getCardType());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return true;
	}

	// ����� ����
	public boolean updateAddressInfo(AddressInfoVO vo, String zipCode) {
		System.out.println("===> JDBC ������� updateAddressInfo() ��� ó��");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CUSTOMERORDER_UPDATE);
			stmt.setString(1, vo.getZipCode());
			stmt.setString(2, vo.getBaseAddress());
			stmt.setString(3, vo.getDetailAddress());
			stmt.setString(4, vo.getCustomer_CustomerId());
			stmt.setString(5, zipCode);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return true;
	}

	// ����� ����
	public boolean deleteCustomerOrder(CustomerOrderVO vo) {
		System.out.println("===> JDBC ������� deleteCustomerOrder() ��� ó��");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CUSTOMERORDER_DELETE);
			stmt.setString(1, vo.getOrderNum());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return true;
	}

	// ���� �� ��ȸ
	public CustomerOrderVO getCustomerOrder(CustomerOrderVO vo) {
		System.out.println("===> JDBC ������� getCustomerOrder() ��� ó��");
		CustomerOrderVO customerOrder = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CUSTOMERORDER_GET);
			stmt.setString(1, vo.getOrderNum());
			rs = stmt.executeQuery();
			if (rs.next()) {
				customerOrder = new CustomerOrderVO();
				customerOrder.setOrderNum(rs.getString("ORDERNUM"));
				customerOrder.setCustomer_CustomerId(rs.getString("CUSTOMER_CUSTOMERID"));
				customerOrder.setOrderDate(rs.getDate("ORDERDATE"));
				customerOrder.setPaymentAmount(rs.getInt("PAYMENTAMOUNT"));
				customerOrder.setZipCode(rs.getString("ZIPCODE"));
				customerOrder.setBaseAddress(rs.getString("BASEADDRESS"));
				customerOrder.setDetailAddress(rs.getString("DETAILADDRESS"));
				customerOrder.setCardNum(rs.getString("CARDNUM"));
				customerOrder.setExpirationDate(rs.getString("EXPIRATIONDATE"));
				customerOrder.setCardType(rs.getString("CARDTYPE"));
			}
			JDBCUtil.close(rs, stmt, conn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);

		}
		return customerOrder;
	}

	// �ֹ� ��� �˻�
	public List<CustomerOrderVO> getCustomerOrderList(CustomerOrderVO vo) {
		System.out.println("===> JDBC ������� getCustomerOrderList() ��� ó��");
		List<CustomerOrderVO> customerOrderList = new ArrayList<CustomerOrderVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CUSTOMERORDER_LIST);
			stmt.setString(1, vo.getCustomer_CustomerId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				CustomerOrderVO customerOrder = new CustomerOrderVO();
				customerOrder.setOrderNum(rs.getString("ORDERNUM"));
				customerOrder.setCustomer_CustomerId(rs.getString("CUSTOMER_CUSTOMERID"));
				customerOrder.setOrderDate(rs.getDate("ORDERDATE"));
				customerOrder.setPaymentAmount(rs.getInt("PAYMENTAMOUNT"));
				customerOrder.setZipCode(rs.getString("ZIPCODE"));
				customerOrder.setBaseAddress(rs.getString("BASEADDRESS"));
				customerOrder.setDetailAddress(rs.getString("DETAILADDRESS"));
				customerOrder.setCardNum(rs.getString("CARDNUM"));
				customerOrder.setExpirationDate(rs.getString("EXPIRATIONDATE"));
				customerOrder.setCardType(rs.getString("CARDTYPE"));
				customerOrderList.add(customerOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return customerOrderList;
	}
	
	public CustomerOrderVO getLastestOrderNum(CustomerOrderVO vo) {
		System.out.println("===> JDBC ������� getLastestOrderNum() ��� ó��");
		CustomerOrderVO customerOrder = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CUSTOMERORDER_LASTESTORDERNUM);
			stmt.setString(1, vo.getCustomer_CustomerId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				customerOrder = new CustomerOrderVO();
				customerOrder.setOrderNum(rs.getString("MAX(ORDERNUM)"));
			}
			JDBCUtil.close(rs, stmt, conn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);

		}
		return customerOrder;
	}
}

