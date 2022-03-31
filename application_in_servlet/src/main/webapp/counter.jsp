<%--
  Created by IntelliJ IDEA.
  User: qq277
  Date: 2022/3/27
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Counter</title>
    </head>
    <body>
        <%
            Integer count = (Integer) application.getAttribute("count");
            response.getWriter().append("<h1>访问量：").append(String.valueOf(count)).append("</h1>");
        %>
    </body>
</html>
