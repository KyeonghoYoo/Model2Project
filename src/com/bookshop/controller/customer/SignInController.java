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
		System.out.println("�α��� ��� ó��");
		// 1. ����� �Է����� ����
		String id = request.getParameter("customerId");
		String password = request.getParameter("password");
		
		// 2. DB ���� ó��
		CustomerVO vo = new CustomerVO();
		vo.setCustomerId(id);
		vo.setPassword(password);
		CustomerDAO customerDAO = new CustomerDAO();
		CustomerVO customer = customerDAO.getCustomer(vo);
		
		// 3. �α��� ���� ����
		HttpSession session = request.getSession();
		session.setAttribute("customer", customer);
		
		// 4. ȭ�� �׺���̼�
		if (customer != null) {
			return "main.do";
		} else {
			return "signIn.jsp";
		}
	}
}
