<%@ page import="taskManagement.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="taskManagement.model.Task" %><%--
  Created by IntelliJ IDEA.
  User: nona.asatryan
  Date: 16.02.22
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserHome</title>
</head>
<body>
<a href="/logout">Logout</a> <br> <br>
<%
    User user = (User) session.getAttribute("user");
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
%>
Welcome <%=user.getName()%> <% if(user.getPictureUrl() != null) { %>
<img src="/image?path=<%=user.getPictureUrl()%>" width="50"/> <%}%> <br> <br>

<div>
All Tasks <br> <br>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Status</th>
        <th>Deadline</th>
        <th>User</th>
        <th>Update Status</th>
    </tr>

    <% for (Task task : tasks) { %>
    <tr>
        <td><%= task.getName() %>
        </td>
        <td><%= task.getDescription() %>
        </td>
        <td><%= task.getStatus() %>
        </td>
        <td><%= task.getDeadline() %>
        </td>
        <td><%= task.getUser().getName() + " " + task.getUser().getSurname() %>
        </td>
        <td>
            <form action="/updateTaskStatus" method="post">
                <input type="hidden" name="taskId" value="<%=task.getId()%>">
                <select name="status">
                    <option value="NEW">NEW</option>
                    <option value="IN_PROGRESS">IN_PROGRESS</option>
                    <option value="FINISHED">FINISHED</option>
                </select> <br> <br>
                <input type="submit" value="Update">
            </form>
        </td>
    </tr>
    <% } %>
</table>
</div>
</body>
</html>
