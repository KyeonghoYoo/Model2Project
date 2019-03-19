package com.bookshop.controller.account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.book.BookDAO;
import com.bookshop.biz.book.BookVO;
import com.bookshop.biz.customer.CustomerVO;
import com.multicampus.controller.Controller;

public class GetMyAccountController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("���������� ��� ó��");
		// 1. ����� �Է����� ����(�˻� ����� ���߿�...)
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		// 2. DB ���� ó��

		// 3. ���� ȭ�� ����
		return "getMyAccount.jsp";

	}
}
