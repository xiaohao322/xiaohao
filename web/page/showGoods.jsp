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
            var parameterData = {action:"showSmallClass"}
            //$.post(url,function(){},"json");
            //执行AJax请求并响应内容类型为JSON格式数据
            $.getJSON(url,parameterData,function (smallclasses) {
                //jq中增强for循环
                $.each(smallclasses,function(index,sClass){
                    $("select[name='sm_id']").append("<option value='"+sClass.id+"'>"+sClass.smallName+"</option>")
                })
            })
        });
    </script>
    <script>
        $(function () {
            var url = "doSup";
            var parameterData = {action:"showDesc"}
            //$.post(url,function(){},"json");
            //执行AJax请求并响应内容类型为JSON格式数据
            $.getJSON(url,parameterData,function (dList) {
                //jq中增强for循环
                $.each(dList,function(index,desc){
                    $("select[name='d_id']").append("<option value='"+desc.id+"'>"+desc.discRate+"</option>")
                })
            })
        });
    </script>
</head>
<body>
<form action="doSup?action=unionQgoods&pageNumber=1" method="post">
    <label>商品名</label>
    <input id="name" type="text" name="likeval" placeholder="请输入商品名称" value="${requestScope.likeval}"/>
    <label>所属小分类</label>
    <select id="s_Id" name="sm_id">
        <option value="">请选择</option>
    </select>
    <label>折扣</label>
    <select id="d_id" name="d_id">
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
                <th>单价</th>
                <th>数量</th>
                <th>运费</th>
                <th>折扣</th>
                <th colspan="2">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.page.pageData}" var="good">
                <tr>
                    <td>${good.id}</td>
                    <td>${good.goodsName}</td>
                    <td>${good.smallclass.smallName}</td>
                    <td>${good.goodsMoney}</td>
                    <td>${good.goodsNumber}</td>
                    <td>${good.goodsCarriage}</td>
                    <td>${good.discount.discRate==1?"无折扣":good.discount.discRate}</td>
                    <td><a class="layui-btn" href="doSup?action=delGood&id=${good.id}">删除</a></td>
                    <td><a class="layui-btn" href="doSup?action=getGood&id=${good.id}">修改</a></td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="4">
                    第<b>${sessionScope.page.pageNumber}</b>页&nbsp;&nbsp;
                    <a class="layui-btn" href="doSup?action=unionQgoods&pageNumber=1&likeval=${requestScope.likeval}&sm_id=${requestScope.sm_id}&d_id=${requestScope.d_id}">首页</a>
                        <%--&Id=${requestScope.id}&cusName=${requestScope.cusName}">首页</a>--%>
                    <a class="layui-btn" href="doSup?action=unionQgoods&pageNumber=${sessionScope.page.pageNumber-1>0?page.pageNumber-1:1}&likeval=${requestScope.likeval}&sm_id=${requestScope.sm_id}&d_id=${requestScope.d_id}">上一页</a>
                    <a class="layui-btn" href="doSup?action=unionQgoods&pageNumber=${sessionScope.page.pageNumber+1>page.totalPage?page.totalPage:page.pageNumber+1}&likeval=${requestScope.likeval}&sm_id=${requestScope.sm_id}&d_id=${requestScope.d_id}">下一页</a>
                    <a class="layui-btn" href="doSup?action=unionQgoods&pageNumber=${sessionScope.page.totalPage}&likeval=${requestScope.likeval}&sm_id=${requestScope.sm_id}&d_id=${requestScope.d_id}">尾页</a>
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
                location = "doSup?action=unionQgoods&pageNumber="+number+"&likeval="+likeval+"&sm_id="+sm_id+"&d_id="+d_id;
            }
        </script>
    </c:when>
    <c:otherwise>
        <h2>暂无数据</h2>
    </c:otherwise>
</c:choose>

</body>
</html>
