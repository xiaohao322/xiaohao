<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath() %>/" />
    <link rel="stylesheet" href="static/layui/css/layui.css">
    <script src="static/layui/layui.js"></script>
    <style>
        .personWarp{
            height: 400px;
            width: 75%;
            overflow: hidden;
            margin: auto;
            background-color: #ffffff;
            padding-left:25%;
            font-size: 16px;
            color: #696969;
        }

        .personWarp input{
            margin-top: 20px;
        }
        input[type='text']{
            border-radius: 5px;
            height: 40px;
            font-size: 15px;
            padding-left: 10px;
        }
        input[name='stuId']{
            background-color: #999999;
        }
        input[type='submit']{
            width: 225px;
        }
    </style>
</head>
<body>
<div class="personWarp">
    <form action="doSup?action=Updateadm&id=${requestScope.superuser.id}" method="post">
        <label>管理员名称:</label>
        <input type="text" name="admName" value="${requestScope.superuser.userName}" /><br>
        <label>密码:</label>
        <input type="text" name="admPwd" value="${requestScope.superuser.userPassword}" /><br>
        <label>身份证:</label>
        <input type="text" name="admid" value="${requestScope.superuser.userId}" /><br>
        <label>账号:</label>
        <input type="text" name="admLoginName" value="${requestScope.superuser.userLoginName}" /><br>
        <input class="layui-btn layui-btn-lg" type="submit"  value="保存资料"/>
    </form>
</div>

</body>
</html>