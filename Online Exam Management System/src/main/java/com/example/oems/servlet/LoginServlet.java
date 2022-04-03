package com.example.oems.servlet;

import com.example.oems.Result;
import com.example.oems.entity.User;
import com.example.oems.repository.UserRepository;
import com.example.oems.util.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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
        Result<User> result = repository.login(email, password);
        if (result.message != null) writer.append(result.message);
        if (result.t != null) {
            // TODO: 2022/3/27 登录成功
            CookieUtil.saveCookie(response, "email", email, 60 * 60 * 24 * 7, "/");
            CookieUtil.saveCookie(response, "password", password, 60 * 60 * 24 * 7, "/");
            writer.append("success");
        }
    }
}
