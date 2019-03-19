package com.multicampus.controller;

import java.util.HashMap;
import java.util.Map;

import com.bookshop.controller.account.GetMyAccountController;
import com.bookshop.controller.addressinfo.DeleteAddressInfoController;
import com.bookshop.controller.addressinfo.GetAddressInfoController;
import com.bookshop.controller.addressinfo.GetAddressInfoForOrderController;
import com.bookshop.controller.addressinfo.GetAddressInfoListController;
import com.bookshop.controller.addressinfo.InsertAddressInfoController;
import com.bookshop.controller.addressinfo.UpdateAddressInfoController;
import com.bookshop.controller.book.GetBookController;
import com.bookshop.controller.book.GetBookListController;
import com.bookshop.controller.book.SeachBookListController;
import com.bookshop.controller.creditcardinfo.DeleteCreditCardInfoController;
import com.bookshop.controller.creditcardinfo.GetCreditCardInfoController;
import com.bookshop.controller.creditcardinfo.GetCreditCardInfoForOrderController;
import com.bookshop.controller.creditcardinfo.GetCreditCardInfoListController;
import com.bookshop.controller.creditcardinfo.InsertCreditCardInfoController;
import com.bookshop.controller.creditcardinfo.UpdateCreditCardInfoController;
import com.bookshop.controller.customer.LogoutController;
import com.bookshop.controller.customer.SignInController;
import com.bookshop.controller.customer.SignUpContorller;
import com.bookshop.controller.customerorder.DeleteCustomerOrderController;
import com.bookshop.controller.customerorder.GetCustomerOrderDetailController;
import com.bookshop.controller.customerorder.GetCustomerOrderListController;
import com.bookshop.controller.customerorder.InsertCustomerOrderController;
import com.bookshop.controller.customerorder.PrepareInsertCustomerOrderController;
import com.bookshop.controller.shoppingbasketlist.DeleteShoppingBasketListController;
import com.bookshop.controller.shoppingbasketlist.GetShoppingBasketListController;
import com.bookshop.controller.shoppingbasketlist.InsertShoppingBasketListController;
import com.multicampus.controller.board.DeleteBoardController;
import com.multicampus.controller.board.GetBoardController;
import com.multicampus.controller.board.GetBoardListController;
import com.multicampus.controller.board.InsertBoardController;
import com.multicampus.controller.board.UpdateBoardController;

public class HandlerMapping {
	private Map<String, Controller> mappings;
	
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();

		mappings.put("/insertBoard.do",  new InsertBoardController());
		mappings.put("/updateBoard.do",  new UpdateBoardController());
		mappings.put("/deleteBoard.do",  new DeleteBoardController());
		mappings.put("/getBoard.do",     new GetBoardController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		
		//±¸ÇöµÊ
		mappings.put("/main.do", 		 				new GetBookListController());
		mappings.put("/getBook.do", 	 				new GetBookController());
		mappings.put("/searchBookList.do", 				new SeachBookListController());
		mappings.put("/signIn.do",       				new SignInController());
		mappings.put("/logout.do",       				new LogoutController());
		mappings.put("/signUp.do",		 				new SignUpContorller());
		mappings.put("/insertShoppingBasketList.do",	new InsertShoppingBasketListController());
		mappings.put("/getShoppingBasketList.do", 		new GetShoppingBasketListController());
		mappings.put("/deteteShoppingBasket.do", 		new DeleteShoppingBasketListController());
		mappings.put("/getMyAccount.do",    			new GetMyAccountController());
		mappings.put("/getAddressInfoList.do", 			new GetAddressInfoListController());
		mappings.put("/insertAddressInfo.do", 			new InsertAddressInfoController());
		mappings.put("/deteteAddressInfo.do", 			new DeleteAddressInfoController());
		mappings.put("/getAddressInfo.do", 				new GetAddressInfoController());
		mappings.put("/getAddressInfoForOrder.do", 		new GetAddressInfoForOrderController());
		mappings.put("/updateAddressInfo.do", 			new UpdateAddressInfoController());
		mappings.put("/insertCreditCardInfo.do", 		new InsertCreditCardInfoController());
		mappings.put("/getCreditCardInfoList.do", 		new GetCreditCardInfoListController());
		mappings.put("/getCreditCardInfo.do", 			new GetCreditCardInfoController());
		mappings.put("/getCreditCardInfoForOrder.do", 	new GetCreditCardInfoForOrderController());
		mappings.put("/updateCreditCardInfo.do", 		new UpdateCreditCardInfoController());
		mappings.put("/deleteCreditCardInfo.do", 		new DeleteCreditCardInfoController());		
		mappings.put("/prepareOrder.do", 				new PrepareInsertCustomerOrderController());		
		mappings.put("/insertOrder.do", 				new InsertCustomerOrderController());		
		mappings.put("/getCustomerOrderList.do", 		new GetCustomerOrderListController());	
		mappings.put("/getCustomerOrderDetail.do",  	new GetCustomerOrderDetailController());
		mappings.put("/deleteOrder.do",				  	new DeleteCustomerOrderController());
		//±¸Çö ¾È µÊ 
		// ÁÖ¹®

		/*
		 mappings.put("/bookSearch.do", new BookSearchController());
		 */
		
		
	}

	public Controller getController(String path) {
		return mappings.get(path);
	}

}
