<%@page import="com.bookshop.biz.customerorder.CustomerOrderVO"%>
<%@page import="com.bookshop.biz.addressinfo.AddressInfoVO"%>
<%@page import="com.bookshop.biz.creditcardinfo.CreditCardInfoVO"%>
<%@page import="com.bookshop.biz.customerorderlist.CustomerOrderListVO"%>
<%@page import="com.bookshop.biz.shoppingbasketlist.ShoppingBasketListVO"%>
<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="utf-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.bookshop.biz.customer.CustomerVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	// 세션에 저장된 글 목록을 꺼낸다.
	List<CustomerOrderListVO> customerOrderListList = (List) session.getAttribute("customerOrderListList");
	CustomerOrderVO customerOrder = (CustomerOrderVO) session.getAttribute("customerOrder");
	//세션에 저장된 유저 정보를 꺼낸다
	CustomerVO customer = (CustomerVO) session.getAttribute("customer");
	int paymentAmount = 0;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>DN CLUB</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/main.css" />
<script src="https://code.jquery.com/jquery.js"></script>

<script type="text/javascript">
</script>

<link rel="stylesheet" type="text/css" href="resources/css/Style.css" />
</head>
<body>
	<!-- 전체를 감싸는 태그 -->
	<div id="page-wrapper">
		<!-- 헤더 -->
		<header id="main-header">
			<hgroup>
				<a href="main.do">
					<h1 class="master-title">DN CLUB</h1>
					<h2 class="master-description">Book Shop</h2>
				</a>
			</hgroup>
		</header>
		<nav id="main-navigation">
			<div class="pull-left">
				<ul class="outer-menu">
					<li class="outer-menu-item"><a href="main.do"><span
							class="menu-title">도서</span></a> <!-- <ul class="inner-menu">
							<li class="inner-menu-item"><a href="#">티셔츠</a></li>
							<li class="inner-menu-item"><a href="#">후디</a></li>
							<li class="inner-menu-item"><a href="#">스웻</a></li>
						</ul> --></li>
				</ul>
			</div>
			<div class="pull-right">
				<div class="search-bar">
					<form action="searchBookList.do" method="POST">
						<input name="searchKeyword" type="text" class="input-search" />
						<input type="submit" class="input-search-submit" value="검색" />
					</form>
				</div>
				<div id="main-header-bar">
					<div id="main-header-bar-module">
						<ul>
							<%
								if (customer != null) {
							%>
							<li><a href="getMyAccount.do"><%=customer.getName() %>님</a></li>
							<li><a href="logout.do">Log out</a></li>
							
							<%
								} else {
							%>
							<li><a href="signIn.jsp">Sign in</a></li>
							<li>or</li>
							<li><a href="signUp.jsp">Sign up</a></li>
							<%
								}
							%>
						</ul>
						<div id="main-header-bar-cart">
							<img src="resources/img/cart.svg"> <a href="getShoppingBasketList.do">Cart</a>
						</div>
					</div>
				</div>
			</div>
		</nav>
		<!-- 아이템 테이블 -->
		<div id="content">
			<section id="main-section">
				<h1>주문 상세보기</h1>
				<form name="orderForm" method="post" action="deleteOrder.do">
					<input type="hidden" value="<%=customerOrder.getOrderNum() %>" name="orderNum"/>
					<table border="1" cellpadding="0" cellspacing="0" width="700" align="center">
						<tr>
							<th bgcolor="#CCCCCC" width="30">도서번호</th>
							<th bgcolor="#CCCCCC" width="200">도서명</th>
							<th bgcolor="#CCCCCC" width="50">수량</th>
							<th bgcolor="#CCCCCC" width="60">결제금액</th>
						</tr>
						<%
							for (CustomerOrderListVO customerOrderList : customerOrderListList) {
						%>
						<tr align="center">
							<td><%=customerOrderList.getBook_BookNum()%></td>
							<td align="left"><a href="getBook.do?bookNum=<%=customerOrderList.getBook_BookNum() %>"><%=customerOrderList.getBook_BookName()%></a></td>
							<td><%=customerOrderList.getQty() %></td>
							<td><%=customerOrderList.getAmount()%></td>	
						</tr>
						<%
							paymentAmount += customerOrderList.getAmount();
							}
						%>
					<table border="1" cellpadding="0" cellspacing="0" width="200" align="center">
						<tr>
							<th bgcolor="#CCCCCC" width="60">총결제금액</th>
						</tr>
						<tr>
							<td align="center"><%=paymentAmount %></td>
						</tr>
					</table>
					</table>
					<table border="1" cellpadding="0" cellspacing="0">
						<tr>
							<td bgcolor="#CCCCCC">우편번호</td>
							<td><%=customerOrder.getZipCode() %></td>
						</tr>
						<tr>
							<td bgcolor="#CCCCCC">기본주소</td>
							<td><%=customerOrder.getBaseAddress() %></td>
						</tr>
						<tr>
							<td bgcolor="#CCCCCC">상세주소</td>
							<td><%=customerOrder.getDetailAddress() %></td>
						</tr>
					</table>
					<table border="1" cellpadding="0" cellspacing="0">
						<tr>
							<td bgcolor="#CCCCCC">카드번호</td>
							<td><%=customerOrder.getCardNum() %></td>
						</tr>
						<tr>
							<td bgcolor="#CCCCCC">유효기간</td>
							<td><%=customerOrder.getExpirationDate() %></td>
						</tr>
						<tr>
							<td bgcolor="#CCCCCC">카드종류</td>
							<td><%=customerOrder.getCardType() %></td>
						</tr>
					</table>
					<input type="submit" value="주문 삭제">
				</form>
			</section>
		</div>
		<footer id="main-footer">
			<div id="main-footer-payimgs">
				<img
					src="resources/img/american_express-ed5c54cf3ceb18cd4deb3687857b816c07e4f4c7e8719da4a206cea3e7961be1.svg"
					alt=""> <img
					src="resources/img/apple_pay-6e5e439db180c20fcddae8e5c353a8c426d64772d590c9bd7f1e97b51728c155.svg"
					alt=""> <img
					src="resources/img/diners_club-16436b9fb6dd9060edb51f1c7c44e23941e544ad798282d6aef1604319562fba.svg"
					alt=""> <img
					src="resources/img/discover-6a5629e025177299a47e8947dc20617d1be3652b0702cc2c31a0d26f0218e782.svg"
					alt=""> <img
					src="resources/img/jcb-39bd079ac1eb8b4eedd7de6ad2e7f78fa187a571362449bf19afa9e7bad7ac1b.svg"
					alt=""> <img
					src="resources/img/master-173035bc8124581983d4efa50cf8626e8553c2b311353fbf67485f9c1a2b88d1.svg"
					alt=""> <img
					src="resources/img/shopify_pay-78300f25adfeb505650e3ecb78b3022f6f64bc84a456ca40e186a347624a4520.svg"
					alt=""> <img
					src="resources/img/visa-319d545c6fd255c9aad5eeaad21fd6f7f7b4fdbdb1a35ce83b89cca12a187f00.svg"
					alt="">
			</div>
			<div id="main-footer-module">
				<a href="#">Copyright © 2018, DN CLUB. Powered by YKH</a>
			</div>
		</footer>
	</div>
</body>
</html>