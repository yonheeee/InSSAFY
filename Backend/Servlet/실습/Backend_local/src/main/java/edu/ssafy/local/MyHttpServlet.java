package edu.ssafy.local;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/myservlet")
public class MyHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		super.service(arg0, arg1);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
	
		System.out.printf("이름 : %s, 나이 : %d", name,age);
		
		response.setContentType("text/html; charset=utf-8");
		
		
		response.getWriter().write("get방식 이름 : %s, 나이 : %d".formatted(name,age));
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("post");
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
	
		System.out.printf("이름 : %s, 나이 : %d", name,age);
		
		String[] fruits= request.getParameterValues("과일");
		System.out.println("과일 : "+ Arrays.toString(fruits));
		
		response.setContentType("text/html; charset=utf-8");

		
		response.getWriter().write("post 이름 : %s, 나이 : %d,과일 %s".formatted(name,age,Arrays.toString(fruits)));
	}
}