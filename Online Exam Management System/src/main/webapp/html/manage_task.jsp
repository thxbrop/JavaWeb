<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qq277
  Date: 2022/4/7
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title></title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="icon" type="image/svg+xml" href="https://developer.android.com/images/picto-icons/learn.svg">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <script src="../js/bootstrap.min.js"></script>
    </head>
    <body>
        <table class="table table-bordered table-light table-hover">
            <thead>
            <tr class="text-center">
                <th scope="col">#</th>
                <th scope="col">试题描述</th>
                <th scope="col">操作</th>
            </tr>
            </thead>
            <tbody>
            <div class="alert alert-danger" role="alert">
                分页展示题库试题功能还未完成
            </div>
            <c:forEach items="${sessionScope.tasks}" var="task">
                <tr>
                    <td scope="row" class="text-center"><c:out value="${task.getId()}"/></td>
                    <td><c:out value="${task.getDescription()}"/></td>
                    <td class="d-grid gap-2">
                        <button class="btn btn-danger text-center">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-trash-fill" viewBox="0 0 16 16">
                                <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"></path>
                            </svg>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-end">
                <li class="page-item">
                    <button class="page-link" href="#">Previous</button>
                </li>
                <li class="page-item active">
                    <button id="s-1" class="page-link" href="#">1</button>
                </li>
                <li class="page-item">
                    <button id="s-2" class="page-link" href="#">2</button>
                </li>
                <li class="page-item">
                    <button id="s-3" class="page-link" href="#">3</button>
                </li>
                <li class="page-item">
                    <button id="s-4" class="page-link" href="#">Next</button>
                </li>
            </ul>
        </nav>
        <button class="btn btn-primary" id="create" type="button" data-bs-target="#model-create"
                data-bs-toggle="modal">新建
        </button>
        <div class="modal fade" id="model-create" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">新建试题</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="input-create-description" class="form-label">试题描述</label>
                            <textarea class="form-control" id="input-create-description" rows="3"></textarea>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">A</span>
                            <input class="form-control" type="text" name="a" id="input-create-a">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon2">B</span>
                            <input class="form-control" type="text" name="a" id="input-create-b">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon3">C</span>
                            <input class="form-control" type="text" name="a" id="input-create-c">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon4">D</span>
                            <input class="form-control" type="text" name="a" id="input-create-d">
                        </div>

                        <div class="input-group mb-3">
                            <span class="input-group-text">答案</span>
                            <select class="form-select" id="correct">
                                <option value="1" selected>A</option>
                                <option value="2">B</option>
                                <option value="3">C</option>
                                <option value="4">D</option>
                            </select>
                        </div>
                        <div id="alert"></div>
                    </div>
                    <div class="modal-footer btn-group">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" id="btn-create">提交</button>
                    </div>
                </div>
            </div>
        </div>

    </body>

    <script>
        function getTasksByPage(page, count = 5) {
            $.get(
                "${pageContext.request.contextPath}/task/all", {
                    "page": page,
                    "count": count
                }, function (data) {
                    if (data === "success") {
                        location.reload()
                    }
                }
            )
        }

        // $("#s-1").click(function (e) {
        //     const page = parseInt($(e.target).innerText) - 1
        //     getTasksByPage(page)
        // })

        $("#btn-create").click(function () {
            createTask(
                $("#input-create-description").val(),
                $("#input-create-a").val(),
                $("#input-create-b").val(),
                $("#input-create-c").val(),
                $("#input-create-d").val(),
                $("option:selected").val()
            )
        })

        function createTask(description, a, b, c, d, correct) {
            $.get(
                "${pageContext.request.contextPath}/task/insert", {
                    "description": description,
                    "a": a,
                    "b": b,
                    "c": c,
                    "d": d,
                    "correct": correct
                }, function (data) {
                    if (data === "success") {
                        alertInCreator("新建成功", "success")
                    } else if (data != null) {
                        alertInCreator(data, "warning")
                    }
                }
            )
        }

        const alertPlaceholder = document.getElementById('alert');

        function alertInCreator(message, type) {
            const wrapper = document.createElement('div');
            wrapper.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible" role="alert">' + message + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'

            alertPlaceholder.append(wrapper)
        }

    </script>
</html>
