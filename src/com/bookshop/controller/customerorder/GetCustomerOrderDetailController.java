package com.bookshop.controller.customerorder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.addressinfo.AddressInfoDAO;
import com.bookshop.biz.addressinfo.AddressInfoVO;
import com.bookshop.biz.creditcardinfo.CreditCardInfoDAO;
import com.bookshop.biz.creditcardinfo.CreditCardInfoVO;
import com.bookshop.biz.customer.CustomerVO;
import com.bookshop.biz.customerorder.CustomerOrderDAO;
import com.bookshop.biz.customerorder.CustomerOrderVO;
import com.bookshop.biz.customerorderlist.CustomerOrderListDAO;
import com.bookshop.biz.customerorderlist.CustomerOrderListVO;
import com.bookshop.biz.shoppingbasketlist.ShoppingBasketListDAO;
import com.bookshop.biz.shoppingbasketlist.ShoppingBasketListVO;
import com.multicampus.biz.board.BoardDAO;
import com.multicampus.biz.board.BoardVO;
import com.multicampus.controller.Controller;

public class GetCustomerOrderDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("�ֹ� ��� �� �˻� ��� ó��");
		// 1. ����� �Է����� ����(�˻� ����� ���߿�...)
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		String orderNum = request.getParameter("orderNum");
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		// 2. DB ���� ó��
		CustomerOrderListVO vo = new CustomerOrderListVO();
		vo.setCustomerOrder_OrderNum(orderNum);
		CustomerOrderListDAO customerOrderListDAO = new CustomerOrderListDAO();
		List<CustomerOrderListVO> customerOrderListList = customerOrderListDAO.getCustomerOrderListList(vo);
		
		CustomerOrderVO customerOrderVO = new CustomerOrderVO();
		customerOrderVO.setOrderNum(orderNum);
		CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();
		CustomerOrderVO customerOrder = customerOrderDAO.getCustomerOrder(customerOrderVO);
		// 3. ���� ȭ�� ����
		if(customerOrderListList != null){
			session.setAttribute("customerOrder", customerOrder);
			session.setAttribute("customerOrderListList", customerOrderListList);
			return "getCustomerOrderDetail.jsp";
		} else {
			return "main.do";
		}
	}

}
