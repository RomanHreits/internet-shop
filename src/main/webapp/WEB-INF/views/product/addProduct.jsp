<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    body {background-color: white;}
    h1 {color: blue;}
    h3 {color: red}
</style>
<body>
<h1>Enter product name and price!!!</h1>

<form method="post" action="${pageContext.request.contextPath}/products/add">
    <label><b>Name:</b></label><br>
    <input type="text", name="name"><br><br>
    <label>Price:</label><br>
    <input type="text", name="price"><br><br>
    <input type="submit" value="Add">
</form>
</body>
</html>

