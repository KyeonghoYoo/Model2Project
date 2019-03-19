package com.bookshop.biz.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.multicampus.biz.board.BoardVO;
import com.multicampus.biz.common.JDBCUtil;

//2. DAO(Data Access Object) 클래스
public class BookDAO {
	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// SQL 명령문
//	private final String BOOK_INSERT = "insert into book(title, writer, content, regDate) values(?,?,?,now())";
//	private final String BOOK_UPDATE = "update book set title=?, content=? where seq=?";
	// private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOOK_DELETE = "delete from book where booknum=?";
	private final String BOOK_GET = "select * from book where booknum=?";
	private final String BOOK_LIST = "select * from book order by booknum desc";
	private final String BOOK_SEARCH = "select * from book where bookname like CONCAT('%', ?, '%')";
	// CRUD 관련 메소드
	// 책 등록
//	public void insertBook(BookVO vo) {
//		System.out.println("===> JDBC 기반으로 insertBook() 기능 처리");
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(BOOK_INSERT);
//			stmt.setString(1, vo.getTitle());
//			stmt.setString(2, vo.getWriter());
//			stmt.setString(3, vo.getContent());
//			stmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(stmt, conn);
//		}
//	}

	// 책 수정
//	public void updateBook(BookVO vo) {
//		System.out.println("===> JDBC 기반으로 updateBook() 기능 처리");
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(BOOK_UPDATE);
//			stmt.setString(1, vo.getTitle());
//			stmt.setString(2, vo.getContent());
//			stmt.setInt(3, vo.getSeq());
//			stmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(stmt, conn);
//		}
//	}

	// 책 삭제
	public void deleteBook(BookVO vo) {
		System.out.println("===> JDBC 기반으로 deleteBook() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOK_DELETE);
//			stmt.setInt(1, vo.getSeq());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 책 상세 조회
	public BookVO getBook(BookVO vo) {
		System.out.println("===> JDBC 기반으로 getBook() 기능 처리");
		BookVO book = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOK_GET);
			stmt.setString(1, vo.getBookNum());
			rs = stmt.executeQuery();
			if (rs.next()) {
				book = new BookVO();
				book.setBookNum(rs.getString("BOOKNUM"));
				book.setBookName(rs.getString("BOOKNAME"));
				book.setStockQty(rs.getInt("STOCKQTY"));
				book.setPrice(rs.getInt("PRICE"));
				book.setUrl(rs.getString("URL"));
			}
			JDBCUtil.close(rs, stmt, conn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);

		}
		return book;
	}

	// 책 목록 검색
	public List<BookVO> getBookList(BookVO vo) {
		System.out.println("===> JDBC 기반으로 getBookList() 기능 처리");
		List<BookVO> bookList = new ArrayList<BookVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOK_LIST);
			rs = stmt.executeQuery();
			while (rs.next()) {
				BookVO book = new BookVO();
				book.setBookNum(rs.getString("BOOKNUM"));
				book.setBookName(rs.getString("BOOKNAME"));
				book.setStockQty(rs.getInt("STOCKQTY"));
				book.setPrice(rs.getInt("PRICE"));
				book.setUrl(rs.getString("URL"));
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return bookList;
	}
	
	// 책 검색
	public List<BookVO> searchBookList(String searchKeyword) {
		System.out.println("===> JDBC 기반으로 searchBookList() 기능 처리");
		List<BookVO> bookList = new ArrayList<BookVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOK_SEARCH);
			stmt.setString(1, searchKeyword);
			rs = stmt.executeQuery();
			while (rs.next()) {
				BookVO book = new BookVO();
				book.setBookNum(rs.getString("BOOKNUM"));
				book.setBookName(rs.getString("BOOKNAME"));
				book.setStockQty(rs.getInt("STOCKQTY"));
				book.setPrice(rs.getInt("PRICE"));
				book.setUrl(rs.getString("URL"));
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return bookList;
	}
}
