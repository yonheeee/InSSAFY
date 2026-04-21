package edu.ssafy.controller;

import java.io.IOException;
import java.util.List;

import edu.ssafy.dto.MemberDto;
import edu.ssafy.service.MemberService;
import edu.ssafy.service.MemberServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/member")
public class FrontMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		service = MemberServiceImpl.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.setContentType("text/html; charset=utf-8 ");
		process(request,response);
	}
	
	private String url = "index.jsp"; //멤버변수는 url만
			
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			if(action != null || action.isBlank()) {
				//member~이 controller
				if(action.endsWith("select")) {
					url = memberSelect(request,response);
				}else if(action.endsWith("selectone")) {
					url = memberSelectOne(request,response);
				}
				else if(action.endsWith("insert")) {
					url = memberInsert(request,response);
				}else if(action.endsWith("insertform")) {
					url = "/member/insert.jsp";
				}else if(action.endsWith("update")) {
					url = memberUpdate(request,response);
				}else if(action.endsWith("delete")) {
					url = memberDelete(request,response);
				}
				else if(action.endsWith("init")) {
					url = "/index.jsp";
				}
				else if(action.endsWith("detail")) {
					url = memberDetail(request,response);
				}
			}
		}catch(Exception e) {
			url = "/error/error.jsp";
		}
		if(url.startsWith("redirect:")) {
		    url = url.substring(url.indexOf(":") + 1);
		    response.sendRedirect(request.getContextPath() + url);
		} else {
		    request.getRequestDispatcher(url).forward(request, response);
		}
	}
	
	private String memberDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		MemberDto mem = service.selectOne(id);
		request.setAttribute("mem",mem);
		return "/member/detail/jsp";
	}

	private String memberSelectOne(HttpServletRequest request, HttpServletResponse response)throws Exception {
		String name = request.getParameter("name");
		MemberDto dto = service.select(name);
		response.getWriter().write(dto.toString());
		return "/member/detail.jsp";
	}
	private String memberInsert(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//1. 파라미터 처리
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String[] hobby = request.getParameterValues("hobby");
		MemberDto dto = new MemberDto(id,password,name, age,hobby);
		
		//2.로직리
		service.insert(dto);
		//3. 화면처리
		//response.getWriter().write(dto+"잘 입력되었습니다.");
		request.setAttribute("res", "입력이 잘 되었습니다");
		return "redirect:/member?action=select";
	
	}
	private String memberDelete(HttpServletRequest request, HttpServletResponse response)throws Exception {
		String id = request.getParameter("id");
		service.delete(id);
		//response.getWriter().write(id+" : 잘 삭제되었습니다");
		return "redirect:/member?action=select";
	}
	private String memberUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String[] hobby = request.getParameterValues("hobby");
		MemberDto dto = new MemberDto(id,password,name, age,hobby);
		
		//2.로직처리
		service.update(dto);
		//3. 화면처리
		//response.getWriter().write(dto+"잘 수정되었습니다");
		return "redirect:/member?action=select";
	}
	private String memberSelect(HttpServletRequest request, HttpServletResponse response)throws Exception {
		List<MemberDto> list = service.select();
		//response.getWriter().write(list.toString());
		request.setAttribute("list", list);
		return "/member/select.jsp";
	}


}