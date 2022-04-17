package com.example.oems.filter;

import com.example.oems.Logger;
import com.example.oems.Result;
import com.example.oems.entity.User;
import com.example.oems.repository.UserRepository;
import com.example.oems.util.CookieUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Arrays;

@WebFilter(filterName = "RoleFilter", value = "*")
public class RoleFilter implements Filter {
    private static final String PATH = "/oems";
    private static final String TAG = RoleFilter.class.getSimpleName();
    private static final String[] OPEN_LINKS = {PATH + "/register", PATH + "/login", PATH + "/index.jsp", PATH + "/"};
    private static final String[] ACCOUNT_LINKS = {PATH + "/logout", PATH + "/logoff", PATH + "/profile", PATH + "/html/profiles.jsp"};
    private static final String[] ROLE_DEFAULT_LINKS = {PATH + "/task/random", PATH + "/html/exam.jsp"};
    private static final String[] ROLE_ADMIN_LINKS = {
            PATH + "/task/all",
            PATH + "/task/insert",
            PATH + "/html/manage.jsp",
            PATH + "/html/manage_task.jsp",
            PATH + "/html/manage_user.jsp",
            PATH + "/task/delete"
    };

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        Logger.t(TAG, "请求的URI：" + uri);
        if (Arrays.asList(OPEN_LINKS).contains(uri)) {
            chain.doFilter(req, resp);
            Logger.t(TAG, "访问被允许");
        } else {
            String email = CookieUtil.getCookie(request, "email");
            String password = CookieUtil.getCookie(request, "password");
            Result<User> byEmail = UserRepository.getInstance().getByEmail(email);
            User user = byEmail.t;
            if (user != null && user.getPassword().equals(password)) {
                if (Arrays.asList(ACCOUNT_LINKS).contains(uri)
                        || (Arrays.asList(ROLE_DEFAULT_LINKS).contains(uri) && user.getRole() == User.ROLE_DEFAULT)
                        || (Arrays.asList(ROLE_ADMIN_LINKS).contains(uri) && user.getRole() == User.ROLE_ADMIN)) {
                    chain.doFilter(req, resp);
                    Logger.t(TAG, "访问被允许");
                    return;
                }
            }
            Logger.et(TAG, "访问被禁止");
            request.getRequestDispatcher("/html/forbid.jsp").forward(req, resp);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
