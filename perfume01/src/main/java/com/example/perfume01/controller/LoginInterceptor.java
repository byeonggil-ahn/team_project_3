package com.example.perfume01.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("loginID") != null) {
            return  true;
        } else {
            request.setAttribute("msg", "로그인 후 이용 가능합니다.");
            String uri = "/WEB-INF/views/member/mLoginForm.jsp";
            request.getRequestDispatcher(uri).forward(request, response);
            return false;
        }
    }

}
