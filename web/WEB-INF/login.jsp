<%--
  Created by IntelliJ IDEA.
  User: nona.asatryan
  Date: 15.02.22
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>User Page</title>
  </head>
  <body>
  <form action="/login" method="post">
    email: <input type="email" name="email"/> <br>
    password: <input type="password" name="password"/> <br> <br>
    <input type="submit" value="OK">
  </form>
  </body>
</html>
