package com.multicampus.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.multicampus.biz.board.BoardDAO;
import com.multicampus.biz.board.BoardVO;
import com.multicampus.controller.Controller;

public class GetBoardListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 목록 검색 기능 처리");
		// 1. 사용자 입력정보 추출(검색 기능은 나중에...)

		// 2. DB 연동 처리
		BoardVO vo = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		java.util.List<BoardVO> boardList = boardDAO.getBoardList(vo);

		// 3. 응답 화면 구성
		HttpSession session = request.getSession();
		session.setAttribute("boardList", boardList);
		return "getBoardList.jsp";

	}

}
