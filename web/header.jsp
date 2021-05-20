<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="css/style.css">
<script src="js/jquery-1.4.2.min.js"></script>
<script>
	$(function(){
		$("#nav ul li").hover(function(){
			$("#nav ul").find(".first").hide();
			$(this).find("ul").slideDown("slow");
		},function(){
			$(this).find("ul").slideUp("slow");
		});
		$("#nav ul").hover(function(){

		},function(){
			$(this).find(".first").slideDown("slow");
		});
	});
</script>


<div id="top">
	<div id="top1">
		<div class="left">
			<%
				String colid = (String)request.getParameter("col");
				if(colid==null) colid="1";	// 如果col为空，默认为1
//out.print(colid);

				Cookie[] cookies = request.getCookies();
				String name = null;
				if(cookies!=null){
					for (int i = 0; i < cookies.length; i++){
						Cookie  cookie = cookies[i];
						if((cookie.getName()).equals("name")){
							name = cookie.getValue();
						}
					}
				}
				name = name==null?"":name;
// 读取session值，判断是否登录
				User ulogin = (User)session.getAttribute("login");
				if(ulogin==null){
			%>
			欢迎访问财大书城，【<a href="login.jsp">登录</a>】
			【<a href="register.jsp">注册</a>】
			<!-- <form action="login.do?act=in" method="post">
		用户：<input type="text" name="uname" value="<%=name%>"> &nbsp;&nbsp;
		密码：<input type="password" name="upass" value="">&nbsp;
		<input type="checkbox" name="usersave" value="1">记住&nbsp;
		<input type="button" value="登 录" onclick="location.href='login.jsp';">&nbsp;
		<input type="button" value="注 册" onclick="location.href='register.jsp';">
	</form> -->
			<%
				String status = (String)request.getAttribute("status");
				if(status!=null) out.print(status);
			%>
			<%
				}else{
					out.print(ulogin.getUname() + "，欢迎回来！【<a href='login.do?act=out'>注销</a>】");
				}
			%>
		</div>
		<div class="right"><a href="#" id="weibo"></a><a href="#" id="wechat"></a><a href="#" id="tel"></a></div>
	</div>
</div>
<div id="middle" class="layout">
	<div id="logo"><img src="images/top_logo.jpg"></div>
	<div id="nav">
		<ul>
			<li><%
				if(colid.equals("1")){
			%>
				<span>首页</span>
				<%
				}else{
				%>
				<a href="index.jsp">首 页</a>
				<%	} %>
				<ul class="first">
					<li>欢迎访问浙江财经大学书城，请使用实名注册！</li>
				</ul>
			</li>
			<li><%
				if(colid.equals("2")){
			%>
				<span>新闻</span>
				<%
				}else{
				%><a href="news.jsp">新 闻</a><% } %>
				<ul>
					<li><a href="#">学校</a> | <a href="#">文化</a> | <a href="#">社会</a></li>
				</ul>
			</li>
			<li><%
				if(colid.equals("3")){
			%>
				<span>社区</span>
				<%
				}else{
				%><a href="comm.jsp">社 区</a><% } %>
				<ul>
					<li><a href="#">频道</a> | <a href="#">直播</a> | <a href="#">论坛</a>
						| <a href="#">专题</a> | <a href="#">VIP</a></li>
				</ul>
			</li>
			<li><%
				if(colid.equals("4")){
			%>
				<span>商城</span>
				<%
				}else{
				%><a href="shop.jsp">商 城</a><% } %>
				<ul>
					<li><a href="#">新书</a> | <a href="#">二手</a> | <a href="#">资料</a>
						| <a href="#">电子版</a></li>
				</ul>
			</li>
			<li><a href="#">客 服</a></li>
			<li><a href="#">合 作</a>
				<ul class="last">
					<li><a href="#">出版社</a> | <a href="#">网站</a> | <a href="#"> 移动端</a></li>
				</ul>
			</li>
		</ul>
	</div>
	<div id="cart"><a class="full" href="#">12</a></div>
</div>