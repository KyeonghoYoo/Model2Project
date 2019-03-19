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
		System.out.println("��ٱ��� ��� ��� ó��");
		// 1. ����� �Է����� ����
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		String customerId = customerVO.getCustomerId();
		String bookNum = request.getParameter("bookNum");
		int price = Integer.parseInt(request.getParameter("price"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		
		// 2. DB ���� ó��
		ShoppingBasketListVO vo = new ShoppingBasketListVO();
		vo.setCustomer_customerId(customerId);;
		vo.setBook_bookNum(bookNum);
		vo.setQty(qty);
		vo.setAmount(price * qty);

		ShoppingBasketListDAO shoppingBasketListDAO = new ShoppingBasketListDAO();
		shoppingBasketListDAO.insertShoppingBasketList(vo);

		// 3. ȭ�� �׺���̼�
		return "main.do";
	}

}
