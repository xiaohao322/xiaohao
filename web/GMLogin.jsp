<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/28
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>贵美登录</title>
    <link rel="shortcut icon" href="static/images/login.jpg">
    <style>

        body {
            margin: 0;
            padding: 0;
            list-style: none;
            background-color: #999999;


        }
        .login{
            position: absolute;
            left: 420px;
            top: 150px;
        }
        .login_button{
            position: absolute;
            left: 550px;
            top: 420px;
        }
        .f1 input,.f2 input{
            width: 180px;
            height: 25px;
            border: 0px;
            outline: none;
            background-color:transparent;

        }
        .f1 {
            position: absolute;
            left: 590px;
            top: 290px;
        }
        .f2{
            position: absolute;
            left: 590px;
            top: 345px;
        }
        .f3{
            position: absolute;
            left: 590px;
            top: 385px;
        }

    </style>

    <script>
        $(function(){
            <%--var msg = "${param.msg}";--%>
            <%--if(msg.length>0){--%>
                <%--if(msg=="0"){--%>
                    <%--$(".sup_error_box").html("你输入的学号或密码有误！").css("color","red");--%>
                <%--}else if(msg=="1"){--%>
                    <%--$(".sup_error_box").html("不登录不能访问系统资源！").css("color","red");--%>
                <%--}else if(msg=="2"){--%>
                    <%--$(".sup_error_box").html("验证码输入有误！").css("color","red");--%>
                <%--}--%>
            <%--}--%>

            $(":image").click(function(){
                var newUrl =$("input[name='admin']:checked").val();
                $("#myform").attr('action',newUrl);
            })
        })

    </script>

</head>
<body>
<div>

<form action="doSup?action=admlogin" method="post" id="myform">
    <div class="sup_error_box">${requestScope.error}</div>
    <div class="login" >

        <img src="static/images/login2.PNG">
    </div>
    <div class="login_button">
        <input type="image" src="static/images/login-botton.PNG">
    </div>
    <div class="f1">
        <input name="userLoginName" type="text" placeholder="请输入昵称" value="xiaohei" required />
    </div>
    <div class="f2">
        <input name="userPassword" type="password" placeholder="请输入密码" value="123435" required/>
    </div>
    <div class="f3" >
        <input  type="radio" name="admin" checked  value="管理员.html"   />管理员
        <input type="radio" name="admin" value="商家.html"  />商家
    </div>
</form>
</div>
</body>
</html>
