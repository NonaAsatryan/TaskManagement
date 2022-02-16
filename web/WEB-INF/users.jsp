<%@ page import="taskManagement.model.Task" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: nona.asatryan
  Date: 16.02.22
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Home Page</title>
</head>
<body>
<%
    List<Task> allTasks = (List<Task>)request.getAttribute("allTasks");
%>

My tasks:
<table border="1">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>description</th>
        <th>user_id</th>
        <th>status</th>
        <th>deadline</th>

    </tr>
    </thead>
    <%
        for (Task task : allTasks) { %>
    <tr>
        <td><%=task.getId()%></td>
        <td><%=task.getName()%></td>
        <td><%=task.getDescription()%></td>
        <td><%=task.getUser().getId()%></td>
        <td><%=task.getStatus().name()%></td>
        <td><%=task.getDeadline()%></td>
        <td><a href="/updateStatus?id=<%=task.getId()%>">Update</a></td>
    </tr>
    <% }
    %>
</table>

</body>
</html>
