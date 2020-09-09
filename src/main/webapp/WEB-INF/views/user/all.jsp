<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>All users page! </h1>
<table border="1" style="width: 20%">
    <tr>
        <th>UserId</th>
        <th>UserName</th>
    </tr>
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
</table>
<p><a href="${pageContext.request.contextPath}/" style="font-size:25px;">Main-Page</a></p>
</body>
</html>
