package com.ssafy.ws.step1.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import com.ssafy.ws.step1.dto.Car;

/**
 * Servlet implementation class MainServelt
 */
@WebServlet("/main")
public class MainServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * front controller parttern을 적용하기 위한 내부적 process 호출
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// request 객체에서 action파라미터 추출해서 실제 미지니스 로직을 추출
		String action = request.getParameter("action");

		if (action == null) {
			action = ""; // 만약 null이면 기본
		}
		switch (action) {
		case "regist":
			doRegist(request, response);
			break;
		default:
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write("<html><body><h1>알 수 없는 요청입니다.</h1></body></html>");
		}
	}

	/*
	 * 자동차 정보를 등록하기 위해 파라미터 잘 전달되는지 확인하고 화면에 출력 request에서 전달 받은 내용 추출 Car 객체 생성 후
	 * response로 출력 특히 response시 content 형식 주의
	 */
	private void doRegist(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");

		try {
			// request 객체에서 전달된 parameter 추출
			String VIN = request.getParameter("VIN");
			String modelName = request.getParameter("modelName");
			String color = request.getParameter("color");
			// 문자열로 전달된 mileage는 숫자로 변환
			int mileage = Integer.parseInt(request.getParameter("mileage"));

			//객체 생성
			Car car = new Car(VIN, modelName, color, mileage);
			
			//세션에 지금까지 등록된 자동차 수 주장한다
			HttpSession session = request.getSession();
			
			Integer carCount = (Integer)session.getAttribute("carCount");
			if(carCount == null) {
				carCount = 0;
			}
			carCount++;
			session.setAttribute("carCount", carCount);
			
			//전달받은 파라미터 request에 담기
			request.setAttribute("VIN", VIN);
			request.setAttribute("modelName", modelName);
			request.setAttribute("color", color);
			request.setAttribute("mileage", mileage);
			request.setAttribute("carCount", carCount);
			
			//JSP화면 호출을 위해 RequestDispatcher의 forward를 사용
			RequestDispatcher disp = request.getRequestDispatcher("/regist_result.jsp");
			disp.forward(request, response);
			

			StringBuilder output = new StringBuilder();
			output.append("<html><body>").append("<h1>자동차 정보</h1>").append(car.toString()).append("</body></html>");
			response.getWriter().write(output.toString());

		} catch (NumberFormatException e) {
			response.getWriter().write("<html><body><h1>오류</h1><p>주행거리는 숫자로 숫자로 입력해주세요.</p></body></html>");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

}
