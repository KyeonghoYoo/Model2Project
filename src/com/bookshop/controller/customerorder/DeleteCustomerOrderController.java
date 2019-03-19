package com.bookshop.controller.customerorder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.customer.CustomerVO;
import com.bookshop.biz.customerorder.CustomerOrderDAO;
import com.bookshop.biz.customerorder.CustomerOrderVO;
import com.multicampus.controller.Controller;

public class DeleteCustomerOrderController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("배송지삭제 기능 처리");
		// 1. 사용자 입력정보 추출
		HttpSession session = request.getSession();
		
		String orderNum = request.getParameter("orderNum");
		
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		
		// 2. DB 연동 처리
		CustomerOrderVO vo = new CustomerOrderVO();
		vo.setOrderNum(orderNum);
		CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();
		boolean result = customerOrderDAO.deleteCustomerOrder(vo);

		// 3. 화면 네비게이션
		if (result) {
			return "getCustomerOrderList.do";
		} else {
			return "getCustomerOrderList.do";
		}
	}
}
