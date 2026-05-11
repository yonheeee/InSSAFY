package edu.ssafy.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import edu.ssafy.dto.MemberDto;
import edu.ssafy.service.MemberService;
import edu.ssafy.service.MemberServiceImpl;

/**
 * Servlet implementation class FrontMemberServlet
 */
@WebServlet("/member")
public class FrontMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		service = MemberServiceImpl.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.setContentType("text/html; charset=utf-8 ");
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.setContentType("text/html; charset=utf-8 ");
		process(request, response);
	}

	private String url = "index.jsp";

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			if (action != null && !action.isBlank()) {
				if (action.endsWith("select")) {
					url = memberSelect(request, response);
				} else if(action.endsWith("selectone")) {
					url = memberSelectOne(request, response);
				} else if (action.endsWith("insert")) {
					url = memberInsert(request, response);
				}else if (action.endsWith("insertform")) {
					url = "/member/insert.jsp";
				}
				else if (action.endsWith("update")) {
					url = memberUpdate(request, response);
				} else if (action.endsWith("delete")) {
					url = memberDelete(request, response);
				} else if(action.endsWith("init")) {
					url = "/index.jsp";
				} else if(action.endsWith("detail")) {
					url = memberDetail(request, response);
				} else if(action.endsWith("loginform")) {
					url = "/member/login.jsp";
				} else if(action.endsWith("login")) {
					url = memberLogin(request,response);
				} else if(action.endsWith("logout")) {
					request.getSession().invalidate();
					url = "/member?action=loginform";
				}else if(action.endsWith("delsid")) {
					url = memberDeleteIds(request, response);
				}
			}
		} catch (Exception e) {
			url = "/error/error.jsp";
		}
		if(url.startsWith("redirect:")) {
			url = url.substring(url.indexOf(":")+1);
			response.sendRedirect(request.getContextPath()+url);
		}else {
			request.getRequestDispatcher(url).forward(request, response);
		}
		
	}

	private String memberDeleteIds(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] delsId = request.getParameterValues("delsid");
		service.deleteIds(delsId);
		return "redirect:/member?action=select";
	}

	private String memberLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		if(service.login(id, password)) {
			// login 성공
			request.getSession().setAttribute("login", id);
			
			// saveid 불러와 쿠키로 저장
			String saveId = request.getParameter("saveid");
			if(saveId != null) {
				Cookie cookie = new Cookie("saveid",saveId);
				cookie.setMaxAge(60*60*24);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			//check가 안되어 있다면
			else {
				Cookie cookie = new Cookie("saveid",saveId);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			
			
			return "redirect:/member?action=select";
			
		}else {
			// 실패
			request.setAttribute("msg", "로그인처리 실패");
			return "redirect:/member?action=loginform";
		}
		
	}

	private String memberDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		MemberDto mem = service.selectOne(id);
		request.setAttribute("mem", mem);
		return "/member/detail.jsp";
	}

	private String memberSelectOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		MemberDto mem = service.select(name);
		//response.getWriter().write(dto.toString());
		request.setAttribute("mem", mem);
		return "/member/detail.jsp";
		
	}

	private String memberDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		System.out.println("id:"+id);
		service.delete(id);
		//response.getWriter().write(id + " : 잘 삭제됬어요");
		return "redirect:/member?action=select";
	}

	private String memberUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 파라미터 처리
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String[] hobby = request.getParameterValues("hobby");
		MemberDto dto = new MemberDto(id, password, name, age, hobby);
		// 2. logic 처리
		service.update(dto);
		// 3. 화면처리
		//response.getWriter().write(dto + " 잘 수정됬어요");
		return "redirect:/member?action=select";
	}

	private String memberInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 파라미터 처리
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String[] hobby = request.getParameterValues("hobby");
		MemberDto dto = new MemberDto(id, password, name, age, hobby);
		// 2. logic 처리
		service.insert(dto);
		// 3. 화면처리
		//response.getWriter().write(dto + " 잘 입력됬어요");
		//request.setAttribute("res", "입력 잘 됬어요");
		return "redirect:/member?action=select";
	}

	private String memberSelect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<MemberDto> list = service.select();
		//response.getWriter().write(list.toString());
		request.setAttribute("res", list);
		return "/member/select.jsp";
	}

}
