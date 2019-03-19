package com.bookshop.controller.addressinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.biz.addressinfo.AddressInfoDAO;
import com.bookshop.biz.addressinfo.AddressInfoVO;
import com.bookshop.biz.customer.CustomerDAO;
import com.bookshop.biz.customer.CustomerVO;
import com.multicampus.controller.Controller;

public class UpdateAddressInfoController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("��������� ��� ó��");
		// 1. ����� �Է����� ����
		HttpSession session = request.getSession();
		
		String zipCode = request.getParameter("zipCode");
		String newZipCode = request.getParameter("newZipCode");
		String baseAddress = request.getParameter("baseAddress");
		String detailAddress = request.getParameter("detailAddress");
		
		CustomerVO customerVO = (CustomerVO)session.getAttribute("customer");
		if(customerVO == null){
			return "signIn.jsp";
		}
		
		String customerId = customerVO.getCustomerId();
		
		
		// 2. DB ���� ó��
		AddressInfoVO vo = new AddressInfoVO();
		vo.setCustomer_CustomerId(customerId);
		vo.setZipCode(newZipCode);
		vo.setBaseAddress(baseAddress);
		vo.setDetailAddress(detailAddress);
		AddressInfoDAO addressInfoDAO = new AddressInfoDAO();
		boolean result = addressInfoDAO.updateAddressInfo(vo, zipCode);
		// 3. ȭ�� �׺���̼�
		if (result) {
			return "getAddressInfoList.do";
		} else {
			return "getAddressInfoList.do";
		}
	}
}
