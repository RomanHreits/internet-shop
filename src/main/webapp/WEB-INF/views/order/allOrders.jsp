<%@ include file = "../header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
    <style>
        h1 {
            font-size: xx-large
        }
    </style>
    <title>Title</title>
</head>
<style>
    body {
        margin: 50px;
    }
</style>
<body>
<h1>Orders list:</h1>
<div class="container" style="margin-left: 10%">
    <table class="table table-dark table-hover" border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Products</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>
                    <c:out value="${order.id}"/>
                </td>
                <td>
                    <c:out value="${order.date}"/>
                </td>
                <td>
                    <c:out value="${order.products}"/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/orders/delete?id=${order.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<p><a href="${pageContext.request.contextPath}/admin" style="font-size:25px;">Personal-Office</a></p>
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
