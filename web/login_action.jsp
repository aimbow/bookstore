<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String[] userlist = {"test01", "guest", "abcde", "hello"};
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录结果</title>
</head>
<body>
<% 
boolean logsuc = false;	// 登录状态
String username = request.getParameter("uname");	// 接收用户名
String userpass = request.getParameter("upass");	// 接收密码
for(int i=0; i<userlist.length; i++){	// 与用户名数组匹配
	if(userlist[i].equals(username)){
		logsuc = true;
		break;
	}
}
if(logsuc){
	out.print(username + "登录成功！");
	// session：JSP的内置对象，用于保存用户登录后的数据。
	session.setAttribute("myusername", username);
	// response.sendRedirect("index.jsp");
%>
<script>
time = 5;
function count(){
	document.getElementById("time").innerText = time;
	if(time <= 0){
		location.href="index.jsp";
	}else{
		time -= 1;
	}
}
setInterval("count()", 1000);
</script>
<span id="time">6</span>秒钟后跳转！[<a href="index.jsp">直接跳转</a>]
<%
}else{
	out.print("登录失败！");
}
%>
</body>
</html>