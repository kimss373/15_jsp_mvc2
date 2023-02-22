package boardBasic.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardBasic.dao.BoardDAO;
import boardBasic.dto.BoardDTO;

@WebServlet("/bUpdate")
public class UpdateBoard extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDTO boardDTO = BoardDAO.getInstance().getBoardDetail(Integer.parseInt(request.getParameter("num")), false);
		
		request.setAttribute("boardDTO", boardDTO);
		
		RequestDispatcher dis = request.getRequestDispatcher("step1_boardBasicEx/bUpdate.jsp");
		dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNum(Integer.parseInt(request.getParameter("num")));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setPassword(request.getParameter("password"));
		boardDTO.setContent(request.getParameter("content"));
		String jsScript = "";
		if (BoardDAO.getInstance().updateBoard(boardDTO)) {
			jsScript += "<script>";
			jsScript += "alert('수정 되었습니다.');";
			jsScript += "location.href='bList';";
			jsScript += "</script>";
			
		} else {
			jsScript += "<script>";
			jsScript += "alert('패스워드를 확인하세요.');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
		}
		
		response.setContentType("text/html; charset=UTF-8"); 
		
		PrintWriter pw = response.getWriter();
		pw.print(jsScript);
		
	}

}
