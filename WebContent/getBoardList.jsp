<%@page import="com.multicampus.biz.user.UserVO"%>
<%@page import="java.util.List"%>
<%@page import="com.multicampus.biz.board.BoardDAO"%>
<%@page import="com.multicampus.biz.board.BoardVO"%>
<%@page contentType="text/html; charset=EUC-KR"%>

<%
	// 세션에 저장된 글 목록을 꺼낸다.
	List<BoardVO> boardList = (List) session.getAttribute("boardList");
	//세션에 저장된 유저 정보를 꺼낸다
	UserVO user = (UserVO) session.getAttribute("user");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>글 목록</title>
</head>
<body>
	<center>
		<h1>글 목록</h1>
		<h3>
			<%if(user != null){ %><%=user.getName()%><%} else { %>테스트<%} %>님 로그인 환영합니다...<a href="login.jsp">Log-in</a>...<a href="logout_proc.jsp">Log-out</a>...<a href="signUp.jsp">Sign-Up</a>
		</h3>

		<!-- 검색 시작 -->
		<form action="getBoardList.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="right">
					<select name="searchCondition">
							<option value="TITLE">제목</option>
							<option value="CONTENT">내용</option>
					</select> <input name="searchKeyword" type="text" /> <input type="submit" value="검색" /></td>
				</tr>
			</table>
		</form>
		<!-- 검색 종료 -->

		<table border="1" cellpadding="0" cellspacing="0" width="700">
			<tr>
				<th bgcolor="orange" width="100">번호</th>
				<th bgcolor="orange" width="200">제목</th>
				<th bgcolor="orange" width="150">작성자</th>
				<th bgcolor="orange" width="150">등록일</th>
				<th bgcolor="orange" width="100">조회수</th>
			</tr>
			<!-- 아래의 자바코드들은 컨트롤 로직이 아니기 때문에 뷰에 해당한다.
				 모든 자바 코드가 사라지는게 아니다. (가능은함 EL, JSTL(JSP문법) 사용하여)
				 EL(Expression Language) session이나 request 내장객체에 저장된 데이터를 JSP에서 사용하기 위한 표현식
				 JSTL(JSP Standard Tag Library) if,for,switch 등과 같은 자바 로직을 대체하는 표준 태그
				 분기처리를 위한 if, forEach 태그만 주로씀 -->
			<%
				for (BoardVO board : boardList) {
			%>
			<tr>
				<td><%=board.getSeq()%></td>
				<td align="left"><a href="getBoard.do?seq=<%=board.getSeq()%>"><%=board.getTitle()%></a></td>
				<td><%=board.getWriter()%></td>
				<td><%=board.getRegDate()%></td>
				<td><%=board.getCnt()%></td>
			</tr>
			<%
				}
			%>

		</table>
		<br> <a href="insertBoard.jsp">새글 등록</a>
	</center>
</body>
</html>



