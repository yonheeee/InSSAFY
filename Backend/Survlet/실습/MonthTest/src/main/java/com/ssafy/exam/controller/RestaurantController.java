package com.ssafy.exam.controller;

import java.io.IOException;

import com.ssafy.exam.model.dto.Member;
import com.ssafy.exam.model.dto.Restaurant;
import com.ssafy.exam.model.service.RestaurantServiceImpl;
import com.sun.net.httpserver.Request;
import com.ssafy.exam.model.service.RestaurantService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

///////////////////////////////////////////////////////
//------아래 코드를 확인하고 코드를 완성하세요.---------------//
///////////////////////////////////////////////////////

@WebServlet("/restaurant")
public class RestaurantController extends HttpServlet implements ControllerHelper {

	private final RestaurantService rService = RestaurantServiceImpl.getService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = getActionParameter(req, resp);

		switch (action) {
		case "index":
			forward(req, resp, "/index.jsp");
			break;
		case "list":
			list(req, resp);
			break;
		case "registForm":
			forward(req, resp, "/restaurant/regist.jsp");
			break;
		case "detail":
			detail(req, resp);
			break;
		case "delete":
			delete(req, resp);
			break;
		case "loginForm":
			forward(req, resp, "/login-form.jsp");
			break;
		case "logout":
			logout(req, resp);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = getActionParameter(req, resp);

		switch (action) {
		case "regist":
			regist(req, resp);
			break;
		case "login":
			login(req, resp);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

	}

	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("restaurants", rService.selectAll());
		forward(req, resp, "/restaurant/list.jsp");
	}

	private void regist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		String category = req.getParameter("category");
		Integer rating = Integer.parseInt(req.getParameter("rating"));
		String review = req.getParameter("review");
		String regdate = req.getParameter("regDate");

		Restaurant restaurant = new Restaurant(code, name, category, rating, review, regdate);

		rService.insert(restaurant);

		redirect(req, resp, req.getContextPath() + "/restaurant?action=list");
	}

	private void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");

		Restaurant restaurant = rService.selectByCode(code);

		req.setAttribute("restaurant", restaurant);

		forward(req, resp, "/restaurant/detail.jsp");
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String code = req.getParameter("code");

		rService.deleteByCode(code);

		redirect(req, resp, req.getContextPath() + "/restaurant?action=list");
	}

	private void login(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		  
			   String email = req.getParameter("email");
			   String password = req.getParameter("password");
			   
			   Member member = rService.login(email, password);
			   	
			   if(member != null) {
				   req.getSession().setAttribute("loginUser", member);
				   redirect(req, resp, "/restaurant?action=index");
			   }else {
				req.setAttribute("alertMsg", "로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요");
				forward(req, resp, "/login-form.jsp");
			 }
	}

	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().invalidate();
		redirect(req, resp, "/restaurant?action=index");
	}
}
