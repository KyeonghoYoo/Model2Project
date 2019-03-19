package com.bookshop.controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.multicampus.controller.Controller;

public class LogoutController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그아웃 기능 처리");
		// 로그인 정보 전달
		HttpSession session = request.getSession();
		session.invalidate();
		return "main.do";

	}
}
