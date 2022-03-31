package com.example.application_in_servlet.servlet;

import com.example.application_in_servlet.Result;
import com.example.application_in_servlet.entity.User;
import com.example.application_in_servlet.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    private UserRepository repository;

    @Override
    public void init() throws ServletException {
        super.init();
        repository = UserRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter(KEY_EMAIL);
        String password = request.getParameter(KEY_PASSWORD);
        PrintWriter writer = response.getWriter();
        Result<User> result = repository.register(email, password);
        if (result.message != null) writer.append(result.message);
        if (result.t != null) {
            // TODO: 2022/3/27 注册成功
            writer.append("success");
        }
    }
}
