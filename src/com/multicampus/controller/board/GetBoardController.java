package com.multicampus.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.multicampus.biz.board.BoardDAO;
import com.multicampus.biz.board.BoardVO;
import com.multicampus.controller.Controller;

public class GetBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
	System.out.println("글 상세 조회 기능 처리");
	String seq = request.getParameter("seq");

	// 2. DB 연동 처리
	BoardVO vo = new BoardVO();
	vo.setSeq(Integer.parseInt(seq));

	BoardDAO boardDAO = new BoardDAO();
	boardDAO.increaseBoardCnt(vo);
	BoardVO board = boardDAO.getBoard(vo);
	
	// 3. 응답 화면 구성
	HttpSession session = request.getSession();
	session.setAttribute("board", board);
	return "getBoard.jsp";
	}

}
