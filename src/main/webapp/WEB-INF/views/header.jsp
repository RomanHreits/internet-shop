<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
    <title>Title</title>
    <style>
        body {
            font-family: 'Lora', serif;
            font-weight: 500;
        }
    </style>
</head>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <h1 style="color: white">Welcome to internet shop!!!</h1>
    <div class="btn-group">
        <button type="button" class="btn btn-info btn-lg dropdown-toggle" style="margin-left: 30%" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            AdminActions
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/products/add">Add_Product-To-Storage</a>
            <a class="dropdown-item" href="${pageContext.request.contextPath}/users">Show-All-Users</a>
            <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/products">Show-All-Products-In-Storage</a>
            <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/orders">Show-All-Orders-In-Storage</a>
        </div>
    </div>
    <div class="btn-group">
    <button type="button" class="btn btn-danger btn-lg dropdown-toggle" style="margin-left: 60px" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        UserActions
    </button>
    <div class="dropdown-menu">
        <a class="dropdown-item" href="${pageContext.request.contextPath}/products">BuyProducts</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/shoppingCart/products">Shopping-Cart</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/user/orders">User-Orders</a>
    </div>
    </div>
    <a style="margin-left: 5%" href="${pageContext.request.contextPath}/login" class="btn btn-success btn-lg" role="button">SingIn</a>
    <a style="margin-left: 5%" href="${pageContext.request.contextPath}/logout" class="btn btn-success btn-lg" role="button">LogOut</a>
    <a style="margin-left: 5%" href="${pageContext.request.contextPath}/inject" class="btn btn-success btn-lg" role="button">InjectData</a>
</nav>
<body>
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
