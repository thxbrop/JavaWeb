<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>起始页</title>
    </head>
    <body>
        <label title="Email">
            <input type="email" name="email">
        </label>
        <label title="Password">
            <input type="password" name="password">
        </label>
        <button id="register">Register</button>
        <button id="login">Login</button>
    </body>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function register(email, password) {
            $.get(
                "${pageContext.request.contextPath}/register", {
                    "email": email,
                    "password": password,
                }, function (data) {
                    alert(data)
                }
            )
        }

        function login(email, password) {
            $.get(
                "${pageContext.request.contextPath}/login", {
                    "email": email,
                    "password": password,
                }, function (data) {
                    if (data === "success") {
                        window.location.replace("${pageContext.request.contextPath}/counter")
                    } else {
                        alert(data)
                    }
                }
            )
        }

        $("#register").click(function () {
            register($("input[name='email']").val(), $("input[name='password']").val())
        })
        $("#login").click(function () {
            login($("input[name='email']").val(), $("input[name='password']").val())
        })
    </script>
</html>