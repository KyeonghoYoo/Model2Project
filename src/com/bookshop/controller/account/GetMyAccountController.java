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
		System.out.println("마이페이지 기능 처리");
		// 1. 사용자 입력정보 추출
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		// 2. DB 연동 처리

		// 3. 응답 화면 구성
		return "getMyAccount.jsp";

	}
}
