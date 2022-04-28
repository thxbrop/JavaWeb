package com.example.oems.filter;

import com.example.oems.base.EncodingRequest;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "CharsetFilter", value = "*")
public class CharacterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding("utf-8");
        if (req.getMethod().equalsIgnoreCase("GET")) {
            req.setCharacterEncoding("utf-8");
            chain.doFilter(new EncodingRequest(req), response);
        } else chain.doFilter(req, response);
    }
}
