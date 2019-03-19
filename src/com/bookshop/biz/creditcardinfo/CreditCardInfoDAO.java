package com.bookshop.biz.creditcardinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bookshop.biz.book.BookVO;
import com.multicampus.biz.common.JDBCUtil;

//2. DAO(Data Access Object) Ŭ����
public class CreditCardInfoDAO {
	// JDBC ���� ����
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// SQL ��ɾ�
	private final String CREDITCARDINFO_INSERT = 	"insert into creditcardinfo(customer_customerid, cardnum, expirationdate, cardtype) values(?,?,?,?)";
	private final String CREDITCARDINFO_UPDATE = 	"update creditcardinfo set cardNum=?, expirationDate=?, cardType=? where cardNum = ?";
	private final String CREDITCARDINFO_DELETE = 	"delete from creditcardinfo where cardNum = ?";
	private final String CREDITCARDINFO_GET = 		"select * from creditcardinfo where cardNum = ?";
	private final String CREDITCARDINFO_LIST = 		"select * from creditcardinfo where customer_CustomerId = ?";

	// CRUD ���� �޼ҵ�
	// ī������ ���
	public boolean insertCreditCardInfoInfo(CreditCardInfoVO vo) {
		System.out.println("===> JDBC ������� insertCreditCardInfoInfo() ��� ó��");
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

	// ī������ ����
	public boolean updateCreditCardInfo(CreditCardInfoVO vo, String exCardNum) {
		System.out.println("===> JDBC ������� updateCreditCardInfo() ��� ó��");
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

	// ī������ ����
	public boolean deleteCreditCardInfo(CreditCardInfoVO vo) {
		System.out.println("===> JDBC ������� deleteCreditCardInfo() ��� ó��");
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

	// ī������ �� ��ȸ
	public CreditCardInfoVO getCreditCardInfo(CreditCardInfoVO vo) {
		System.out.println("===> JDBC ������� getCreditCardInfo() ��� ó��");
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

	// ī������ ��� �˻�
	public List<CreditCardInfoVO> getCreditCardInfoList(CreditCardInfoVO vo) {
		System.out.println("===> JDBC ������� getCreditCardInfoList() ��� ó��");
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
