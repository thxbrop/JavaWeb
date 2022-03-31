<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
        <a href="hello-servlet">Hello Servlet</a>
        <c:forEach var="student" items="${requestScope.map}">
            <c:out value="${student}"/>
        </c:forEach>
    </body>
</html>