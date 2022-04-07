<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.oems.util.CookieUtil" %>
<%@ page import="com.example.oems.repository.UserRepository" %>
<%@ page import="com.example.oems.entity.User" %>
<%@ page import="com.example.oems.Result" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>线上考试管理系统</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="icon" type="image/svg+xml" href="https://developer.android.com/images/picto-icons/learn.svg">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
    </head>

    <%
        String email = CookieUtil.getCookie(request, "email");
        String password = CookieUtil.getCookie(request, "password");
        Result<User> result = UserRepository.getInstance().getByEmail(email);
    %>

    <body class="bg-dark">
        <header class="navbar navbar-expand-lg navbar-light bg-light shadow-sm">
            <div class="container">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">主页</a>
                    </li>
                    <%if (result.t != null && result.t.getRole() == User.ROLE_DEFAULT) {%>
                    <li class="nav-item">
                        <a class="nav-link" href="html/exam.jsp">考试系统</a>
                    </li>
                    <%} else if (result.t != null && result.t.getRole() == User.ROLE_ADMIN) {%>
                    <li class="nav-item">
                        <a class="nav-link" href="html/manage.jsp">管理系统</a>
                    </li>
                    <%}%>
                </ul>
                <div class="align-content-end">
                    <%if (result.t != null && result.t.getPassword().equals(password)) {%>
                    <a class="nav-link" href="${pageContext.request.contextPath}/html/proxy.jsp">个人中心</a>
                    <%} else {%>
                    <button class="btn btn-primary" id="register" type="button" data-bs-target="#model-register"
                            data-bs-toggle="modal">注册
                    </button>
                    <button class="btn btn-secondary" id="login" type="button" data-bs-target="#model-login"
                            data-bs-toggle="modal">登录
                    </button>
                    <%}%>
                </div>
            </div>
        </header>

        <div class="modal fade" id="model-register" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">注册</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="input-register-email" class="form-label">电子邮箱地址</label>
                            <input class="form-control" type="email" name="email" id="input-register-email"
                                   placeholder="name@example.com">
                        </div>
                        <div class="mb-3">
                            <label for="input-register-username" class="form-label">用户名</label>
                            <input class="form-control" type="text" name="username" id="input-register-username"
                                   placeholder="可选">
                        </div>
                        <div class="mb-3">
                            <label for="input-register-password" class="form-label">密码</label>
                            <input class="form-control" type="password" name="password"
                                   id="input-register-password">
                        </div>
                        <div id="alert-register"></div>
                    </div>
                    <div class="modal-footer btn-group">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" id="btn-register">提交</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="model-login" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabelLogin">登录</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="input-login-email" class="form-label">电子邮箱地址</label>
                            <input class="form-control" type="email" name="email" id="input-login-email"
                                   placeholder="name@example.com">
                        </div>
                        <div class="mb-3">
                            <label for="input-login-password" class="form-label">密码</label>
                            <input class="form-control" type="password" name="password"
                                   id="input-login-password">
                        </div>
                        <div id="alert-login"></div>
                    </div>
                    <div class="modal-footer btn-group">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" id="btn-login">提交</button>
                    </div>
                </div>
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
        const alertPlaceholderRegister = document.getElementById('alert-register');
        const alertPlaceholderLogin = document.getElementById('alert-login');

        function alertInRegister(message, type) {
            const wrapper = document.createElement('div');
            wrapper.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible" role="alert">' + message + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'

            alertPlaceholderRegister.append(wrapper)
        }

        function alertInLogin(message, type) {
            const wrapper = document.createElement('div');
            wrapper.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible" role="alert">' + message + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'

            alertPlaceholderLogin.append(wrapper)
        }

        $("#btn-register").click(function () {
            $.get(
                "${pageContext.request.contextPath}/register", {
                    "email": $("#input-register-email").val(),
                    "username": $("#input-register-username").val(),
                    "password": $("#input-register-password").val(),
                }, function (data) {
                    if (data === "success") {
                        alertInRegister("注册成功", "success")
                    } else if (data != null) {
                        alertInRegister(data, "warning")
                    }
                }
            )
        })

        $("#btn-login").click(function () {
            $.get(
                "${pageContext.request.contextPath}/login", {
                    "email": $("#input-login-email").val(),
                    "password": $("#input-login-password").val(),
                }, function (data) {
                    if (data === "success") {
                        location.reload()
                    } else if (data != null) {
                        alertInLogin(data, "warning")
                    }
                }
            )
        })

        const toastLiveExample = document.getElementById('liveToast');

        function toast(message, from = "系统消息", time = "Just Now") {
            document.getElementById("toast-body").innerText = message
            document.getElementById("toast-from").innerText = from
            document.getElementById("toast-time").innerText = time
            new bootstrap.Toast(toastLiveExample).show()
        }

        toast("欢迎来到线上考试管理系统")

    </script>
</html>