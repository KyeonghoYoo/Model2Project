package com.bookshop.controller.shoppingbasketlist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.customer.CustomerVO;
import com.bookshop.biz.shoppingbasketlist.ShoppingBasketListDAO;
import com.bookshop.biz.shoppingbasketlist.ShoppingBasketListVO;
import com.multicampus.biz.board.BoardDAO;
import com.multicampus.biz.board.BoardVO;
import com.multicampus.controller.Controller;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.InterningXmlVisitor;

public class InsertShoppingBasketListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("장바구니 등록 기능 처리");
		// 1. 사용자 입력 정보 추출
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		String customerId = customerVO.getCustomerId();
		String bookNum = request.getParameter("bookNum");
		int price = Integer.parseInt(request.getParameter("price"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		
		// 2. DB 연동 처리
		ShoppingBasketListVO vo = new ShoppingBasketListVO();
		vo.setCustomer_customerId(customerId);;
		vo.setBook_bookNum(bookNum);
		vo.setQty(qty);
		vo.setAmount(price * qty);

		ShoppingBasketListDAO shoppingBasketListDAO = new ShoppingBasketListDAO();
		shoppingBasketListDAO.insertShoppingBasketList(vo);

		// 3. 응답 화면 구성
		return "main.do";
	}

}
