package com.example.oems.servlet.task;

import com.example.oems.entity.Selection;
import com.example.oems.entity.Task;
import com.example.oems.repository.TaskRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "InsertServlet", value = "/task/insert")
public class InsertServlet extends HttpServlet {
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_A = "a";
    private static final String KEY_B = "b";
    private static final String KEY_C = "c";
    private static final String KEY_D = "d";
    private static final String KEY_CORRECT = "correct";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String description = request.getParameter(KEY_DESCRIPTION);
        String a = request.getParameter(KEY_A);
        String b = request.getParameter(KEY_B);
        String c = request.getParameter(KEY_C);
        String d = request.getParameter(KEY_D);
        int correct = Integer.parseInt(request.getParameter(KEY_CORRECT));
        TaskRepository repository = TaskRepository.getInstance();
        repository.saveTask(
                new Task(
                        description,
                        new Selection(a, b, c, d),
                        correct
                )
        );
        repository.close();
        response.getWriter().append("success");
    }
}
