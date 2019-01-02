<%--
  Created by IntelliJ IDEA.
  User: 20885
  Date: 2018/12/27
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看顾客</title>
    <base href="<%=request.getContextPath() %>/" />
    <%@include file="layUI.jsp"%>
    <style>
        input{
            border-radius: 5px;
            height: 30px;
            font-size: 15px;
            padding-left: 5px;
        }
    </style>
    <script src="/static/js/jquery-2.1.0.js"></script>
    <script>
        $(function () {
            var url = "doSup";
            var parameterData = {action:"showBigClass"}
            //$.post(url,function(){},"json");
            //执行AJax请求并响应内容类型为JSON格式数据
            $.getJSON(url,parameterData,function (bigclasses) {
                //jq中增强for循环
                $.each(bigclasses,function(index,bClass){
                    $("select[name='b_id']").append("<option value='"+bClass.id+"'>"+bClass.bigName+"</option>")
                })
            })
        });
    </script>
</head>
<body>
<form action="doSup?action=unionSmallClass&pageNumber=1" method="post">
    <label>商品名</label>
    <input id="name" type="text" name="likeval" placeholder="请输入商品名称" value="${requestScope.likeval}"/>
    <label>种类</label>
    <select id="b_Id" name="b_id">
        <option value="">请选择</option>
    </select>
    <input class="layui-btn " type="submit"  value="查询"/>
    <input class="layui-btn" type="reset" value="重置">
</form>
<c:choose>
    <c:when test="${not empty sessionScope.page.pageData}">
        <% request.setCharacterEncoding("UTF-8");%>
        <table class="layui-table" width="80%">
            <thead>
            <tr>
                <th>id</th>
                <th>名称</th>
                <th>种类</th>
                <th>描述</th>
                <th colspan="2">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.page.pageData}" var="smallClass">
                <tr>
                    <td>${smallClass.id}</td>
                    <td>${smallClass.smallName}</td>
                    <td>${smallClass.bigclass.bigName}</td>
                    <td>${smallClass.smallText}</td>
                    <td><a class="layui-btn" href="doSup?action=delSClass&id=${smallClass.id}">删除</a></td>
                    <td><a class="layui-btn" href="doSup?action=getSClass&id=${smallClass.id}">修改</a></td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="4">
                    第<b>${sessionScope.page.pageNumber}</b>页&nbsp;&nbsp;
                    <a class="layui-btn" href="doSup?action=unionSmallClass&pageNumber=1&likeval=${requestScope.likeval}&b_id=${requestScope.b_id}">首页</a>
                        <%--&Id=${requestScope.id}&cusName=${requestScope.cusName}">首页</a>--%>
                    <a class="layui-btn" href="doSup?action=unionSmallClass&pageNumber=${sessionScope.page.pageNumber-1>0?page.pageNumber-1:1}&likeval=${requestScope.likeval}&b_id=${requestScope.b_id}">上一页</a>
                    <a class="layui-btn" href="doSup?action=unionSmallClass&pageNumber=${sessionScope.page.pageNumber+1>page.totalPage?page.totalPage:page.pageNumber+1}&likeval=${requestScope.likeval}&b_id=${requestScope.b_id}">下一页</a>
                    <a class="layui-btn" href="doSup?action=unionSmallClass&pageNumber=${sessionScope.page.totalPage}&likeval=${requestScope.likeval}&b_id=${requestScope.b_id}">尾页</a>
                    &nbsp;&nbsp;共<b>${sessionScope.page.totalPage}</b>页 &nbsp;&nbsp;
                    <select id="jumpNumber" class="jumpNumber">
                        <c:forEach begin="1" varStatus="num" end="${sessionScope.page.totalPage}">
                            <option value="${num.count}">-${num.count}页-</option>
                        </c:forEach>
                    </select>
                    &nbsp;&nbsp;
                    <b class="layui-btn" onclick="jump()">跳转</b>

                </td>
            </tr>
            </tfoot>

        </table>
        <script src="../static/js/jquery-1.8.2.min.js"></script>
        <script>
            document.getElementsByClassName("jumpNumber")[${sessionScope.page.pageNumber-1}].selected = true;
            function jump() {
                var number = document.getElementById("jumpNumber").value;
                var name=document.getElementById("name").value;
                var id=document.getElementById("id").value;
                var sex=$("input[type='radio']:checked").val();
                alert(sex);
                location = "doadmin?action=unionSmallClass&pageNumber="+number+"&likeval="+likeval+"&sm_id="+sm_id+"&d_id="+d_id;
            }
        </script>
    </c:when>
    <c:otherwise>
        <h2>暂无数据</h2>
    </c:otherwise>
</c:choose>

</body>
</html>
