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
		System.out.println("å �˻� ��� ó��");
		// 1. ����� �Է����� ����(�˻� ����� ���߿�...)
		String seachKeyword = (String) request.getParameter("searchKeyword");
		// 2. DB ���� ó��
		BookDAO bookDAO = new BookDAO();
		List<BookVO> bookList = bookDAO.searchBookList(seachKeyword);

		// 3. ���� ȭ�� ����
		HttpSession session = request.getSession();
		session.removeAttribute("bookList");
		session.setAttribute("bookList", bookList);
		return "main.jsp";

	}
}
