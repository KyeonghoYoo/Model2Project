package com.bookshop.controller.customerorder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.addressinfo.AddressInfoDAO;
import com.bookshop.biz.addressinfo.AddressInfoVO;
import com.bookshop.biz.book.BookVO;
import com.bookshop.biz.creditcardinfo.CreditCardInfoDAO;
import com.bookshop.biz.creditcardinfo.CreditCardInfoVO;
import com.bookshop.biz.customer.CustomerVO;
import com.bookshop.biz.shoppingbasketlist.ShoppingBasketListVO;
import com.multicampus.controller.Controller;

public class PrepareInsertCustomerOrderController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("주문화면 준비 기능 처리");
		// 1. 사용자 입력정보 추출
		HttpSession session = request.getSession();
		session.removeAttribute("addressInfoForOrder");
		session.removeAttribute("creditCardInfoForOrder");
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		String from = request.getParameter("from");
		session.setAttribute("from", from);
		
		List<ShoppingBasketListVO> shoppingBasketList = new ArrayList<ShoppingBasketListVO>();
		
		if(from.equals("book")){
			session.removeAttribute("shoppingBasketList");
			String bookNum = request.getParameter("bookNum");
			String bookName = request.getParameter("bookName");
			String stockQty = request.getParameter("stockQty");
			String sPrice = request.getParameter("price");
			int price = Integer.parseInt(sPrice);
			String sQty = request.getParameter("qty");
			int qty = Integer.parseInt(sQty);
			
			ShoppingBasketListVO vo = new ShoppingBasketListVO();
			vo.setBook_bookNum(bookNum);
			vo.setBook_bookName(bookName);
			vo.setQty(qty);
			vo.setAmount(qty * price);
			shoppingBasketList.add(vo);
			
		} else if(from.equals("shoppingBasketList")) {
			shoppingBasketList = (List) session.getAttribute("shoppingBasketList");
		}

		
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		// 2. DB 연동 처리
		//배송지 정보 목록 조회
		AddressInfoVO addressInfoVO = new AddressInfoVO();
		addressInfoVO.setCustomer_CustomerId(customerId);
		AddressInfoDAO addressInfoDAO = new AddressInfoDAO();
		List<AddressInfoVO> addressInfoList = addressInfoDAO.getAddressInfoList(addressInfoVO);
		//카드 정보 목록 조회
		CreditCardInfoVO creditCardInfoVO = new CreditCardInfoVO();
		creditCardInfoVO.setCustomer_CustomerId(customerId);
		CreditCardInfoDAO creditCardInfoDAO = new CreditCardInfoDAO();
		List<CreditCardInfoVO> creditCardInfoList = creditCardInfoDAO.getCreditCardInfoList(creditCardInfoVO);
		
		// 3. 응답 화면 구성
		session.setAttribute("shoppingBasketList", shoppingBasketList);
		session.setAttribute("addressInfoList", addressInfoList);
		session.setAttribute("creditCardInfoList", creditCardInfoList);
		return "insertOrder.jsp";

	}
}
