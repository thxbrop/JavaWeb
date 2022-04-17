<%@ page import="com.example.oems.util.CookieUtil" %>
<%@ page import="com.example.oems.util.StringUtil" %>
<%@ page import="com.example.oems.repository.UserRepository" %>
<%@ page import="com.example.oems.entity.User" %>
<%@ page import="com.example.oems.Result" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>个人中心</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <script src="../js/bootstrap.min.js"></script>
        <link rel="icon" type="image/svg+xml" href="https://developer.android.com/images/picto-icons/learn.svg">
    </head>
    <body class="bg-dark bg-gradient">
        <%
            String email = CookieUtil.getCookie(request, "email");
            String password = CookieUtil.getCookie(request, "password");
            Result<User> user = UserRepository.getInstance().getByEmail(email);
        %>
        <header>
            <div class="navbar navbar-light bg-light shadow-sm">
                <div class="container">
                    <a href="#" class="navbar-brand d-flex align-items-center">
                        <strong>个人中心</strong>
                    </a>

                    <div class="align-content-end">
                        <%if (!StringUtil.isNullOrEmpty(email) && !StringUtil.isNullOrEmpty(password)) {%>
                        <button class="btn btn-danger" id="logout" type="button">退出登录</button>
                        <%}%>
                    </div>

                </div>
            </div>
        </header>
        <div class="container">
            <div class="row py-5">
                <div class="col-4">
                    <div class="list-group" id="list-tab" role="tablist">
                        <a class="list-group-item list-group-item-action active" id="list-home-list"
                           data-bs-toggle="list" href="#list-home" role="tab" aria-controls="list-home">资料

                        </a>
                        <a class="list-group-item list-group-item-action" id="list-profile-list" data-bs-toggle="list"
                           href="#list-profile" role="tab" aria-controls="list-profile">消息</a>
                        <a class="list-group-item list-group-item-action" id="list-messages-list" data-bs-toggle="list"
                           href="#list-messages" role="tab" aria-controls="list-messages">考试</a>
                        <a class="list-group-item list-group-item-action" id="list-settings-list" data-bs-toggle="list"
                           href="#list-settings" role="tab" aria-controls="list-settings">其他</a>
                    </div>
                </div>
                <div class="col-8 card">
                    <div class="tab-content bg-body" id="nav-tabContent">
                        <div class="tab-pane fade show active" id="list-home" role="tabpanel"
                             aria-labelledby="list-home-list">
                            <div class="container py-5 row">
                                <div class="col-3">
                                    <img src="../avatars/widget_avatar_7.png" class="rounded thumbnail" alt="...">
                                </div>
                                <div class="col-9">
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="basic-addon1">用户名</span>
                                        <input class="form-control" id="proxy-username" name="username"
                                               value="<%=user.t.getUsername()%>" aria-describedby="basic-addon1">
                                    </div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="basic-addon2">E-mail</span>
                                        <input disabled class="form-control text-muted" id="proxy-email" name="email"
                                               value="<%=user.t.getEmail()%>" aria-describedby="basic-addon2">
                                    </div>

                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="role"
                                               id="flexRadioDefault1" value="<%=User.ROLE_DEFAULT%>"
                                               <%if (user.t != null && user.t.getRole() == User.ROLE_DEFAULT){%>checked<%}%>>
                                        <label class="form-check-label" for="flexRadioDefault1">
                                            普通用户
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="role"
                                               id="flexRadioDefault2" value="<%=User.ROLE_ADMIN%>"
                                               <%if (user.t != null && user.t.getRole() == User.ROLE_ADMIN){%>checked<%}%>>
                                        <label class="form-check-label" for="flexRadioDefault2">
                                            管理员
                                        </label>
                                    </div>
                                    <br>

                                    <div class="row">
                                        <div class="btn-group">
                                            <input type="button" class="btn btn-danger" id="proxy-logoff" value="注销">
                                            <input type="submit" class="btn btn-light" id="proxy-save" value="保存">
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="tab-pane fade" id="list-profile" role="tabpanel"
                             aria-labelledby="list-profile-list">
                            <div class="alert alert-warning mt-2" role="alert">这里还没有内容...</div>
                        </div>
                        <div class="tab-pane fade" id="list-messages" role="tabpanel"
                             aria-labelledby="list-messages-list">
                            <div class="alert alert-warning mt-2" role="alert">这里还没有内容...</div>
                        </div>
                        <div class="tab-pane fade" id="list-settings" role="tabpanel"
                             aria-labelledby="list-settings-list">
                            <div class="alert alert-warning mt-2" role="alert">这里还没有内容...</div>
                        </div>
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

        const toastLiveExample = document.getElementById('liveToast');

        function toast(message, from = "系统消息", time = "Just Now") {
            document.getElementById("toast-body").innerText = message
            document.getElementById("toast-from").innerText = from
            document.getElementById("toast-time").innerText = time
            new bootstrap.Toast(toastLiveExample).show()
        }

        <%if (StringUtil.isNullOrEmpty(email)||StringUtil.isNullOrEmpty(password)){%>
        toast("未登录")
        <%}%>
        $("#logout").click(function () {
            logout()
        })

        $("#proxy-save").click(function () {
            const email = $("#proxy-email").val()
            const username = $("#proxy-username").val()
            const proxy = $("input:radio:checked").val()
            saveProxy(email, username, proxy)
        })
        $("#proxy-logoff").click(function () {
            const email = $("#proxy-email").val()
            logoff(email)
        })

        function saveProxy(email, username, role) {
            $.get(
                "${pageContext.request.contextPath}/profile", {
                    "email": email,
                    "username": username,
                    "role": role
                }, function (data) {
                    if (data === "success") {
                        toast("保存成功")
                    } else {
                        toast(data)
                    }
                }
            )
        }

        function logout() {
            $.get(
                "${pageContext.request.contextPath}/logout",
                function () {
                    location.replace("${pageContext.request.contextPath}/index.jsp")
                }
            )
        }

        function logoff(email) {
            $.get(
                "${pageContext.request.contextPath}/logoff", {
                    "email": email
                },
                function () {
                    logout()
                }
            )
        }
    </script>
</html>
