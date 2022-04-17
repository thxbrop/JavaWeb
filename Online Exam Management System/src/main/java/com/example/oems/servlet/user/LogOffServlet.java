package com.example.oems.servlet.user;

import com.example.oems.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LogOffServlet", value = "/logoff")
public class LogOffServlet extends HttpServlet {
    private static final String KEY_EMAIL = "email";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter(KEY_EMAIL);
        UserRepository repository = UserRepository.getInstance();
        repository.delete(email);
        response.getWriter().append("success");
    }
}
