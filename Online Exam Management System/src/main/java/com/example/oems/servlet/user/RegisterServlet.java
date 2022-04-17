package com.example.oems.servlet.user;

import com.example.oems.Result;
import com.example.oems.entity.User;
import com.example.oems.repository.UserRepository;
import com.example.oems.util.StringUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_USERNAME = "username";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter(KEY_EMAIL);
        String password = request.getParameter(KEY_PASSWORD);
        String username = request.getParameter(KEY_USERNAME);
        PrintWriter writer = response.getWriter();
        UserRepository repository = UserRepository.getInstance();
        Result<User> result;
        if (StringUtil.isNullOrEmpty(username)) {
            result = repository.register(email, password);
        } else result = repository.register(email, username, password);
        if (result.message != null) writer.append(result.message);
        if (result.t != null) {
            // TODO: 2022/3/27 注册成功
            writer.append("success");
        }
    }
}
