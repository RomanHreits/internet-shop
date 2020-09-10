<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
    <title>Title</title>
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

    h1 {
        color: blue;
    }

    h3 {
        color: red
    }
</style>
<body>
<h1>Enter your login and password !!!</h1>

<h3>${message}</h3>

<form method="post" action="${pageContext.request.contextPath}/registration">
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
</body>
</html>
