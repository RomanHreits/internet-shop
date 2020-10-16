<%@ include file = "header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div class="btn-group">
    <button type="button" class="btn btn-info btn-lg dropdown-toggle" style="margin-left: 30%"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        AdminActions
    </button>
    <div class="dropdown-menu">
        <a class="dropdown-item" href="${pageContext.request.contextPath}/products/add">Add_Product-To-Storage</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/users">Show-All-Users</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/products">Show-All-Products-In-Storage</a>
        <a class="dropdown-item"
           href="${pageContext.request.contextPath}/admin/orders">Show-All-Orders-In-Storage</a>
    </div>
</div>
<div class="btn-group">
    <button type="button" class="btn btn-danger btn-lg dropdown-toggle" style="margin-left: 60px"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        UserActions
    </button>
    <div class="dropdown-menu">
        <a class="dropdown-item" href="${pageContext.request.contextPath}/user">UserPage</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/products">BuyProducts</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/shoppingCart/products">Shopping-Cart</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/user/orders">User-Orders</a>
    </div>
</div>
</body>
</html>
