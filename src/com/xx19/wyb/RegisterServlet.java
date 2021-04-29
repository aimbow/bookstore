package com.xx19.wyb;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.*;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet({ "/RegisterServlet", "/reg.do", "/reg.action" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		SmartUpload su = new SmartUpload();
		ServletConfig config = this.getServletConfig();
		su.initialize(config, request, response);
		try {
			su.upload();  // 上传文件
			com.jspsmart.upload.File sf = su.getFiles().getFile(0); // 文件对象
			String name = sf.getFileName();  // 文件名

			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
			String fname = sdf.format(now) + "." + sf.getFileExt();
			SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY");

			/* 先建立目录（需要物理路径），再上传文件 */
			java.io.File fdir = new File(
					request.getSession().getServletContext().getRealPath("/")
							+ "/upload/" + sdf2.format(now));
			fdir.mkdir();

			sf.saveAs("/upload/" + sdf2.format(now) + "/" +fname,
					SmartUpload.SAVE_AUTO);

			/* 一般表单数据需要使用 jspsmartupload 的 getRequest() 方法  */
			String username = su.getRequest().getParameter("uname");
			String userpass = su.getRequest().getParameter("upass");
			String[] interest = su.getRequest().getParameterValues("interest");

			PrintWriter out = response.getWriter();
			out.print("<html><head><title>注册结果</title><meta charset=\"utf-8\"></head><body>");
			out.print("<b>IP地址</b>为：" + request.getRemoteAddr() + "<br>");
			String header = request.getHeader("User-Agent");
			out.print("你的浏览器为：" + header + "<br>");
			out.print("用户名：" + username + "<br>");
			out.print("密码：" + userpass + "<br>");
			out.print("你的兴趣：");
			// 输出兴趣数组
			for(int i=0; i<interest.length; i++){
				out.print(interest[i] + " ");
			}
			out.print("<br>");
			out.print("文件：<a href='upload/" + sdf2.format(now) + "/" + fname + "'>");
			out.print("["+name+"]下载</a></body></html>");
		} catch (Exception e) {
			e.printStackTrace();
		}



		/*String username = request.getParameter("uname");
		String userpass = request.getParameter("upass");
		String usergender = request.getParameter("gender");

		System.out.print("user:" + username);

		// 获取兴趣数组
		String[] userinter = request.getParameterValues("interest");

		PrintWriter out = response.getWriter();

		RequestDispatcher rd;

		if((username.length()<6)||(username.length()>20)) {
			// out.print("用户名长度不符合要求！");
			rd = request.getRequestDispatcher("register.jsp");
			request.setAttribute("status", "用户名长度不符合要求！");
			rd.forward(request, response);
		}


		String ipaddr = request.getRemoteAddr();
		out.print("<html><head><title>注册结果</title><meta charset=\"utf-8\"></head><body>");
		out.print("<b>IP地址</b>为：" + ipaddr + "<br>");
		String header = request.getHeader("User-Agent");
		out.print("你的浏览器为：" + header + "<br>");
		out.print("注册成功！<br>用户名：" + username + "<br>密码：" +
			userpass + "<br>性别：" + usergender + "<br>");
		out.print("你的兴趣：");
		// 输出兴趣数组
		for(int i=0; i<userinter.length; i++){
			out.print(userinter[i] + " ");
		}
		// 猜你喜欢
		if(usergender.equals("male")){
			out.print("给你推荐：电脑、运动装备、王者。");
		}else{
			out.print("给你推荐：化妆品、美食、消消乐。");
		}
		out.print("</body></html>");
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

