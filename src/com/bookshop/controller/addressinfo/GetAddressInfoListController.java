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
		System.out.println("��������� ��� �˻� ��� ó��");
		// 1. ����� �Է����� ����(�˻� ����� ���߿�...)
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		// 2. DB ���� ó��
		AddressInfoVO vo = new AddressInfoVO();
		vo.setCustomer_CustomerId(customerId);
		AddressInfoDAO addressInfoDAO = new AddressInfoDAO();
		List<AddressInfoVO> addressInfoList = addressInfoDAO.getAddressInfoList(vo);

		// 3. ���� ȭ�� ����
		session.setAttribute("addressInfoList", addressInfoList);
		return "getAddressInfoList.jsp";

	}
}
