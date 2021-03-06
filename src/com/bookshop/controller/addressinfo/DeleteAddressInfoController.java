package com.bookshop.controller.addressinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.addressinfo.AddressInfoDAO;
import com.bookshop.biz.addressinfo.AddressInfoVO;
import com.bookshop.biz.customer.CustomerDAO;
import com.bookshop.biz.customer.CustomerVO;
import com.multicampus.controller.Controller;

public class DeleteAddressInfoController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("배송지삭제 기능 처리");
		// 1. 사용자 입력정보 추출
		HttpSession session = request.getSession();
		
		String zipCode = request.getParameter("zipCode");
		
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		
		// 2. DB 연동 처리
		AddressInfoVO vo = new AddressInfoVO();
		vo.setCustomer_CustomerId(customerId);
		vo.setZipCode(zipCode);
		AddressInfoDAO addressInfoDAO = new AddressInfoDAO();
		boolean result = addressInfoDAO.deleteAddressInfo(vo);

		// 3. 화면 네비게이션
		if (result) {
			return "getAddressInfoList.do";
		} else {
			return "getAddressInfoList.do";
		}
	}
}
