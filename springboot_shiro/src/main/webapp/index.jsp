<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>jsp index</title>
</head>
<body>
<h1>index界面</h1>
<a href="${pageContext.request.contextPath}/user/logout">退出</a>
<shiro:principal/>

<shiro:authenticated>
    认证之后展示的内容
</shiro:authenticated>
<shiro:notAuthenticated>
    没有认证之后展示的内容
</shiro:notAuthenticated>
<%--user或admin组都可以看到--%>
<shiro:hasRole name="admin">
    <li><a href="">商品管理</a></li>
    <li><a href="">订单管理</a></li>
</shiro:hasRole>

<%--只有admin可以看到--%>
<shiro:hasAnyRoles name="admin,user">
    <li><a href="">用户管理</a>
        <ul>
            <shiro:hasPermission name="user:add:*">
                <li><a href="">添加</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:del:*">
                <li><a href="">删除</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="order:find:*">
                <li><a href="">查询</a></li>
            </shiro:hasPermission>
        </ul>
    </li>
    <li><a href="">物流管理</a></li>
</shiro:hasAnyRoles>

</body>
</html>