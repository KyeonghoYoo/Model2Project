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
		System.out.println("ī������ ��� �˻� ��� ó��");
		// 1. ����� �Է����� ����(�˻� ����� ���߿�...)
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		// 2. DB ���� ó��
		CreditCardInfoVO vo = new CreditCardInfoVO();
		vo.setCustomer_CustomerId(customerId);
		CreditCardInfoDAO creditCardInfoDAO = new CreditCardInfoDAO();
		List<CreditCardInfoVO> creditCardInfoList = creditCardInfoDAO.getCreditCardInfoList(vo);

		// 3. ���� ȭ�� ����
		session.setAttribute("creditCardInfoList", creditCardInfoList);
		return "getCreditCardInfoList.jsp";

	}
}
