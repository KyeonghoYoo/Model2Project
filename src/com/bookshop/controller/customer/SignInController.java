package com.bookshop.controller.customer;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.customer.CustomerDAO;
import com.bookshop.biz.customer.CustomerVO;
import com.multicampus.biz.user.UserDAO;
import com.multicampus.biz.user.UserVO;
import com.multicampus.controller.Controller;

public class SignInController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그인 기능 처리");
		// 1. 사용자 입력정보 추출
		String id = request.getParameter("customerId");
		String password = request.getParameter("password");
		
		// 2. DB 연동 처리
		CustomerVO vo = new CustomerVO();
		vo.setCustomerId(id);
		vo.setPassword(password);
		CustomerDAO customerDAO = new CustomerDAO();
		CustomerVO customer = customerDAO.getCustomer(vo);
		
		// 3. 로그인 정보 전달
		HttpSession session = request.getSession();
		session.setAttribute("customer", customer);
		
		// 4. 화면 네비게이션
		if (customer != null) {
			return "main.do";
		} else {
			return "signIn.jsp";
		}
	}
}
