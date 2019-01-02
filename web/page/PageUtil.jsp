<%@ page contentType="text/html;charset=UTF-8" language="java" %>
第<b>${sessionScope.Page.pageNumber}</b>页&nbsp;&nbsp;
<a class="layui-btn" href="${requestScope.ServletUrl}&pageNumber=1">首页</a>
<a class="layui-btn" href="${requestScope.ServletUrl}&pageNumber=${sessionScope.Page.pageNumber-1>0?Page.pageNumber-1:1}">上一页</a>
<a class="layui-btn" href="${requestScope.ServletUrl}&pageNumber=${sessionScope.Page.pageNumber+1>Page.totalPage?Page.totalPage:Page.pageNumber+1}">下一页</a>
<a class="layui-btn" href="${requestScope.ServletUrl}&pageNumber=${sessionScope.Page.totalPage}">尾页</a>
&nbsp;&nbsp;共<b>${sessionScope.Page.totalPage}</b>页 &nbsp;&nbsp;

<select id="jumpNumber">
    <c:forEach begin="1" varStatus="num" end="${sessionScope.Page.totalPage}">
        <option name="option1" value="${num.count}">-${num.count}页-</option>
    </c:forEach>
</select>
&nbsp;&nbsp;
<b class="layui-btn" onclick="jump()">跳转</b>
<script>
    document.getElementsByName("option1")[${sessionScope.Page.pageNumber-1}].selected = true;
    function jump() {
        var number = document.getElementById("jumpNumber").value;
        //open("doTea?action=queryAll&pageNumber="+number,"iframe_context");
        location = "${requestScope.ServletUrl}&pageNumber="+number;
    }
</script>
