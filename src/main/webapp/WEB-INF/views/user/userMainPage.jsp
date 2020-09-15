<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
    <title>Title</title>
</head>
<style>
    body {
        margin: 50px;
    }
</style>
<body>
<div class="container p-3 my-3 bg-dark text-white">
    <h1>Welcome to your page!</h1>
</div>
<p><a href="${pageContext.request.contextPath}/products" style="font-size:25px;">Products</a></p>
<p><a href="${pageContext.request.contextPath}/shoppingCart/products" style="font-size:25px;">Shopping-Cart</a></p>
<p><a href="${pageContext.request.contextPath}/user/orders" style="font-size:25px;">User-Orders</a></p>
<p><a href="${pageContext.request.contextPath}/" style="font-size:25px;">Sing-Out</a></p>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>