package edu.ssafy.local;

import java.io.IOException;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/generic")
public class MyClass extends GenericServlet{

	//초기화 시
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("init");
	}

	//요청 시에 매번
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		System.out.println("service");
	}
	
	//
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("destroy");
	}

}
