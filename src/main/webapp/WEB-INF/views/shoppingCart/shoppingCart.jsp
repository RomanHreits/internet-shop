<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        h1 {
            font-size: xx-large
        }
    </style>
    <title>Title</title>
</head>
<body>
<h1>Shopping cart of customer:</h1>
<table border="1" style="width: 20%">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.id}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/products/delete?id=${product.id}
                &name=${product.name}&price=${product.price}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
