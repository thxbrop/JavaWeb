package com.example.oems.servlet;

import com.example.oems.Result;
import com.example.oems.entity.User;
import com.example.oems.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ProxyServlet", value = "/proxy")
public class ProxyServlet extends HttpServlet {
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ROLE = "role";

    private UserRepository repository;

    @Override
    public void init() throws ServletException {
        super.init();
        repository = UserRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(KEY_EMAIL);
        String username = request.getParameter(KEY_USERNAME);
        String password = request.getParameter(KEY_PASSWORD);
        Integer role = null;
        try {
            role = Integer.parseInt(request.getParameter(KEY_ROLE));
        } catch (Exception ignored) {
        }

        PrintWriter writer = response.getWriter();
        Result<User> result = repository.update(email, username, password, role);
        if (result.message != null) {
            writer.append(result.message);
        } else {
            writer.append("success");
        }
    }
}
