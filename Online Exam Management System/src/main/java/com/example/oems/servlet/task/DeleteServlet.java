package com.example.oems.servlet.task;

import com.example.oems.repository.TaskRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/task/delete")
public class DeleteServlet extends HttpServlet {
    private static final String KEY_ID = "id";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(KEY_ID));
        TaskRepository repository = TaskRepository.getInstance();
        repository.deleteTask(id);
        request.getSession().removeAttribute("tasksAll");
        response.getWriter().append("success");
    }
}
