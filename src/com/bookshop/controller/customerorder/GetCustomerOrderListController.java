package com.bookshop.controller.customerorder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.customer.CustomerVO;
import com.bookshop.biz.customerorder.CustomerOrderDAO;
import com.bookshop.biz.customerorder.CustomerOrderVO;
import com.bookshop.biz.shoppingbasketlist.ShoppingBasketListDAO;
import com.bookshop.biz.shoppingbasketlist.ShoppingBasketListVO;
import com.multicampus.biz.board.BoardDAO;
import com.multicampus.biz.board.BoardVO;
import com.multicampus.controller.Controller;

public class GetCustomerOrderListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("장바구니 목록 검색 기능 처리");
		// 1. 사용자 입력정보 추출
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		// 2. DB 연동 처리
		CustomerOrderVO vo = new CustomerOrderVO();
		vo.setCustomer_CustomerId(customerId);
		CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();
		List<CustomerOrderVO> customerOrderList = customerOrderDAO.getCustomerOrderList(vo);

		// 3. 화면 네비게이션
		if(customerOrderList != null){
			session.setAttribute("customerOrderList", customerOrderList);
			return "getCustomerOrderList.jsp";
		} else {
			return "main.do";
		}
	}

}
