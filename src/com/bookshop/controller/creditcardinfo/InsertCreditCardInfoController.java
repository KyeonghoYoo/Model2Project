package com.bookshop.controller.creditcardinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.addressinfo.AddressInfoDAO;
import com.bookshop.biz.creditcardinfo.CreditCardInfoDAO;
import com.bookshop.biz.creditcardinfo.CreditCardInfoVO;
import com.bookshop.biz.customer.CustomerVO;
import com.multicampus.controller.Controller;

public class InsertCreditCardInfoController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ī������ ��� ��� ó��");
		// 1. ����� �Է����� ����
		HttpSession session = request.getSession();
		String cardNum = "";
		for(int i = 1; i < 4; i++){
			cardNum += request.getParameter("num" + i) + "-";
		}
		cardNum += request.getParameter("num4");
		String expirationDate = request.getParameter("month") + "/" + request.getParameter("year");
		String cardType = request.getParameter("cardType");
		
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		
		// 2. DB ���� ó��
		CreditCardInfoVO vo = new CreditCardInfoVO();
		vo.setCustomer_CustomerId(customerId);
		vo.setCardNum(cardNum);
		vo.setExpirationDate(expirationDate);
		vo.setCardType(cardType);
		CreditCardInfoDAO addressInfoDAO = new CreditCardInfoDAO();
		boolean result = addressInfoDAO.insertCreditCardInfoInfo(vo);

		// 3. ȭ�� �׺���̼�
		if (result) {
			return "getCreditCardInfoList.do";
		} else {
			return "insertCreditCardInfo.jsp";
		}
	}
}
