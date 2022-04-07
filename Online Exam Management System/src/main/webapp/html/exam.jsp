<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.oems.entity.Task" %><%--
  Created by IntelliJ IDEA.
  User: qq277
  Date: 2022/4/7
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>考试系统</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <script src="../js/bootstrap.min.js"></script>
        <link rel="icon" type="image/svg+xml" href="https://developer.android.com/images/picto-icons/learn.svg">
    </head>

    <body class="bg-dark bg-gradient">
        <header>
            <div class="navbar navbar-light bg-light shadow-sm">
                <div class="container">
                    <div class="align-content-end">
                        <button class="btn btn-primary" id="btn-random">随机出题</button>
                    </div>
                </div>
            </div>
        </header>

        <div class="container py-5 col-6">
            <c:forEach var="task" items="${sessionScope.tasks}">
                <div class="card">
                    <p class="card-header">
                        <c:out value="${task.getDescription()}"/>
                    </p>
                    <div class="card-body">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="task-${task.getId()}" id="selection-a">
                            <label class="form-check-label" for="selection-a">
                                <c:out value="${task.getSelections().a}"/>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="task-${task.getId()}" id="selection-b">
                            <label class="form-check-label" for="selection-b">
                                <c:out value="${task.getSelections().b}"/>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="task-${task.getId()}" id="selection-c">
                            <label class="form-check-label" for="selection-c">
                                <c:out value="${task.getSelections().c}"/>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="task-${task.getId()}" id="selection-d">
                            <label class="form-check-label" for="selection-d">
                                <c:out value="${task.getSelections().d}"/>
                            </label>
                        </div>
                    </div>
                </div>
                <br>
            </c:forEach>
            <div class="container">
                <div class="alert alert-danger" role="alert">
                    提交功能还未完成
                </div>
            </div>
            <div class="d-grid gap-2">
                <button class="btn btn-primary" disabled>提交</button>
            </div>
        </div>

        <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
            <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
                <div class="toast-header">
                    <strong class="me-auto" id="toast-from">系统消息</strong>
                    <small id="toast-time">Just Now</small>
                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
                <div class="toast-body" id="toast-body">
                    Hello, world! This is a toast message.
                </div>
            </div>
        </div>
    </body>
    <script>
        const toastLiveExample = document.getElementById('liveToast');

        function toast(message, from = "系统消息", time = "Just Now") {
            document.getElementById("toast-body").innerText = message
            document.getElementById("toast-from").innerText = from
            document.getElementById("toast-time").innerText = time
            new bootstrap.Toast(toastLiveExample).show()
        }

        $("#btn-random").click(function () {
            $.get(
                "${pageContext.request.contextPath}/task/random", {
                    "limit": 5
                }, function (data) {
                    if (data === "success") {
                        location.reload()
                    } else {
                        toast(data)
                    }
                }
            )
        })
    </script>
</html>
