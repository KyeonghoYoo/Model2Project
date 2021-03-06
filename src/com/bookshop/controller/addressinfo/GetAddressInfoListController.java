package com.bookshop.controller.addressinfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.addressinfo.AddressInfoDAO;
import com.bookshop.biz.addressinfo.AddressInfoVO;
import com.bookshop.biz.book.BookDAO;
import com.bookshop.biz.book.BookVO;
import com.bookshop.biz.customer.CustomerVO;
import com.multicampus.controller.Controller;

public class GetAddressInfoListController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("배송지정보 목록 검색 기능 처리");
		// 1. 사용자 입력정보 추출
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		// 2. DB 연동 처리
		AddressInfoVO vo = new AddressInfoVO();
		vo.setCustomer_CustomerId(customerId);
		AddressInfoDAO addressInfoDAO = new AddressInfoDAO();
		List<AddressInfoVO> addressInfoList = addressInfoDAO.getAddressInfoList(vo);

		// 3. 응답 화면 구성
		session.setAttribute("addressInfoList", addressInfoList);
		return "getAddressInfoList.jsp";

	}
}
