package com.example.map_stu_int_in_request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        Map<Student, Integer> map = new HashMap<>();
        map.put(new Student(1, "1"), 1);
        map.put(new Student(2, "2"), 2);
        map.put(new Student(3, "3"), 3);
        map.put(new Student(4, "4"), 4);
        map.put(new Student(5, "5"), 5);
        request.setAttribute("map", map);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}