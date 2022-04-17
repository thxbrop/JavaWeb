package com.example.oems.servlet.user;

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
import java.util.Objects;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ROLE = "role";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter(KEY_EMAIL);
        String username = request.getParameter(KEY_USERNAME);
        String password = request.getParameter(KEY_PASSWORD);
        Integer role = null;
        try {
            role = Integer.parseInt(request.getParameter(KEY_ROLE));
        } catch (Exception ignored) {
        }

        UserRepository repository = UserRepository.getInstance();
        PrintWriter writer = response.getWriter();
        Result<User> result = repository.update(email, username, password, role);
        writer.append(Objects.requireNonNullElse(result.message, "success"));
    }
}
