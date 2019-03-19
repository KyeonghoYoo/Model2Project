package com.bookshop.controller.creditcardinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.creditcardinfo.CreditCardInfoDAO;
import com.bookshop.biz.creditcardinfo.CreditCardInfoVO;
import com.bookshop.biz.customer.CustomerVO;
import com.multicampus.controller.Controller;

public class GetCreditCardInfoController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("카드정보 정보 상세 보기 기능 처리");
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
		vo.setCardNum(cardNum);;
		CreditCardInfoDAO creditCardInfoDAO = new CreditCardInfoDAO();
		CreditCardInfoVO creditCardInfo = creditCardInfoDAO.getCreditCardInfo(vo);

		// 3. 응답 화면 구성
		session.setAttribute("creditCardInfo", creditCardInfo);
		return "getCreditCardInfo.jsp";

	}
}
