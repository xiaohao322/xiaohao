<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/18
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/"/>
    <%@include file="layUI.jsp"%>
    <style>
        input{
            border-radius: 5px;
            height: 30px;
            font-size: 15px;
            padding-left: 5px;
        }
    </style>
</head>
<body>

<c:choose>
    <c:when test="${not empty sessionScope.page.pageData}">
        <% request.setCharacterEncoding("UTF-8");%>
        <table class="layui-table" width="80%">
            <thead>
            <tr>
                <th>id</th>
                <th>分类名称</th>
                <th>描述</th>
                <th colspan="2">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.page.pageData}" var="big">
                <tr>
                    <td>${big.id}</td>
                    <td>${big.bigName}</td>
                    <td>${big.bigText}</td>
                    <td><a class="layui-btn" href="doSup?action=delSClass&id=${smallClass.id}">删除</a></td>
                    <td><a class="layui-btn" href="doSup?action=getSClass&id=${smallClass.id}">修改</a></td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="4">
                    第<b>${sessionScope.page.pageNumber}</b>页&nbsp;&nbsp;
                    <a class="layui-btn" href="doSup?action=queryAllBigClass&pageNumber=1">首页</a>
                        <%--&Id=${requestScope.id}&cusName=${requestScope.cusName}">首页</a>--%>
                    <a class="layui-btn" href="doSup?action=queryAllBigClass&pageNumber=${sessionScope.page.pageNumber-1>0?page.pageNumber-1:1}">上一页</a>
                    <a class="layui-btn" href="doSup?action=queryAllBigClass&pageNumber=${sessionScope.page.pageNumber+1>page.totalPage?page.totalPage:page.pageNumber+1}}">下一页</a>
                    <a class="layui-btn" href="doSup?action=queryAllBigClass&pageNumber=${sessionScope.page.totalPage}">尾页</a>
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
                alert(number);
                location = "doadmin?action=queryAllBigClass&pageNumber="+number;
            }
        </script>
    </c:when>
    <c:otherwise>
        <h2>暂无数据</h2>
    </c:otherwise>
</c:choose>


</body>
</html>
