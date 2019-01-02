<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<style>
    body {
        background-size: 100%;
    }

    * {
        box-sizing: border-box;
        margin: 0px auto;
        text-align: center;
        font-size: 20px;
    }

    .tx {
        width: 70px;
        height: 70px;
        margin-right: 10px;
        border-radius: 50%

    }

    .zl {
        height: 40px;
        width: 300px;
        margin-bottom: 20px;
        border-radius: 3px;
    }

    .sex {
        height: 20px;
        width: 20px;
        margin-bottom: 20px;
    }
</style>
<div style="border: 1px #d5d5d5 solid ; width: 500px; height: 630px; margin: 0px auto; background-color: #9F9F9F">
    <h3 style="margin:0px auto; font-weight: bold;font-family: 方正舒体;font-size: 50px">修改顾客信息</h3><br/>
    <img src="${customer.cusPhoto}" class="tx"/><br/><br/>
    <form method="post" action="/doSup?action=updataUser&id=${customer.id}">
        <%--<label>编&nbsp;&nbsp;&nbsp;号：</label><input type="text" name="cusId" value="${customer.id}" readonly required--%>
                                                   <%--class="zl"--%>
                                                   <%--style="font-family: 方正姚体; color: red;font-weight: bolder"/><br/>--%>

        <label>姓&nbsp;&nbsp;&nbsp;名：</label><input type="text" name="name" value="${customer.cusName}" required
                                                   class="zl"/><br/>

        登陆名<input type="text" name="loginname" value="${customer.cusLoginName}" readonly required
        style="display: block"/><br/>

        <input type="text" name="pwd" value="${customer.cusPassword}" readonly required
        style="display: none"/><br/>

        <label style="margin-left: -75px;">性&nbsp;&nbsp;&nbsp;别：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input
            type="radio" name="sex"
            value="男" ${cusSex=='男'?'checked':''} class="sex"/>男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="radio" name="sex" value="女" ${cusSex=='女'?'checked':''} class="sex"/>女<br/>

        <label>身份证：</label><input type="text" name="code" value="${customer.cusCode}" required class="zl"/><br/>

        <label>邮&nbsp;&nbsp;&nbsp;箱：</label><input type="text" name="email" value="${customer.cusEmail}" required
                                                   class="zl"/><br/>

        <label>爱&nbsp;&nbsp;&nbsp;好：</label><input type="text" name="hobby" value="${customer.cusHobby}" required
                                                   class="zl"/><br/>


        <label>生&nbsp;&nbsp;&nbsp;日：</label><input type="date" name="birth" value="${customer.cusBirthday}"
                                                   required class="zl"/><br/>

        <input type="submit" value="保&nbsp;&nbsp;存"
               style="width: 120px; height: 45px; margin-left: 40px; background-color: orangered ;border: 0px ;border-radius: 10px; color: white; "/>
    </form>
</div>
</body>
</html>
<%--cusSex--%>