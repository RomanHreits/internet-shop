<%@ include file = "../header.jsp"%>
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

    h1 {
        color: blue;
    }

    h3 {
        color: red
    }
</style>
<body>
<h1 style="margin-left: 50px">Enter product name and price!!!</h1>

<form method="post" style="margin: 50px" action="${pageContext.request.contextPath}/products/add">
    <label><b>Name:</b></label><br>
    <input type="text" , name="name" required pattern="[a-zA-Z]+"><br><br>
    <label>Price:</label><br>
    <input type="text" , name="price" required pattern="\d+"><br><br>
    <button type="submit" class="btn btn-primary">Add</button>
</form>
<h2>${message}</h2>
<p style="margin-left: 50px"><a href="${pageContext.request.contextPath}/admin" style="font-size:25px;">Personal-Office</a></p>
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
