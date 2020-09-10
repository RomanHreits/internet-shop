<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        a:link {
            color: green;
            background-color: transparent;
            text-decoration: none;
        }

        a:visited {
            color: pink;
            background-color: transparent;
            text-decoration: none;
        }

        a:hover {
            color: red;
            background-color: transparent;
            text-decoration: underline;
        }

        a:active {
            color: yellow;
            background-color: transparent;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1 style="text-align:center;">Welcome to internet shop!!!</h1>
<h3 style="color: blue">${message}</h3>
<p><a href="${pageContext.request.contextPath}/registration" style="font-size:25px;">User-Registration</a></p>
<p><a href="${pageContext.request.contextPath}/users" style="font-size:25px;">Show-All-Users</a></p>
<p><a href="${pageContext.request.contextPath}/products" style="font-size:25px;">Show-All-Products</a></p>
<p><a href="${pageContext.request.contextPath}/products/add" style="font-size:25px;">Add_Product-To-Storage</a></p>
<p><a href="${pageContext.request.contextPath}/shoppingCart/products" style="font-size:25px;">Go-to-cart</a></p>
</body>
</html>
