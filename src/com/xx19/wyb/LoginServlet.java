package com.xx19.wyb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/LoginServlet", "/login.do" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// ���տͻ��˵�act�������жϵ��뻹���˳�
		String action = (String)request.getParameter("act");
		
		if(action.equals("in")){
			login(request, response);
		}else{
			logout(request, response);
		}
	}

	/*
	 * ��������checkUname
	 * ���ܣ�����û���������
	 * ���룺username, userpass
	 * �����true �� false
	 * �汾��1.0
	 * ִ���ˣ�����
	 * ���ڣ�2021-04-15 */
	private boolean checkUname(String un, String up) {
		boolean logsuc = false;	// ��¼״̬
		String[] userlist = {"test01", "guest", "abcde", "hello"};
		String[] passlist = {"123456", "hello", "howru", "niceto"};
		for(int i=0; i<userlist.length; i++){	// ���û�������ƥ��
			if(userlist[i].equals(un) && passlist[i].equals(up)){
				logsuc = true;
				break;
			}
		}
		return logsuc;
	}
	
	private void login(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		boolean logsuc = false;	// ��¼״̬
		
		HttpSession session = request.getSession();	// ����session
		session.setMaxInactiveInterval(1);			// ����session��Ч��
		RequestDispatcher rd;// response.sendRedirect()������ֱ�ӣ����ܴ������� RequestDispatcher()�������Դ�����
		rd = request.getRequestDispatcher("index.jsp");	// ������תҳ��
		
		String username = request.getParameter("uname");	// �����û���
		String userpass = request.getParameter("upass");	// ��������
		System.out.print(username);
		if(checkUname(username, userpass)) {	// ����û���
			session.setAttribute("myusername", username);// response.getWriter().append("��¼�ɹ���");response.sendRedirect("index.jsp");
			Cookie cname = new Cookie("name", username); // д�� Cookie;
			if(request.getParameter("usersave")!=null){
			       cname.setMaxAge(60*60*24); // ������Ч�ڣ����ӣ�
			}else{
				cname.setMaxAge(0); // ����Ϊ0�൱�����
			}
			response.addCookie(cname);  // �� Cookie ���� response

			
			request.setAttribute("status", "��¼�ɹ���");
		}else {// response.getWriter().append("��¼ʧ�ܣ�");// response.sendRedirect("index.jsp");
			request.setAttribute("status", "��¼ʧ�ܣ�");
		}
		rd.forward(request, response);
	}
	
	private void logout(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("index.jsp");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
