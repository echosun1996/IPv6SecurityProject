package com.echosun.Filter;

/*
 * 过滤器
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		HttpSession section = request2.getSession();
		//System.out.println("header" + request2.getHeader("Referer"));
		//System.out.println("原始路径" + request2.getRequestURI());
		if (request2.getRequestURI().equals("/WebServer/index.jsp")) {
			if (section.getAttribute("loginuser") != null) {
				response2.sendRedirect("/WebServer/login_success.jsp");
			} else {
				filterChain.doFilter(request, response);
			}
		} else if (request2.getRequestURI().equals("/WebServer/login_success.jsp")) {
			if (section.getAttribute("loginuser") != null) {
				filterChain.doFilter(request, response);
			} else
				response2.sendRedirect("/WebServer/index.jsp");
		} else if (request2.getRequestURI().indexOf("/WebServer/Frame/") != -1) {
			if (section.getAttribute("loginuser") != null) {
				filterChain.doFilter(request, response);
			} else
				response2.sendRedirect("/WebServer/loginout.jsp");
		} else {
			response2.sendRedirect("/WebServer/index.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}


}
