<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="utf-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.bookshop.biz.book.BookVO"%>
<%@ page import="com.bookshop.biz.customer.CustomerVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	// 세션에 저장된 글 목록을 꺼낸다.
	//List<BookVO> bookList = (List) session.getAttribute("bookList");
	//세션에 저장된 유저 정보를 꺼낸다
	CustomerVO customer = (CustomerVO) session.getAttribute("customer");
%>

<!DOCTYPE html>
<html>
<head>
<title>DN CLUB</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/main.css" />
<script src="https://code.jquery.com/jquery.js"></script>

<script type="text/javascript">
function btn_click(num) {
	if(num == 1){
		document.orderForm.action = "prepareOrder.do";
	} else if(num == 2){
		document.orderForm.action = "insertShoppingBasketList.do";
	}
	document.orderForm.submit();
}
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
				<form name="orderForm" method="post">
				<input type="hidden" value="book" name="from"/>
					<table border="1" cellpadding="0" cellspacing="0">
						<tr>
							<img src="${book.url }" alt="${book.bookName }" />
						</tr>
						<tr>
							<td bgcolor="#CCCCCC">도서번호</td>
							<td><input type="hidden" value="${book.bookNum }" name="bookNum"/>${book.bookNum }</td>
						</tr>
						<tr>
							<td bgcolor="#CCCCCC">도서이름</td>
							<td><input type="hidden" value="${book.bookName }" name="bookName"/>${book.bookName }</td>
						</tr>
						<tr>
							<td bgcolor="#CCCCCC">재고수량</td>
							<td><input type="hidden" value="${book.stockQty }" name="stockQty"/>${book.stockQty }</td>
						</tr>
						<tr>
							<td bgcolor="#CCCCCC">판매가</td>
							<td><input type="hidden" value="${book.price }" name="price"/>￦${book.price }</td>
						</tr>
						<tr>
							<td bgcolor="#CCCCCC">수량</td>
							<td><input type="text" name="qty" value="1" width="50"/></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="button" value="주문하기" onclick="btn_click(1)"/>   
							<input type="button" value="장바구니 추가" onclick="btn_click(2)" /></td>
						</tr>
					</table>
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