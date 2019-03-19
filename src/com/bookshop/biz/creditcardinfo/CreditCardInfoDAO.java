package com.bookshop.biz.creditcardinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bookshop.biz.book.BookVO;
import com.multicampus.biz.common.JDBCUtil;

//2. DAO(Data Access Object) 클래스
public class CreditCardInfoDAO {
	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// SQL 명령어
	private final String CREDITCARDINFO_INSERT = 	"insert into creditcardinfo(customer_customerid, cardnum, expirationdate, cardtype) values(?,?,?,?)";
	private final String CREDITCARDINFO_UPDATE = 	"update creditcardinfo set cardNum=?, expirationDate=?, cardType=? where cardNum = ?";
	private final String CREDITCARDINFO_DELETE = 	"delete from creditcardinfo where cardNum = ?";
	private final String CREDITCARDINFO_GET = 		"select * from creditcardinfo where cardNum = ?";
	private final String CREDITCARDINFO_LIST = 		"select * from creditcardinfo where customer_CustomerId = ?";

	// CRUD 관련 메소드
	// 카드정보 등록
	public boolean insertCreditCardInfoInfo(CreditCardInfoVO vo) {
		System.out.println("===> JDBC 기반으로 insertCreditCardInfoInfo() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CREDITCARDINFO_INSERT);
			stmt.setString(1, vo.getCustomer_CustomerId());
			stmt.setString(2, vo.getCardNum());
			stmt.setString(3, vo.getExpirationDate());
			stmt.setString(4, vo.getCardType());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return true;
	}

	// 카드정보 수정
	public boolean updateCreditCardInfo(CreditCardInfoVO vo, String exCardNum) {
		System.out.println("===> JDBC 기반으로 updateCreditCardInfo() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CREDITCARDINFO_UPDATE);
			stmt.setString(1, vo.getCardNum());
			stmt.setString(2, vo.getExpirationDate());
			stmt.setString(3, vo.getCardType());
			stmt.setString(4, exCardNum);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return true;
	}

	// 카드정보 삭제
	public boolean deleteCreditCardInfo(CreditCardInfoVO vo) {
		System.out.println("===> JDBC 기반으로 deleteCreditCardInfo() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CREDITCARDINFO_DELETE);
			stmt.setString(1, vo.getCardNum());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return true;
	}

	// 카드정보 상세 조회
	public CreditCardInfoVO getCreditCardInfo(CreditCardInfoVO vo) {
		System.out.println("===> JDBC 기반으로 getCreditCardInfo() 기능 처리");
		CreditCardInfoVO creditCardInfo = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CREDITCARDINFO_GET);
			stmt.setString(1, vo.getCardNum());
			rs = stmt.executeQuery();
			if (rs.next()) {
				creditCardInfo = new CreditCardInfoVO();
				creditCardInfo.setCustomer_CustomerId(rs.getString("CUSTOMER_CUSTOMERID"));
				creditCardInfo.setCardNum(rs.getString("CARDNUM"));
				creditCardInfo.setExpirationDate(rs.getString("EXPIRATIONDATE"));
				creditCardInfo.setCardType(rs.getString("CARDTYPE"));;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);

		}
		return creditCardInfo;
	}

	// 카드정보 목록 검색
	public List<CreditCardInfoVO> getCreditCardInfoList(CreditCardInfoVO vo) {
		System.out.println("===> JDBC 기반으로 getCreditCardInfoList() 기능 처리");
		List<CreditCardInfoVO> addressInfoList = new ArrayList<CreditCardInfoVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CREDITCARDINFO_LIST);
			stmt.setString(1, vo.getCustomer_CustomerId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				CreditCardInfoVO creditCardInfo = new CreditCardInfoVO();
				creditCardInfo.setCustomer_CustomerId(rs.getString("CUSTOMER_CUSTOMERID"));
				creditCardInfo.setCardNum(rs.getString("CARDNUM"));
				creditCardInfo.setExpirationDate(rs.getString("EXPIRATIONDATE"));
				creditCardInfo.setCardType(rs.getString("CARDTYPE"));
				addressInfoList.add(creditCardInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return addressInfoList;
	}
}
