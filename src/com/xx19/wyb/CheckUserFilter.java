package com.xx19.wyb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class CheckUserFilter
 */
@WebFilter({ "/login.do", "/register.do" })
public class CheckUserFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CheckUserFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		String username = request.getParameter("uname");	// �����û���
		String userpass = request.getParameter("upass");	// ��������

		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("gbk");
		
		if(username!=null && userpass!=null) {
		
			if(username.length()<6 || username.length()>20) {
				out.print("username's length is wrong!");
				return;
			}
			if(userpass.length()<6 || userpass.length()>20) {
				out.print("password's length is wrong!");
				return;
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
