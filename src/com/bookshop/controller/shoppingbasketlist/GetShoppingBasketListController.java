package com.bookshop.controller.shoppingbasketlist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.customer.CustomerVO;
import com.bookshop.biz.shoppingbasketlist.ShoppingBasketListDAO;
import com.bookshop.biz.shoppingbasketlist.ShoppingBasketListVO;
import com.multicampus.biz.board.BoardDAO;
import com.multicampus.biz.board.BoardVO;
import com.multicampus.controller.Controller;

public class GetShoppingBasketListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("장바구니 목록 검색 기능 처리");
		// 1. 사용자 입력정보 추출(검색 기능은 나중에...)
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		// 2. DB 연동 처리
		ShoppingBasketListVO vo = new ShoppingBasketListVO();
		ShoppingBasketListDAO shoppingBasketListDAO = new ShoppingBasketListDAO();
		List<ShoppingBasketListVO> shoppingBasketList = shoppingBasketListDAO.getShoppingBasketList(customerId);

		// 3. 응답 화면 구성
		if(shoppingBasketList != null){
			session.setAttribute("shoppingBasketList", shoppingBasketList);
			return "getShoppingBasketList.jsp";
		} else {
			return "main.do";
		}
	}

}
