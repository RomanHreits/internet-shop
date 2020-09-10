<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    input:invalid {
        border: 2px dashed red;
    }

    input:valid {
        border: 2px solid black;
    }
    body {background-color: white;}
    h1 {color: blue;}
    h3 {color: red}
</style>
<body>
<h1>Enter product name and price!!!</h1>

<form method="post" action="${pageContext.request.contextPath}/products/add">
    <label><b>Name:</b></label><br>
    <input type="text", name="name" required pattern="[a-zA-Z]+"><br><br>
    <label>Price:</label><br>
    <input type="text", name="price" required pattern="\d+"><br><br>
    <input type="submit" value="Add">
</form>
<h2>${message}</h2>
<p><a href="${pageContext.request.contextPath}/" style="font-size:25px;">Main-Page</a></p>
</body>
</html>

