package com.ssafy.ws.ste2.servlet;

import java.io.IOException;

import com.ssafy.ws.step2.dto.Movie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MainServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String action = request.getParameter("action");
		
		if(action == null) {
			action = "";
		}
		
		switch(action) {
		case "regist":
			doregist(request,response);
			break;
		default :
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write("<html><body><h1>알 수 없는 요청입니다.</h1></body></html>");
		}
		
	}

	private static int sequence = 1;
	private void doregist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		try {
		
			String title = request.getParameter("title");
			String director = request.getParameter("director");
			String genre = request.getParameter("genre");
			int runningTime = Integer.parseInt(request.getParameter("runningTime"));
			
			int id = sequence++;
			Movie movie = new Movie(id, title, director, genre, runningTime);
			
			StringBuilder sb = new StringBuilder();
			sb.append("<html><body>").append("<h1>영화 정보 입력</h1>").append(movie.toString()).append("</body></html>");
			response.getWriter().write(sb.toString());
			
		}catch(NumberFormatException e) {
			response.getWriter().write("<html><body><h1>오류</h1><p>상영시간은 숫자로 입력해주세요</p></body></html>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		process(request,response);
	}

}
