<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.09.2020
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    input:invalid {
        border: 2px dashed red;
    }

    input:valid {
        border: 2px solid black;
    }
    body {background-color: white;}
    h1 {color: blue;}
    h3 {color: red}
</style>
<body>
<h1>Enter your login and password !!!</h1>

<h3>${message}</h3>

<form method="post" action="${pageContext.request.contextPath}/registration">
    <label><b>Login:</b></label><br>
    <input type="text", name="login", value="${currentLogin}" required><br>
    <label>Password:</label><br>
    <input type="password", name="password" required><br><br>
    <label>Repeat password:</label><br>
    <input type="password", name="repeatPwd" required><br><br>
    <input type="submit" value="Register">
</form>
</body>
</html>
