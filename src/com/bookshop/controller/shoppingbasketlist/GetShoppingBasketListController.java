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
		System.out.println("��ٱ��� ��� �˻� ��� ó��");
		// 1. ����� �Է����� ����(�˻� ����� ���߿�...)
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		// 2. DB ���� ó��
		ShoppingBasketListVO vo = new ShoppingBasketListVO();
		ShoppingBasketListDAO shoppingBasketListDAO = new ShoppingBasketListDAO();
		List<ShoppingBasketListVO> shoppingBasketList = shoppingBasketListDAO.getShoppingBasketList(customerId);

		// 3. ���� ȭ�� ����
		if(shoppingBasketList != null){
			session.setAttribute("shoppingBasketList", shoppingBasketList);
			return "getShoppingBasketList.jsp";
		} else {
			return "main.do";
		}
	}

}
