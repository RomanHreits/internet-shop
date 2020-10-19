<%@ include file = "../header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
</head>
<style>
    body {
        margin: 50px;
    }
</style>
<body>
<h1>All users list: </h1>
<h4 style="color: red">${message}</h4>
<div class="container" style="margin-left: 10%">
    <table class="table table-dark table-hover" border="1">
        <thead>
        <tr>
            <th>Id</th>
            <th>Login</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <c:out value="${user.id}"/>
                </td>
                <td>
                    <c:out value="${user.login}"/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/users/delete?id=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<p><a href="${pageContext.request.contextPath}/admin" style="font-size:25px;">Personal-Office</a></p>
</body>
</html>
