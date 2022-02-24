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
    <title>ManagerHome</title>
</head>
<body>
<a href="/logout">Logout</a> <br> <br>
Welcome Manager Homepage <br>
<%
    List<User> users = (List<User>) request.getAttribute("users");
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
%>
<div style="width: 800px">
    <div style="width: 50%; float: left">
        Add User <br> <br>
        <form action="/register" method="post" enctype="multipart/form-data">
            <input type="text" name="name" placeholder="name"><br> <br>
            <input type="text" name="surname" placeholder="surname"><br> <br>
            <input type="email" name="email" placeholder="email"><br> <br>
            <input type="password" name="password" placeholder="password"><br> <br>
            <select name="type">
                <option value="USER">USER</option>
                <option value="MANAGER">MANAGER</option>
            </select> <br> <br>
            <input type="file" name="image"> <br> <br>
            <input type="submit" value="Add">
        </form>
    </div>
    <div style="width: 50%; float: right">
        Add Task <br> <br>
        <form action="/addTask" method="post">
            <input type="text" name="name" placeholder="name"> <br> <br>
            <textarea name="description" placeholder="description"></textarea> <br> <br>
            <select name="status">
                <option value="NEW">NEW</option>
                <option value="IN_PROGRESS">IN_PROGRESS</option>
                <option value="FINISHED">FINISHED</option>
            </select> <br> <br>
            <input type="date" name="deadline"/> <br> <br>
            <select name="user_id">
                <% for (User user : users) { %>
                <option value="<%=user.getId()%>"><%=user.getName()%> <%=user.getSurname()%>
                </option>
                <% } %>
            </select> <br> <br>
            <input type="submit" value="Add">
        </form>
    </div>
</div>
<div>
All Users <br> <br>
<table border="1">
       <tr>
           <th>Name</th>
           <th>Surname</th>
           <th>Email</th>
           <th>Type</th>
           <th>Remove</th>
       </tr>
    <% for (User user : users) { %>
    <tr>
        <td><%= user.getName() %>
        </td>
        <td><%= user.getSurname() %>
        </td>
        <td><%= user.getEmail() %>
        </td>
        <td><%= user.getType().name() %>
        </td>
        <td><a href="/removeUser?id=<%=user.getId()%>">Remove</a>
        </td>
    </tr>
    <% } %>
</table>
</div>
<div> <br> <br>
All Tasks <br> <br>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Status</th>
        <th>Deadline</th>
        <th>User</th>
    </tr>
    <%
        for (Task task : tasks) { %>
    <% if (task.isExpired()) { %>
    <tr style="background-color: darkred">
        <td><%=task.getName()%>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><%=task.getStatus().name()%>
        </td>
        <td><%=task.getDeadline()%>
        </td>
        <td> <%=task.getUser().getName() + " " + task.getUser().getSurname()%>
        </td>
    </tr>
       <% } else { %>
    <tr>
        <td><%=task.getName()%>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><%=task.getStatus().name()%>
        </td>
        <td><%=task.getDeadline()%>
        </td>
        <td> <%=task.getUser().getName() + " " + task.getUser().getSurname()%>
        </td>
    </tr>
    <% } %>
    <% } %>
</table>
</div>
</body>
</html>

