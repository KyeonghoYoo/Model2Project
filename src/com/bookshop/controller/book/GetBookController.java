package com.bookshop.controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.book.BookDAO;
import com.bookshop.biz.book.BookVO;
import com.multicampus.controller.Controller;

public class GetBookController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("�� �� ��ȸ ��� ó��");
		String bookNum = request.getParameter("bookNum");

		// 2. DB ���� ó��
		BookVO vo = new BookVO();
		vo.setBookNum(bookNum);
		BookDAO bookDAO = new BookDAO();;
		BookVO book = bookDAO.getBook(vo);
		
		// 3. ���� ȭ�� ����
		HttpSession session = request.getSession();
		session.setAttribute("book", book);
		return "getBook.jsp";
	}
}
