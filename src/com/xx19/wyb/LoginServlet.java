package com.xx19.wyb;

import java.io.IOException;
import java.sql.*;

import javax.naming.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.*;

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

		String action = (String)request.getParameter("act");

		if(action.equals("in")){
			try {
				login(request, response);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | ServletException
					| IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			logout(request, response);
		}
	}

	/*
	 * 方法名：checkUname
	 * 功能：实现用户登录
	 * 输入：username, userpass
	 * 输出：true、false
	 * 版本：1.0
	 * 开发者：陈明晶
	 * 日期：2021-05-13 */
	private User checkUname(String un, String up) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, NamingException {
		User login = new User();

		/*String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver).newInstance();

		// 数据库连接参数
		String dbname = "bookstore039";
		String uname = "root";
		String upass = "123456";

		// 构建数据库连接字符串
		String url = "jdbc:mysql://localhost:3306/";
			url += dbname + "?user=" + uname + "&password=" + upass;
			url += "&useUnicode=true&characterEncoding=utf8";
		// out.print(url);
		Connection conn = DriverManager.getConnection(url);
		*/

		// 第二种方案：以下使用数据库连接池来连接数据库
		BasicDataSource bds=new BasicDataSource();
		bds.setUrl("jdbc:mysql://localhost:3306/bookstore039?useUnicode=true&characterEncoding=utf-8");
		bds.setUsername("root");
		bds.setPassword("123456");
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		// 此处省略数据源的配置参数，见下页
		//Connection conn = bds.getConnection();

		// 以下为选择性配置
		bds.setMaxTotal(30); 		//初始连接数
		bds.setMaxIdle(10);	 	//最大空闲数
		bds.setMinIdle(5); 			//最小空闲数
		bds.setMaxWaitMillis(10000); //最长等待时间(ms)
		bds.setRemoveAbandonedTimeout(100); //超时则关闭(s)
		bds.setRemoveAbandonedOnBorrow(true); //用完后是否回收
		bds.setRemoveAbandonedOnMaintenance(true);


		// 第三种方案：静态连接池
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/bookstore");

		// 获取连接
		Connection conn = ds.getConnection();



		Statement stmt = conn.createStatement();
		String sql = "select uid,ucode,uname,upass,ugender from t_user ";
		sql += "where ucode='"+un+"' and upass='"+up+"'";
		System.out.print(sql);
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			login.setUid(rs.getInt(1));
			login.setUcode(rs.getString(2));
			login.setUname(rs.getString(3));
			login.setUpass(rs.getString(4));
			login.setUgender(rs.getInt(5));
		}
		rs.close();
		stmt.close();
		conn.close();
		return login;
	}

	private void login(HttpServletRequest request,
					   HttpServletResponse response) throws ServletException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NamingException {
		boolean logsuc = false;

		HttpSession session = request.getSession();
		User userinfo;

		String[] validcode = request.getParameterValues("checkvalue");
		String validstring = "";
		for(int i=0; i<validcode.length; i++) {
			validstring += validcode[i];
		}
		if(session.getAttribute("checkcode").equals(validstring)) {

			RequestDispatcher rd;

			String username = request.getParameter("uname");
			String userpass = request.getParameter("upass");
			System.out.print(username);
			userinfo = checkUname(username, userpass);
			if(userinfo.getUid()!=0) {
				session.setAttribute("login", userinfo);
				Cookie cname = new Cookie("name", username);
				if(request.getParameter("usersave")!=null){
					cname.setMaxAge(60*60*24);
				}else{
					cname.setMaxAge(0);
				}
				response.addCookie(cname);

				rd = request.getRequestDispatcher("index.jsp");

				request.setAttribute("status", "登录成功！");
			}else {
				rd = request.getRequestDispatcher("login.jsp");
				request.setAttribute("status", "登录失败！");
			}
			rd.forward(request, response);
		}else {
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("login.jsp");
			request.setAttribute("status", "验证码错误！");
			rd.forward(request, response);
		}
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
