package com.example.oems.servlet.task;

import com.example.oems.entity.Task;
import com.example.oems.repository.TaskRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * 获取数据库中所有的Task
 * 由于数据量过大，需要提供page参数进行翻页，每页默认提供count个
 */
@WebServlet(name = "LoadAllServlet", value = "/task/all")
public class LoadAllServlet extends HttpServlet {
    private static final String KEY_PAGE = "page";
    private static final String KEY_COUNT = "count";
    private TaskRepository repository;

    @Override
    public void init() throws ServletException {
        super.init();
        repository = TaskRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int count = Integer.parseInt(request.getParameter(KEY_COUNT));
        int page = Integer.parseInt(request.getParameter(KEY_PAGE));
        List<Task> list = repository.getAll(count, page);
        request.getSession().setAttribute("tasks", list);
        response.getWriter().append("success");
    }
}
