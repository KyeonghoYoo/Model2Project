package com.bookshop.controller.customerorder;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.customer.CustomerVO;
import com.bookshop.biz.customerorder.CustomerOrderDAO;
import com.bookshop.biz.customerorder.CustomerOrderVO;
import com.bookshop.biz.customerorderlist.CustomerOrderListDAO;
import com.bookshop.biz.customerorderlist.CustomerOrderListVO;
import com.bookshop.biz.shoppingbasketlist.ShoppingBasketListDAO;
import com.bookshop.biz.shoppingbasketlist.ShoppingBasketListVO;
import com.multicampus.controller.Controller;

public class InsertCustomerOrderController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("주문등록 기능 처리");
		// 1. 사용자 입력정보 추출
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		List<ShoppingBasketListVO> shoppingBasketList = (List) session.getAttribute("shoppingBasketList");
		String from = (String)session.getAttribute("from");
		
		String paymentAmount = request.getParameter("paymentAmount");
		String zipCode = request.getParameter("zipCode");
		String baseAddress = request.getParameter("baseAddress");
		String detailAddress = request.getParameter("detailAddress");
		String cardNum = request.getParameter("cardNum");
		String expirationDate = request.getParameter("expirationDate");
		String cardType = request.getParameter("cardType");
		
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		CustomerOrderVO orderVO = new CustomerOrderVO();
		orderVO.setCustomer_CustomerId(customerId);
		orderVO.setPaymentAmount(Integer.parseInt(paymentAmount));
		orderVO.setZipCode(zipCode);
		orderVO.setBaseAddress(baseAddress);
		orderVO.setDetailAddress(detailAddress);
		orderVO.setCardNum(cardNum);
		orderVO.setExpirationDate(expirationDate);
		orderVO.setCardType(cardType);
		
		List<CustomerOrderListVO> customerOrderListVOs = new ArrayList<CustomerOrderListVO>(); 
		for (ShoppingBasketListVO item : shoppingBasketList) {
			CustomerOrderListVO customerOrderListVO = new CustomerOrderListVO();
			customerOrderListVO.setBook_BookNum(item.getBook_bookNum());
			customerOrderListVO.setQty(item.getQty());
			customerOrderListVO.setAmount(item.getAmount());
			customerOrderListVOs.add(customerOrderListVO);
		}
		
		// 2. DB 연동 처리
		CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();
		customerOrderDAO.insertCustomerOrder(orderVO);
		String lastestOrderNum = customerOrderDAO.getLastestOrderNum(orderVO).getOrderNum();
		
		CustomerOrderListDAO customerOrderListDAO = new CustomerOrderListDAO();
		for(int i = 0; i < customerOrderListVOs.size(); i++){
			customerOrderListVOs.get(i).setCustomerOrder_OrderNum(lastestOrderNum);
			customerOrderListDAO.insertCustomerOrderList(customerOrderListVOs.get(i));
		}
		// 3. 화면 네비게이션
		if(from.equals("book")){
		
		} else if(from.equals("shoppingBasketList")){
			for (ShoppingBasketListVO item : shoppingBasketList) {
				ShoppingBasketListVO vo = new ShoppingBasketListVO();
				vo.setCustomer_customerId(customerId);
				vo.setBook_bookNum(item.getBook_bookNum());
				ShoppingBasketListDAO shoppingBasketListDAO = new ShoppingBasketListDAO();
				shoppingBasketListDAO.deleteShoppingBasketList(vo);
			}
		}
		return "main.do";

	}
}
