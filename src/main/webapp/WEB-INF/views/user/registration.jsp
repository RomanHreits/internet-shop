<%@ include file = "../header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<style>
    input:invalid {
        border: 2px dashed red;
    }

    input:valid {
        border: 2px solid black;
    }

    body {
        background-color: white;
        margin: 50px;
    }

    h2 {
        color: black;
    }

    h3 {
        color: red
    }
</style>
<body>
<h2>Enter your login and password for register!!!</h2>
<h3>${message}</h3>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <label><b>Name:</b></label><br>
    <input type="text" , name="name" , value="${currentName}" required><br>
    <label><b>Login:</b></label><br>
    <input type="text" , name="login" , value="${currentLogin}" required><br>
    <label>Password:</label><br>
    <input type="password" , name="password" required><br><br>
    <label>Repeat password:</label><br>
    <input type="password" , name="repeatPwd" required><br><br>
    <button type="submit" class="btn btn-primary">Register</button>
</form>
<br>
<p><a href="${pageContext.request.contextPath}/" style="font-size:25px;">Back-to-Main-Page</a></p>
</body>
</html>
