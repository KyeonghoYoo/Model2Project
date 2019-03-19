package com.bookshop.controller.shoppingbasketlist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.customer.CustomerVO;
import com.bookshop.biz.shoppingbasketlist.ShoppingBasketListDAO;
import com.bookshop.biz.shoppingbasketlist.ShoppingBasketListVO;
import com.multicampus.controller.Controller;

public class DeleteShoppingBasketListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("장바구니 목록 검색 기능 처리");
		// 1. 사용자 입력정보 추출(검색 기능은 나중에...)
		HttpSession session = request.getSession();
		String bookNum = request.getParameter("bookNum");
		CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
		
		// 비로그인이면 로그인 화면으로 이동
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		// 2. DB 연동 처리
		ShoppingBasketListVO vo = new ShoppingBasketListVO();
		vo.setCustomer_customerId(customerId);
		vo.setBook_bookNum(bookNum);
		
		ShoppingBasketListDAO shoppingBasketListDAO = new ShoppingBasketListDAO();
		shoppingBasketListDAO.deleteShoppingBasketList(vo);

		// 3. 응답 화면 구성
		return "getShoppingBasketList.do";
	}

}
