package com.ssafy.exam.controller;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ControllerHelper {
    default String getActionParameter(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null || action.isBlank()) {
            action = "index";
        }
        return action;
    }
    //TODO 1-1 : 정상적으로 리다이렉트 될 수 있도록 아래 메소드를 완성하시오.
    public default void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
    	if(path.startsWith(path)) {
    		response.sendRedirect(path);
    	}else {
    		response.sendRedirect(request.getContextPath() + path);
    	}
    }
    //TODO 1-2 : 정상적으로 포워딩 될 수 있도록 아래 메소드를 완성하시오.
    public default void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
    	RequestDispatcher disp = request.getRequestDispatcher(path);
    	disp.forward(request, response);
    }
    
    public default void setupCookie(String name, String value, int maxAge, String path,
            HttpServletResponse resp) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        if (path != null) {
            cookie.setPath(path);
        }
        resp.addCookie(cookie);
    }
    
}
