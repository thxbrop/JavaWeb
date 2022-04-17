package com.example.oems.servlet.task;

import com.example.oems.Result;
import com.example.oems.entity.Task;
import com.example.oems.repository.TaskRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "RandomServlet", value = "/task/random")
public class RandomServlet extends HttpServlet {
    private static final String KEY_LIMIT = "limit";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int limit = Integer.parseInt(request.getParameter(KEY_LIMIT));
        TaskRepository repository = TaskRepository.getInstance();
        Result<List<Task>> result = repository.random(limit, true);
        PrintWriter writer = response.getWriter();
        repository.close();
        if (result.t != null) {
            request.getSession().setAttribute("tasks", result.t);
            writer.append("success");
        }
        if (result.message != null) {
            writer.append(result.message);
        }
    }
}
