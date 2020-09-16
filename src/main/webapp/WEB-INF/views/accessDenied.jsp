<%@ include file = "header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        h1 {
            color: red;
            text-align: center;
        }
    </style>
    <title>Title</title>
</head>
<body>
<h1>Sorry! Access to this page is denied!</h1>
<br>
<a href="${pageContext.request.contextPath}/" class="btn btn-success btn-lg" role="button">MainPage</a>
</body>
</html>
