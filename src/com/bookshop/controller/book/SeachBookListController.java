package com.bookshop.controller.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.book.BookDAO;
import com.bookshop.biz.book.BookVO;
import com.multicampus.controller.Controller;

public class SeachBookListController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("책 검색 기능 처리");
		// 1. 사용자 입력정보 추출(검색 기능은 나중에...)
		String seachKeyword = (String) request.getParameter("searchKeyword");
		// 2. DB 연동 처리
		BookDAO bookDAO = new BookDAO();
		List<BookVO> bookList = bookDAO.searchBookList(seachKeyword);

		// 3. 응답 화면 구성
		HttpSession session = request.getSession();
		session.removeAttribute("bookList");
		session.setAttribute("bookList", bookList);
		return "main.jsp";

	}
}
