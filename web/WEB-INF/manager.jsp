<%--
  Created by IntelliJ IDEA.
  User: nona.asatryan
  Date: 16.02.22
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Home Page</title>
</head>
<body>
Add User:
<form action="/manager" method="post">
    name: <input type="text" name="name"/> <br>
    surname: <input type="text" name="surname"/> <br>
    email: <input type="email" name="email"/> <br>
    password: <input type="password" name="password"/> <br> <br>
    <input type="submit" value="OK">
</form>

Add Task:
<form action="/manager" method="post">
    name: <input type="text" name="name"/> <br>
    description: <input type="text" name="surname"/> <br>
    user Type: <select name="userType">
    <option value="USER">USER</option>
    <option value="MANAGER">MANAGER</option>
</select> <br>
    taskStatus: <select name="taskStatus">
    <option value="NEW">NEW</option>
    <option value="IN_PROGRESS">IN_PROGRESS</option>
    <option value="FINISHED">FINISHED</option>
</select> <br>
    deadline: <input type="date" name="deadline"/> <br>
    <input type="submit" value="OK">
</form>
</body>
</html>
