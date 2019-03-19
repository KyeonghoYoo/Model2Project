package com.bookshop.biz.addressinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bookshop.biz.book.BookVO;
import com.multicampus.biz.common.JDBCUtil;

//2.  DAO(Data Access Object) 클래스
public class AddressInfoDAO {
	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// SQL 명령어
	private final String ADDRESSINFO_INSERT = 	"insert into addressinfo(customer_customerid, zipcode, baseaddress, detailaddress) values(?,?,?,?)";
	private final String ADDRESSINFO_UPDATE = 	"update addressinfo set zipcode=?, baseaddress=?, detailaddress=? where customer_customerid = ? and zipcode=?";
	private final String ADDRESSINFO_DELETE = 	"delete from addressinfo where customer_customerid = ? and zipcode=?";
	private final String ADDRESSINFO_GET = 		"select * from addressinfo where customer_customerid = ? and zipcode=?";
	private final String ADDRESSINFO_LIST = 	"select * from addressinfo where Customer_CustomerId = ?";

	// CRUD 관련 메소드
	// 배송지 등록
	public boolean insertAddressInfo(AddressInfoVO vo) {
		System.out.println("===> JDBC 기반으로 insertAddressInfo() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ADDRESSINFO_INSERT);
			stmt.setString(1, vo.getCustomer_CustomerId());
			stmt.setString(2, vo.getZipCode());
			stmt.setString(3, vo.getBaseAddress());
			stmt.setString(4, vo.getDetailAddress());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return true;
	}

	// 배송지 수정
	public boolean updateAddressInfo(AddressInfoVO vo, String zipCode) {
		System.out.println("===> JDBC 기반으로 updateAddressInfo() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ADDRESSINFO_UPDATE);
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

	// 배송지 삭제
	public boolean deleteAddressInfo(AddressInfoVO vo) {
		System.out.println("===> JDBC 기반으로 deleteAddressInfo() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ADDRESSINFO_DELETE);
			stmt.setString(1, vo.getCustomer_CustomerId());
			stmt.setString(2, vo.getZipCode());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return true;
	}

	// 배송지 상세 조회
	public AddressInfoVO getAddressInfo(AddressInfoVO vo) {
		System.out.println("===> JDBC 기반으로 getAddressInfo() 기능 처리");
		AddressInfoVO addressInfo = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ADDRESSINFO_GET);
			stmt.setString(1, vo.getCustomer_CustomerId());
			stmt.setString(2, vo.getZipCode());
			rs = stmt.executeQuery();
			if (rs.next()) {
				addressInfo = new AddressInfoVO();
				addressInfo.setCustomer_CustomerId(rs.getString("CUSTOMER_CUSTOMERID"));
				addressInfo.setZipCode(rs.getString("ZIPCODE"));
				addressInfo.setBaseAddress(rs.getString("BASEADDRESS"));
				addressInfo.setDetailAddress(rs.getString("DETAILADDRESS"));
			}
			JDBCUtil.close(rs, stmt, conn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);

		}
		return addressInfo;
	}

	// 배송지 목록 검색
	public List<AddressInfoVO> getAddressInfoList(AddressInfoVO vo) {
		System.out.println("===> JDBC 기반으로 getAddressInfoList() 기능 처리");
		List<AddressInfoVO> addressInfoList = new ArrayList<AddressInfoVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ADDRESSINFO_LIST);
			stmt.setString(1, vo.getCustomer_CustomerId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				AddressInfoVO addressInfo = new AddressInfoVO();
				addressInfo.setCustomer_CustomerId(rs.getString("CUSTOMER_CUSTOMERID"));
				addressInfo.setZipCode(rs.getString("ZIPCODE"));
				addressInfo.setBaseAddress(rs.getString("BASEADDRESS"));
				addressInfo.setDetailAddress(rs.getString("DETAILADDRESS"));
				addressInfoList.add(addressInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return addressInfoList;
	}
}
