package com.bookshop.controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.multicampus.controller.Controller;

public class LogoutController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("�α׾ƿ� ��� ó��");
		// 3. �α��� ���� ����
		HttpSession session = request.getSession();
		session.invalidate();
		return "main.do";

	}
}
