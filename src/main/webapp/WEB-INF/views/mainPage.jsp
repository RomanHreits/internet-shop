<%@ include file = "header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<div class="container">
    <br>
    <br>
    <h3>Please choose your role:</h3>
    <br>
    <div class="btn-group btn-group-lg">
        <a href="${pageContext.request.contextPath}/user" class="btn btn-info" role="button">Consumer</a>
        <a href="${pageContext.request.contextPath}/admin" class="btn btn-info" role="button">Admin</a>
    </div>
</div>
<body>
</body>
</html>
