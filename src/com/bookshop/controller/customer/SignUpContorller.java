package com.bookshop.controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookshop.biz.customer.CustomerDAO;
import com.bookshop.biz.customer.CustomerVO;
import com.multicampus.biz.user.UserDAO;
import com.multicampus.biz.user.UserVO;
import com.multicampus.controller.Controller;

public class SignUpContorller implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("회원가입 기능 처리");
		// 1. 사용자 입력정보 추출
		String customerId = request.getParameter("customerId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		// 2. DB 연동 처리
		CustomerVO vo = new CustomerVO();
		vo.setCustomerId(customerId);
		vo.setPassword(password);
		vo.setName(name);

		CustomerDAO customerDAO = new CustomerDAO();
		boolean result = customerDAO.insertCustomer(vo);

		// 3. 화면 네비게이션
		if (result) {
			return "signIn.jsp";
		} else {
			return "signUp.jsp";
		}
	}
}
