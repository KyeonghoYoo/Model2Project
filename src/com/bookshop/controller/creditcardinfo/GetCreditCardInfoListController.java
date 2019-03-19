package com.bookshop.controller.creditcardinfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.addressinfo.AddressInfoDAO;
import com.bookshop.biz.addressinfo.AddressInfoVO;
import com.bookshop.biz.creditcardinfo.CreditCardInfoDAO;
import com.bookshop.biz.creditcardinfo.CreditCardInfoVO;
import com.bookshop.biz.customer.CustomerVO;
import com.multicampus.controller.Controller;

public class GetCreditCardInfoListController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("카드정보 목록 검색 기능 처리");
		// 1. 사용자 입력정보 추출(검색 기능은 나중에...)
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		// 2. DB 연동 처리
		CreditCardInfoVO vo = new CreditCardInfoVO();
		vo.setCustomer_CustomerId(customerId);
		CreditCardInfoDAO creditCardInfoDAO = new CreditCardInfoDAO();
		List<CreditCardInfoVO> creditCardInfoList = creditCardInfoDAO.getCreditCardInfoList(vo);

		// 3. 응답 화면 구성
		session.setAttribute("creditCardInfoList", creditCardInfoList);
		return "getCreditCardInfoList.jsp";

	}
}
