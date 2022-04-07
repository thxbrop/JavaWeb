<%@ page import="com.example.oems.repository.UserRepository" %>
<%@ page import="com.example.oems.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: qq277
  Date: 2022/4/6
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>管理系统</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="icon" type="image/svg+xml" href="https://developer.android.com/images/picto-icons/learn.svg">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <script src="../js/bootstrap.min.js"></script>
    </head>
    <body class="bg-dark bg-gradient">

        <div class="container py-5 col-6">
            <div class="row">
                <div class="col-4">
                    <div class="list-group" id="list-tab" role="tablist">
                        <a class="list-group-item list-group-item-action active" id="list-home-list"
                           data-bs-toggle="list" href="#list-home" role="tab" aria-controls="list-home">用户管理</a>
                        <a class="list-group-item list-group-item-action" id="list-profile-list" data-bs-toggle="list"
                           href="#list-profile" role="tab" aria-controls="list-profile">试题管理</a>
                    </div>
                </div>
                <div class="col-8">
                    <div class="tab-content" id="nav-tabContent">
                        <div class="tab-pane fade show active" id="list-home" role="tabpanel"
                             aria-labelledby="list-home-list">
                            <table class="table table-bordered table-light table-hover">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Username</th>
                                    <th scope="col">Password</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    UserRepository repository = UserRepository.getInstance();
                                    List<User> list = repository.getAll();
                                    for (User user : list) {
                                %>
                                <tr>
                                    <td scope="row"><%=user.getId()%>
                                    </td>
                                    <td><%=user.getEmail()%>
                                    </td>
                                    <td><%=user.getUsername()%>
                                    </td>
                                    <td><%=user.getPassword()%>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>

                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id="list-profile" role="tabpanel"
                             aria-labelledby="list-profile-list">
                            <div class="card p-2">
                                <%@ include file="manage_task.jsp" %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
