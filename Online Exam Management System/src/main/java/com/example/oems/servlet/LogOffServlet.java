package com.example.oems.servlet;

import com.example.oems.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LogOffServlet", value = "/logoff")
public class LogOffServlet extends HttpServlet {
    private UserRepository repository;
    private static final String KEY_EMAIL = "email";

    @Override
    public void init() throws ServletException {
        super.init();
        repository = UserRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(KEY_EMAIL);
        repository.delete(email);
        response.getWriter().append("success");
    }
}
