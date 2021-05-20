<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>登录界面</title>
	<style>
		#base{position: relative;}
		.check{display: none; position: absolute;}
		input[name=checkvalue]{display:none;}
	</style>

</head>
<body>
<h2>用户登录</h2>
<form action="login.do?act=in" method="post">
	用户名：<input name="uname"><br>
	密码：<input name="upass" type="password"><br>
	验证码：<input name="checkvalue" type="checkbox" value="0">
	<input name="checkvalue" type="checkbox" value="1">
	<input name="checkvalue" type="checkbox" value="2">
	<input name="checkvalue" type="checkbox" value="3">
	<input name="checkvalue" type="checkbox" value="4">
	<input name="checkvalue" type="checkbox" value="5">
	<input name="checkvalue" type="checkbox" value="6">
	<input name="checkvalue" type="checkbox" value="7">
	<div>
		<div id="base">
			<img id="checkcode" src="CheckCode" style="width: 630px; height: 400px; " onclick="clickImg(event)" id="bigPicture">
		</div>
	</div>
	<img name="check" src="checkcode/check.png" class="check">
	<img name="check" src="checkcode/check.png" class="check">
	<img name="check" src="checkcode/check.png" class="check">
	<img name="check" src="checkcode/check.png" class="check">
	<img name="check" src="checkcode/check.png" class="check">
	<img name="check" src="checkcode/check.png" class="check">
	<img name="check" src="checkcode/check.png" class="check">
	<img name="check" src="checkcode/check.png" class="check">
	<script>
		function clickImg(e) {
			var baseDiv = document.getElementById("base");
			var checkBox = document.getElementsByName("checkvalue");
			var topValue = 0;
			var leftValue = 0;
			var obj = baseDiv;
			while (obj) {
				leftValue += obj.offsetLeft;
				topValue +=obj.offsetTop;
				obj = obj.offsetParent;
			}
			//解决firefox获取不到点击事件的问题
			var clickEvent = e ? e : (window.event ? window.event : null);

			var clickLeft = clickEvent.clientX + document.body.scrollLeft - document.body.clientLeft;
			var clickTop = clickEvent.clientY + document.body.scrollTop - document.body.clientTop;
			//alert("" + (clickLeft-leftValue) + "," + (clickTop-topValue));
			row = parseInt((clickTop - topValue - 80) / 160);
			col = parseInt((clickLeft - leftValue) / 160);
			//alert("" + row + "," + col);
			checkBox[row*4+col].checked=!checkBox[row*4+col].checked;

			showCheck(leftValue, topValue);
		}

		function showCheck(mx, my){
			var baseDiv = document.getElementById("base");
			var checkBox = document.getElementsByName("checkvalue");
			var check = document.getElementsByName("check");
			for(i=0; i<checkBox.length; i++){
				if(checkBox[i].checked){
					check[i].style.display = "block";
					check[i].style.top = "" + (parseInt(i/4) * 160 + 80 + 10 + my) + "px";
					check[i].style.left = "" + (parseInt(i%4) * 160 + 100 + mx) + "px";
				}else{
					check[i].style.display = "none";
				}
			}
		}
	</script>
	<br>
	<span><%=status==null?"":status %></span>
	<br>
	<input type="submit" value="登录">
	<input type="reset" value="取消">
</form>
</body>
</html>