package com.practice.tasktracking.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.practice.tasktracking.entities.User;

/**
 * Servlet Filter implementation class RequestProcessorFilter
 */
@WebFilter(urlPatterns = "/*")
public class RequestProcessorFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public RequestProcessorFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute("user");
		String uri = httpRequest.getRequestURI();
		System.out.println("user : "+user + " || uri: "+uri+" || sessionId: "+session.getId());
		if(uri.endsWith("Home.jsp") && user == null){
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect("Login.jsp");
		}else{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
