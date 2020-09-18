<%@ include file = "header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        h1 {
            color: red;
            text-align: center;
            font-family: 'Lora', serif;
        }
    </style>
    <title>Title</title>
</head>
<body>
<h1> Servlet Name - "${servletName}"</h1>
<h1> Exception Name - "${exception}"</h1>
<h1> Exception Message - "${errorMessage}"</h1>
<h1> Requested URI - "${requestUri}"</h1>
<a href="${pageContext.request.contextPath}/" class="btn btn-success btn-lg" role="button">MainPage</a>
</body>
</html>
