package com.example.application_in_servlet.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

@WebServlet("/counter")
public class CounterServlet extends HttpServlet {
    private static final String ATTR_COUNT = "count";
    private static final String HEADER_NAME = "refresh";
    private static final int HEADER_VALUE = 1;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        request.setCharacterEncoding(Charset.defaultCharset().name());
        response.setCharacterEncoding(Charset.defaultCharset().name());
        response.setContentType("text/html");
        Integer count = (Integer) getServletContext().getAttribute(ATTR_COUNT);
        if (count == null) count = 1;
        else count++;
        getServletContext().setAttribute(ATTR_COUNT, count);
        //response.setIntHeader(HEADER_NAME, HEADER_VALUE);

        writer.append("<h2> count:").append(String.valueOf(count)).append("</h2>");
    }
}