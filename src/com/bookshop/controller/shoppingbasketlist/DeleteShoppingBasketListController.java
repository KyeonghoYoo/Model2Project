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
		System.out.println("��ٱ��� ��� �˻� ��� ó��");
		// 1. ����� �Է����� ����(�˻� ����� ���߿�...)
		HttpSession session = request.getSession();
		String bookNum = request.getParameter("bookNum");
		CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
		
		// ��α����̸� �α��� ȭ������ �̵�
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		// 2. DB ���� ó��
		ShoppingBasketListVO vo = new ShoppingBasketListVO();
		vo.setCustomer_customerId(customerId);
		vo.setBook_bookNum(bookNum);
		
		ShoppingBasketListDAO shoppingBasketListDAO = new ShoppingBasketListDAO();
		shoppingBasketListDAO.deleteShoppingBasketList(vo);

		// 3. ���� ȭ�� ����
		return "getShoppingBasketList.do";
	}

}
