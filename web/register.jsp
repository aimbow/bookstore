<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>>>财大书城 ** 用户注册<<</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div id="banner" class="layout"><img src="images/banner.jpg"></div>
	
	<div class="box" id="main">
		<h2>用户注册</h2>
		<form action="reg.do" method="post" enctype="multipart/form-data">
			用户名：<input name="uname"><br>
			密码：<input name="upass" type="password"><br>
			<!-- 性别包含两个控件 -->
			性别：<input type="radio" value="male" name="gender" checked>男
			<input type="radio" value="female" name="gender">女<br>
			兴趣：<input type="checkbox" value="IT" name="interest">IT
			<input type="checkbox" value="PE" name="interest">体育
			<input type="checkbox" value="FE" name="interest">财经
			<input type="checkbox" value="MI" name="interest">军事
			<input type="checkbox" value="ED" name="interest">教育
			<input type="checkbox" value="SO" name="interest">社会<br>
			<% String status = (String)request.getAttribute("status");
				out.print(status==null?"":status);
			%>
			<br>
			<input type="file" name="logo1">
			<br><br>
			<input type="file" name="logo2">
			<br><br>
			<input type="file" name="logo3">
			<br><br>
			<input type="submit" value="注册">
			<input type="reset" value="取消">
		</form>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>