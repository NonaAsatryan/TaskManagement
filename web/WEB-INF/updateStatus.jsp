<%@ page import="java.util.List" %>
<%@ page import="taskManagement.model.Task" %><%--
  Created by IntelliJ IDEA.
  User: nona.asatryan
  Date: 15.02.22
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>
<%
    Task task = (Task) request.getAttribute("task");
%>

Update Status:
<form action="/updateStatus" method="post">
    <input type="hidden" name="id" value="<%=task.getId()%>"/> <br>
    name: <input type="text" name="name" value="<%=task.getName()%>"/> <br>
    description: <input type="text" name="description" value="<%=task.getDescription()%>"/> <br>
    user Type: <select name="userType">
    <option value="USER">USER</option>
    <option value="MANAGER">MANAGER</option>
</select> <br>
    taskStatus: <select name="taskStatus">
    <option value="NEW">NEW</option>
    <option value="IN_PROGRESS">IN_PROGRESS</option>
    <option value="FINISHED">FINISHED</option>
</select> <br>
    deadline: <input type="date" name="deadline"/> <br> <br>
    <input type="submit" value="Update">
</form>
</body>
</html>
