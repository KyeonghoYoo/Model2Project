package com.bookshop.controller.creditcardinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.addressinfo.AddressInfoDAO;
import com.bookshop.biz.addressinfo.AddressInfoVO;
import com.bookshop.biz.creditcardinfo.CreditCardInfoDAO;
import com.bookshop.biz.creditcardinfo.CreditCardInfoVO;
import com.bookshop.biz.customer.CustomerDAO;
import com.bookshop.biz.customer.CustomerVO;
import com.multicampus.controller.Controller;

public class DeleteCreditCardInfoController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("카드정보 삭제 기능 처리");
		// 1. 사용자 입력정보 추출
		HttpSession session = request.getSession();
		
		String cardNum = request.getParameter("cardNum");
		
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		
		// 2. DB 연동 처리
		CreditCardInfoVO vo = new CreditCardInfoVO();
		vo.setCustomer_CustomerId(customerId);
		vo.setCardNum(cardNum);
		CreditCardInfoDAO creditCardInfoDAO = new CreditCardInfoDAO();
		boolean result = creditCardInfoDAO.deleteCreditCardInfo(vo);

		// 3. 화면 네비게이션
		if (result) {
			return "getCreditCardInfoList.do";
		} else {
			return "getCreditCardInfoList.do";
		}
	}
}
